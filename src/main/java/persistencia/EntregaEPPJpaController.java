package persistencia;

import clases.EPP;
import clases.EntregaEPP;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EntregaEPPJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public EntregaEPPJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntregaEPPJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(EntregaEPP entEpp) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entEpp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(EntregaEPP entEpp) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            entEpp = em.merge(entEpp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = entEpp.getIdEntrega();
            if (findEntregaEPP(id) == null) {
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
            EntregaEPP entEpp;
            try {
                entEpp = em.getReference(EntregaEPP.class, id);
                entEpp.getIdEntrega();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(entEpp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public EntregaEPP findEntregaEPP(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntregaEPP.class, id);
        } finally {
            em.close();
        }
    }

    public List<EntregaEPP> findEntregaEPPEntities(){
        return findEntregaEPPEntities(true, -1, -1);
    }
    
    public List<EntregaEPP> findEntregaEPPEntities(int maxResults, int firstResult){
        return findEntregaEPPEntities(false, maxResults, firstResult);
    }
    
    private List<EntregaEPP> findEntregaEPPEntities(boolean all, int maxResults, int firstResult){
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