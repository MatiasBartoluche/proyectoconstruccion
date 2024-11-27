package persistencia;

import clases.RegistroContable;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class RegistroContableJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public RegistroContableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RegistroContableJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(RegistroContable reg) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(reg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(RegistroContable reg) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            reg = em.merge(reg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = reg.getIdRegistroContable();
            if (findRegistroContable(id) == null) {
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
            RegistroContable reg;
            try {
                reg = em.getReference(RegistroContable.class, id);
                reg.getIdRegistroContable();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(reg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public RegistroContable findRegistroContable(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroContable.class, id);
        } finally {
            em.close();
        }
    }

    public List<RegistroContable> findRegistroContableEntities(){
        return findRegistroContableEntities(true, -1, -1);
    }
    
    public List<RegistroContable> findRegistroContableEntities(int maxResults, int firstResult){
        return findRegistroContableEntities(false, maxResults, firstResult);
    }
    
    private List<RegistroContable> findRegistroContableEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroContable.class));
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