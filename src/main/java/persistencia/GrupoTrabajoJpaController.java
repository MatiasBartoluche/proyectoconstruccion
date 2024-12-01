package persistencia;

import clases.Empleado;
import clases.EmpleadoDTO;
import clases.GrupoTrabajo;
import clases.GrupoTrabajoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
            GrupoTrabajo grupo;
            try {
                grupo = em.getReference(GrupoTrabajo.class, id);
                grupo.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Contrato con id " + id + " no existe.");
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
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
    
    public List<GrupoTrabajoDTO> convertirAGrupoTrabajoDTO(List<GrupoTrabajo> grupos) {
        List<GrupoTrabajoDTO> dtos = new ArrayList<>();
        for (GrupoTrabajo grupo : grupos) {
            GrupoTrabajoDTO dto = new GrupoTrabajoDTO();
            dto.setIdGrupo(grupo.getIdGrupo());
            dto.setNombreGrupo(grupo.getNombreGrupo());

            List<EmpleadoDTO> empleadosDTO = grupo.getListaEmpleados().stream().map(empleado -> {
                EmpleadoDTO empDTO = new EmpleadoDTO();
                empDTO.setIdEmpleado(empleado.getId());
                empDTO.setNombres(empleado.getNombres());
                empDTO.setFechaIngreso(empleado.getFechaIngreso());
                empDTO.setJerarquia(empleado.getJerarquia());
                empDTO.setContrato(empleado.getContrato());
                empDTO.setCuil(empleado.getCuil());
                empDTO.setLegajo(empleado.getLegajo());
                return empDTO;
            }).collect(Collectors.toList());

            dto.setEmpleados(empleadosDTO);
            dtos.add(dto);
        }
        return dtos;
    }
}
