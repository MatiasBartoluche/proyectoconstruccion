package persistencia;

import clases.Empleado;
import clasesDTO.EmpleadoDTO;
import clases.GrupoTrabajo;
import clasesDTO.GrupoTrabajoDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class GrupoTrabajoJpaController{

    private EntityManagerFactory emf = null;

    public GrupoTrabajoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public GrupoTrabajoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(GrupoTrabajo grupo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(GrupoTrabajo grupo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            grupo = em.merge(grupo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = grupo.getIdGrupo();
            if (findGrupoTrabajo(id) == null) {
                throw new EntityNotFoundException("El grupo con id " + id + " no existe.");
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
            GrupoTrabajo grupo = em.find(GrupoTrabajo.class, id);

            if (grupo != null) {
                Empleado capataz = grupo.getCapataz();
                capataz.setGrupo(null);
                em.merge(capataz);
                // Desvincular empleados
                for (Empleado empleado : grupo.getListaEmpleados()) {
                    empleado.setGrupo(null); // Quitar la referencia al grupo
                    em.merge(empleado); // Actualizar en la base de datos
                }
                // Eliminar el grupo
                em.remove(grupo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    // Encontrar usuario por legajo
    public GrupoTrabajo findGrupoTrabajo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public List<GrupoTrabajo> findGrupoTrabajoEntities(){
        return findGrupoTrabajoEntities(true, -1, -1);
    }
    
    public List<GrupoTrabajo> findGrupoTrabajoEntities(int maxResults, int firstResult){
        return findGrupoTrabajoEntities(false, maxResults, firstResult);
    }
    
    
    private List<GrupoTrabajo> findGrupoTrabajoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try {
            // Crear y ejecutar la consulta
            CriteriaQuery<GrupoTrabajo> cq = em.getCriteriaBuilder().createQuery(GrupoTrabajo.class);
            cq.select(cq.from(GrupoTrabajo.class));
            List<GrupoTrabajo> grupos = em.createQuery(cq).getResultList();

            // Cargar las listas de empleados para evitar LazyInitializationException
            for (GrupoTrabajo grupo : grupos) {
                grupo.getListaEmpleados().size(); // Forzar la inicialización
                if (grupo.getCapataz() != null) {
                    grupo.getCapataz().getNombres(); // Inicializa el capataz
                }
            }
            return grupos;
        } finally {
            em.close();
        }
    }

   public GrupoTrabajoDTO convertirGrupoTrabajoDTO(GrupoTrabajo grupoTrabajo) {
        EmpleadoJpaController empJpa = new EmpleadoJpaController();
        if (grupoTrabajo == null) {
            return null;
        }

        EmpleadoDTO capataz = empJpa.convertirAEmpleadoDTO(grupoTrabajo.getCapataz());
        List<EmpleadoDTO> listaEmpleadosDTO = new ArrayList<>();

        // obtener lista de empleados
        List<Empleado> listaEmpleados = grupoTrabajo.getListaEmpleados();
        
        for(Empleado emp : listaEmpleados){
            // si eml empleado no es capataz, agrego a la lista de empleados subalternos
            if(!"Capataz".equals(emp.getJerarquia().getDescripcion())){
                EmpleadoDTO empDTO = empJpa.convertirAEmpleadoDTO(emp);
                listaEmpleadosDTO.add(empDTO);
            }
        }
        // Crear y retornar el DTO
        return new GrupoTrabajoDTO(
            grupoTrabajo.getIdGrupo(),
            grupoTrabajo.getNombreGrupo(),
            capataz,
            listaEmpleadosDTO
        );
    }
    
    public List<GrupoTrabajoDTO> convertirListaGrupoTrabajoDTO(List<GrupoTrabajo> grupos) {
        if (grupos == null || grupos.isEmpty()) {
            return Collections.emptyList();
        }
        return grupos.stream()
            .map(this::convertirGrupoTrabajoDTO)
            .collect(Collectors.toList());
    }
}
