package persistencia;

import clases.EstadoObra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EstadoObraJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public EstadoObraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EstadoObraJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(EstadoObra estObra) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(estObra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(EstadoObra estObra) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            estObra = em.merge(estObra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = estObra.getId_estado_obra();
            if (findEstadoObra(id) == null) {
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
            EstadoObra estObra;
            try {
                estObra = em.getReference(EstadoObra.class, id);
                estObra.getId_estado_obra();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(estObra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public EstadoObra findEstadoObra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoObra.class, id);
        } finally {
            em.close();
        }
    }

    public List<EstadoObra> findEstadoObraEntities(){
        return findEstadoObraEntities(true, -1, -1);
    }
    
    public List<EstadoObra> findEstadoObraEntities(int maxResults, int firstResult){
        return findEstadoObraEntities(false, maxResults, firstResult);
    }
    
    private List<EstadoObra> findEstadoObraEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoObra.class));
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