package persistencia;


import clases.Plano;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class PlanoJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public PlanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PlanoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Plano plano) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(plano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Plano plano) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            plano = em.merge(plano);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = plano.getId_plano();
            if (findPlano(id) == null) {
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
            Plano plano;
            try {
                plano = em.getReference(Plano.class, id);
                plano.getId_plano();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(plano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Plano findPlano(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plano.class, id);
        } finally {
            em.close();
        }
    }

    public List<Plano> findPlanoEntities(){
        return findPlanoEntities(true, -1, -1);
    }
    
    public List<Plano> findPlanoEntities(int maxResults, int firstResult){
        return findPlanoEntities(false, maxResults, firstResult);
    }
    
    private List<Plano> findPlanoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Plano.class));
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