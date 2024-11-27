package persistencia;

import clases.Sociedad;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class SociedadJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public SociedadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SociedadJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Sociedad soc) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(soc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Sociedad soc) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            soc = em.merge(soc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = soc.getIdSociedad();
            if (findSociedad(id) == null) {
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
            Sociedad soc;
            try {
                soc = em.getReference(Sociedad.class, id);
                soc.getIdSociedad();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(soc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Sociedad findSociedad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sociedad.class, id);
        } finally {
            em.close();
        }
    }

    public List<Sociedad> findSociedadEntities(){
        return findSociedadEntities(true, -1, -1);
    }
    
    public List<Sociedad> findSociedadEntities(int maxResults, int firstResult){
        return findSociedadEntities(false, maxResults, firstResult);
    }
    
    private List<Sociedad> findSociedadEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sociedad.class));
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