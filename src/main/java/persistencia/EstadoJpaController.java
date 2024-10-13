package persistencia;

import clases.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EstadoJpaController {

    private EntityManagerFactory emf = null;

    public EstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EstadoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Estado estado) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Estado estado) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            estado = em.merge(estado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = estado.getIdEstado();
            if (findEstado(id) == null) {
                throw new EntityNotFoundException("El Rol con id " + id + " no existe.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Rol con id " + id + " no existe.");
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Estado findEstado(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public List<Estado> findEstadoEntities(){
        return findEstadoEntities(true, -1, -1);
    }
    
    public List<Estado> findEstadoEntities(int maxResults, int firstResult){
        return findEstadoEntities(false, maxResults, firstResult);
    }
    
    
    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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
