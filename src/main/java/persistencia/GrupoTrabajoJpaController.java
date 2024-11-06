package persistencia;

import clases.GrupoTrabajo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GrupoTrabajoJpaController implements Serializable{
                
    
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
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GrupoTrabajo.class));
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
}
