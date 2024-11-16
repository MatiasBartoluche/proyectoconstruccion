package persistencia;

import clases.Jerarquia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class JerarquiaJpaController{

    private EntityManagerFactory emf = null;

    public JerarquiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public JerarquiaJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Jerarquia jerarquia) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jerarquia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Jerarquia jerarquia) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            jerarquia = em.merge(jerarquia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = jerarquia.getIdJerarquia();
            if (findJerarquia(id) == null) {
                throw new EntityNotFoundException("La jerarquia con id " + id + " no existe.");
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
            Jerarquia jerarquia;
            try {
                jerarquia = em.getReference(Jerarquia.class, id);
                jerarquia.getIdJerarquia();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Contrato con id " + id + " no existe.");
            }
            em.remove(jerarquia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Jerarquia findJerarquia(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jerarquia.class, id);
        } finally {
            em.close();
        }
    }

    public List<Jerarquia> findJerarquiaEntities(){
        return findJerarquiaEntities(true, -1, -1);
    }
    
    public List<Jerarquia> findJerarquiaEntities(int maxResults, int firstResult){
        return findJerarquiaEntities(false, maxResults, firstResult);
    }
    
    
    private List<Jerarquia> findJerarquiaEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jerarquia.class));
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
