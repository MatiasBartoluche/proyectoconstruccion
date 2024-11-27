package persistencia;

import clases.EPP;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EPPJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public EPPJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EPPJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(EPP epp) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(epp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(EPP epp) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            epp = em.merge(epp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = epp.getId_epp();
            if (findEpp(id) == null) {
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
            EPP epp;
            try {
                epp = em.getReference(EPP.class, id);
                epp.getId_epp();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(epp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public EPP findEpp(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EPP.class, id);
        } finally {
            em.close();
        }
    }

    public List<EPP> findEppEntities(){
        return findEppEntities(true, -1, -1);
    }
    
    public List<EPP> findEppEntities(int maxResults, int firstResult){
        return findEppEntities(false, maxResults, firstResult);
    }
    
    private List<EPP> findEppEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EPP.class));
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
