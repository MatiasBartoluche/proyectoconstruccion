package persistencia;

import clases.Presupuesto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class PresupuestoJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public PresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public PresupuestoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Presupuesto pres) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Presupuesto pres) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            pres = em.merge(pres);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = pres.getIdPresupuesto();
            if (findPresupuesto(id) == null) {
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
            Presupuesto pres;
            try {
                pres = em.getReference(Presupuesto.class, id);
                pres.getIdPresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(pres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Presupuesto findPresupuesto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Presupuesto> findPresupuestoEntities(){
        return findPresupuestoEntities(true, -1, -1);
    }
    
    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult){
        return findPresupuestoEntities(false, maxResults, firstResult);
    }
    
    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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