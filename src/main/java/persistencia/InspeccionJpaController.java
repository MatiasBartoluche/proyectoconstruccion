package persistencia;

import clases.Inspeccion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class InspeccionJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public InspeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public InspeccionJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Inspeccion insp) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(insp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Inspeccion insp) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            insp = em.merge(insp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = insp.getNumeroActa();
            if (findInspeccion(id) == null) {
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
            Inspeccion insp;
            try {
                insp = em.getReference(Inspeccion.class, id);
                insp.getNumeroActa();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(insp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Inspeccion findInspeccion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inspeccion.class, id);
        } finally {
            em.close();
        }
    }

    public List<Inspeccion> findInspeccionEntities(){
        return findInspeccionEntities(true, -1, -1);
    }
    
    public List<Inspeccion> findInspeccionEntities(int maxResults, int firstResult){
        return findInspeccionEntities(false, maxResults, firstResult);
    }
    
    private List<Inspeccion> findInspeccionEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inspeccion.class));
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