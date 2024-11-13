package persistencia;

import clases.Aporte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class AporteJpaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public AporteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AporteJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Aporte aporte) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Aporte aporte) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            aporte = em.merge(aporte);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = aporte.getId_aporte();
            if (findAporte(id) == null) {
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
            Aporte aporte;
            try {
                aporte = em.getReference(Aporte.class, id);
                aporte.getId_aporte();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(aporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Aporte findAporte(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aporte.class, id);
        } finally {
            em.close();
        }
    }

    public List<Aporte> findAporteEntities(){
        return findAporteEntities(true, -1, -1);
    }
    
    public List<Aporte> findAporteEntities(int maxResults, int firstResult){
        return findAporteEntities(false, maxResults, firstResult);
    }
    
    
    private List<Aporte> findAporteEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aporte.class));
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
