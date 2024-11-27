package persistencia;

import clases.Subcontratista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class SubcontratistaJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public SubcontratistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SubcontratistaJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Subcontratista sub) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Subcontratista sub) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            sub = em.merge(sub);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = sub.getIdSubcontratista();
            if (findSubcontratista(id) == null) {
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
            Subcontratista sub;
            try {
                sub = em.getReference(Subcontratista.class, id);
                sub.getIdSubcontratista();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(sub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Subcontratista findSubcontratista(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subcontratista.class, id);
        } finally {
            em.close();
        }
    }

    public List<Subcontratista> findSubcontratistaEntities(){
        return findSubcontratistaEntities(true, -1, -1);
    }
    
    public List<Subcontratista> findSubcontratistaEntities(int maxResults, int firstResult){
        return findSubcontratistaEntities(false, maxResults, firstResult);
    }
    
    private List<Subcontratista> findSubcontratistaEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subcontratista.class));
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