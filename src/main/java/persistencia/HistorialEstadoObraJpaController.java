package persistencia;

import clases.HistorialEstadoObra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class HistorialEstadoObraJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public HistorialEstadoObraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public HistorialEstadoObraJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(HistorialEstadoObra heo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(heo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(HistorialEstadoObra heo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            heo = em.merge(heo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = heo.getId_historial();
            if (findHistorialEstadoObra(id) == null) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
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
            HistorialEstadoObra heo;
            try {
                heo = em.getReference(HistorialEstadoObra.class, id);
                heo.getId_historial();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(heo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public HistorialEstadoObra findHistorialEstadoObra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialEstadoObra.class, id);
        } finally {
            em.close();
        }
    }

    public List<HistorialEstadoObra> findHistorialEstadoObraEntities(){
        return findHistorialEstadoObraEntities(true, -1, -1);
    }
    
    public List<HistorialEstadoObra> findHistorialEstadoObraEntities(int maxResults, int firstResult){
        return findHistorialEstadoObraEntities(false, maxResults, firstResult);
    }
    
    private List<HistorialEstadoObra> findHistorialEstadoObraEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialEstadoObra.class));
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