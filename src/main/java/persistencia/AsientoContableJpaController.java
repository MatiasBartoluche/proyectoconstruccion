package persistencia;

import clases.AsientoContable;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class AsientoContableJpaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public AsientoContableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AsientoContableJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(AsientoContable asiento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(asiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(AsientoContable asiento) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            asiento = em.merge(asiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = asiento.getId_asiento();
            if (findAsiento(id) == null) {
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
            AsientoContable asiento;
            try {
                asiento = em.getReference(AsientoContable.class, id);
                asiento.getId_asiento();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(asiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public AsientoContable findAsiento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsientoContable.class, id);
        } finally {
            em.close();
        }
    }

    public List<AsientoContable> findAsientoEntities(){
        return findAsientoEntities(true, -1, -1);
    }
    
    public List<AsientoContable> findAsientoEntities(int maxResults, int firstResult){
        return findAsientoEntities(false, maxResults, firstResult);
    }
    
    
    private List<AsientoContable> findAsientoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsientoContable.class));
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
