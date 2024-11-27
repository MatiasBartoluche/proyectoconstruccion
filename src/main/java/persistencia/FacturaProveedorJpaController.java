package persistencia;

import clases.FacturaProveedor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class FacturaProveedorJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public FacturaProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public FacturaProveedorJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(FacturaProveedor facProv) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(facProv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(FacturaProveedor facProv) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            facProv = em.merge(facProv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = facProv.getIdFactura();
            if (findFacturaProveedor(id) == null) {
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
            FacturaProveedor facProv;
            try {
                facProv = em.getReference(FacturaProveedor.class, id);
                facProv.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(facProv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public FacturaProveedor findFacturaProveedor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public List<FacturaProveedor> findFacturaProveedorEntities(){
        return findFacturaProveedorEntities(true, -1, -1);
    }
    
    public List<FacturaProveedor> findFacturaProveedorEntities(int maxResults, int firstResult){
        return findFacturaProveedorEntities(false, maxResults, firstResult);
    }
    
    private List<FacturaProveedor> findFacturaProveedorEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaProveedor.class));
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