package persistencia;

import clases.HistorialART;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class HistorialARTJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public HistorialARTJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public HistorialARTJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(HistorialART art) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(art);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(HistorialART art) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            art = em.merge(art);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = art.getId_historial_art();
            if (findHistorialART(id) == null) {
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
            HistorialART art;
            try {
                art = em.getReference(HistorialART.class, id);
                art.getId_historial_art();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(art);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public HistorialART findHistorialART(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialART.class, id);
        } finally {
            em.close();
        }
    }

    public List<HistorialART> findHistorialARTEntities(){
        return findHistorialARTEntities(true, -1, -1);
    }
    
    public List<HistorialART> findHistorialARTEntities(int maxResults, int firstResult){
        return findHistorialARTEntities(false, maxResults, firstResult);
    }
    
    private List<HistorialART> findHistorialARTEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialART.class));
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