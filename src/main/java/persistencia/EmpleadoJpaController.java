package persistencia;

import clases.Empleado;
import clases.GrupoTrabajo;
import clasesDTO.EmpleadoDTO;
import clasesDTO.GrupoTrabajoDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class EmpleadoJpaController{

    private EntityManagerFactory emf = null;

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EmpleadoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Empleado empleado) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Empleado empleado) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            empleado = em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = empleado.getId();
            if (findEmpleado(id) == null) {
                throw new EntityNotFoundException("El Empleado con legajo " + id + " no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar usuario
    public void destroy(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Empleado con legajo " + id + " no existe.");
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Empleado findEmpleado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public List<Empleado> findEmpleadoEntities(){
        return findEmpleadoEntities(true, -1, -1);
    }
    
    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult){
        return findEmpleadoEntities(false, maxResults, firstResult);
    }
    
    
    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query query = em.createQuery(cq);
            if(!all){
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        }
        finally{
            em.close();
        }
    }
    
    public Empleado findEmpleadoByLegajo(int legajo) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
            Root<Empleado> empleado = cq.from(Empleado.class);

            // Construir la condición
            cq.where(cb.equal(empleado.get("legajo"), legajo));

            TypedQuery<Empleado> query = em.createQuery(cq);
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        finally{
            em.close();
        }
    }
    
    public List<Empleado> findEmpleadosByAttributes(Map<String, Object> parametros){
        EntityManager em = getEntityManager();
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
            Root<Empleado> empleado = cq.from(Empleado.class);

            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, Object> filtro : parametros.entrySet()) {
                String atributo = filtro.getKey();
                Object valor = filtro.getValue();

                if (valor instanceof String && !((String) valor).isEmpty()) {
                    predicates.add(cb.like(empleado.get(atributo), "%" + valor + "%"));
                } else if (valor != null) {
                    predicates.add(cb.equal(empleado.get(atributo), valor));
                }
            }

            cq.where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Empleado> findEmpleadosByGroup(Integer idGrupo, String nombreGrupo) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Empleado> cq = cb.createQuery(Empleado.class);
            Root<Empleado> empleado = cq.from(Empleado.class);

            // Crear un alias para la relación con GrupoTrabajo
            Join<Empleado, GrupoTrabajo> grupo = empleado.join("grupo");

            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por id del grupo si se proporciona
            if (idGrupo != null) {
                predicates.add(cb.equal(grupo.get("id_grupo"), idGrupo));
            }

            // Filtrar por nombre del grupo si se proporciona
            if (nombreGrupo != null && !nombreGrupo.isEmpty()) {
                predicates.add(cb.like(grupo.get("nombre_grupo"), "%" + nombreGrupo + "%"));
            }

            // Agregar las condiciones al query
            cq.where(predicates.toArray(new Predicate[0]));

            // Ejecutar la consulta
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
    
    //convertir una instancia de Empleado a EmpleadoDto 
    public EmpleadoDTO convertirAEmpleadoDTO(Empleado empleado) {
        EmpleadoDTO empDTO = null;
        GrupoTrabajoDTO grupoDTO = null;
        // Obtener datos del grupo y capataz si existen
        int idGrupo = 0;
        String nombreGrupo = null;
        if (empleado == null) {
            empDTO = null;
        }
        
        /*if (empleado.getGrupo() != null) {
             nombreGrupo = empleado.getGrupo().getNombre(); // Nombre del grupo
             if (empleado.getGrupo().getCapataz() != null) {
                 nombreCapataz = empleado.getGrupo().getCapataz().getNombre(); // Nombre del capataz
             }
         }*/
       
        empDTO = new EmpleadoDTO();
        empDTO.setIdEmpleado(empleado.getId());
        empDTO.setLegajo(empleado.getLegajo());
        empDTO.setNombres(empleado.getNombres());
        empDTO.setApellidos(empleado.getApellidos());
        empDTO.setCuil(empleado.getCuil());
        empDTO.setCalle(empleado.getCalle());
        empDTO.setAltura(empleado.getAltura());
        empDTO.setPiso(empleado.getPiso());
        empDTO.setLocalidad(empleado.getLocalidad());
        empDTO.setTelefono(empleado.getTelefono());
        empDTO.setTelefonoFamiliar(empleado.getTelefonoFamiliar());
        empDTO.setFotoDni(empleado.getFotoDni());
        empDTO.setFechaIngreso(empleado.getFechaIngreso());
        empDTO.setDespido(empleado.isDespido());
        empDTO.setSueldoBase(empleado.getSueldoBase());
        
        empDTO.setJerarquia(empleado.getJerarquia());
        empDTO.setContrato(empleado.getContrato());
        empDTO.setEstado(empleado.getEstado());
        //empDTO.setGrupo();
        empDTO.setAsignaciones(empleado.getAsignaciones());
        empDTO.setHistorialART(empleado.getHistorialART());
        empDTO.setLiquidaciones(empleado.getLiquidaciones());
        empDTO.setPlanillaEPP(empleado.getPlanillaEPP());
        empDTO.setAsistencias(empleado.getAsistencias());
//----------------------------------------------------------------------------------

        //return new EmpleadoDTO();
        return empDTO;
    }

    public List<EmpleadoDTO> convertirListaAEmpleadoDTO(List<Empleado> empleados) {
        if (empleados == null || empleados.isEmpty()) {
            return Collections.emptyList();
        }

        List<EmpleadoDTO> listaDTO = new ArrayList<>();
        for (Empleado empleado : empleados) {
            listaDTO.add(convertirAEmpleadoDTO(empleado));
        }
        return listaDTO;
    }
}
