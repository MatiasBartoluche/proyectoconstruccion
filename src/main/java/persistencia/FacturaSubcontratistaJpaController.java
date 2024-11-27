package persistencia;

import clases.FacturaSubcontratista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class FacturaSubcontratistaJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public FacturaSubcontratistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public FacturaSubcontratistaJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(FacturaSubcontratista facSub) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(facSub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(FacturaSubcontratista facSub) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            facSub = em.merge(facSub);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = facSub.getId_subcontratista();
            if (findFacturaSubcontratista(id) == null) {
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
            FacturaSubcontratista facSub;
            try {
                facSub = em.getReference(FacturaSubcontratista.class, id);
                facSub.getId_subcontratista();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(facSub);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public FacturaSubcontratista findFacturaSubcontratista(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaSubcontratista.class, id);
        } finally {
            em.close();
        }
    }

    public List<FacturaSubcontratista> findFacturaSubcontratistaEntities(){
        return findFacturaSubcontratistaEntities(true, -1, -1);
    }
    
    public List<FacturaSubcontratista> findFacturaSubcontratistaEntities(int maxResults, int firstResult){
        return findFacturaSubcontratistaEntities(false, maxResults, firstResult);
    }
    
    private List<FacturaSubcontratista> findFacturaSubcontratistaEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaSubcontratista.class));
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