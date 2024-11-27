package persistencia;

import clases.Proveedor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ProveedorJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProveedorJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Proveedor prov) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(prov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Proveedor prov) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            prov = em.merge(prov);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = prov.getIdProveedor();
            if (findProveedor(id) == null) {
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
            Proveedor prov;
            try {
                prov = em.getReference(Proveedor.class, id);
                prov.getIdProveedor();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(prov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Proveedor findProveedor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public List<Proveedor> findProveedorEntities(){
        return findProveedorEntities(true, -1, -1);
    }
    
    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult){
        return findProveedorEntities(false, maxResults, firstResult);
    }
    
    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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