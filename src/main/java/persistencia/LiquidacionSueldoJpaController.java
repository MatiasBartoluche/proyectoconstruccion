package persistencia;

import clases.LiquidacionSueldo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class LiquidacionSueldoJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public LiquidacionSueldoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LiquidacionSueldoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(LiquidacionSueldo liq) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(liq);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(LiquidacionSueldo liq) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            liq = em.merge(liq);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = liq.getId_liquidacion();
            if (findLiquidacionSueldo(id) == null) {
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
            LiquidacionSueldo liq;
            try {
                liq = em.getReference(LiquidacionSueldo.class, id);
                liq.getId_liquidacion();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(liq);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public LiquidacionSueldo findLiquidacionSueldo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LiquidacionSueldo.class, id);
        } finally {
            em.close();
        }
    }

    public List<LiquidacionSueldo> findLiquidacionSueldoEntities(){
        return findLiquidacionSueldoEntities(true, -1, -1);
    }
    
    public List<LiquidacionSueldo> findLiquidacionSueldoEntities(int maxResults, int firstResult){
        return findLiquidacionSueldoEntities(false, maxResults, firstResult);
    }
    
    private List<LiquidacionSueldo> findLiquidacionSueldoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LiquidacionSueldo.class));
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