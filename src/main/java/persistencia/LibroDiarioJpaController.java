package persistencia;

import clases.LibroDiario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class LibroDiarioJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public LibroDiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public LibroDiarioJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(LibroDiario libro) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(LibroDiario libro) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = libro.getIdLibroDiario();
            if (findLibroDiario(id) == null) {
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
            LibroDiario libro;
            try {
                libro = em.getReference(LibroDiario.class, id);
                libro.getIdLibroDiario();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public LibroDiario findLibroDiario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LibroDiario.class, id);
        } finally {
            em.close();
        }
    }

    public List<LibroDiario> findLibroDiarioEntities(){
        return findLibroDiarioEntities(true, -1, -1);
    }
    
    public List<LibroDiario> findLibroDiarioEntities(int maxResults, int firstResult){
        return findLibroDiarioEntities(false, maxResults, firstResult);
    }
    
    private List<LibroDiario> findLibroDiarioEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LibroDiario.class));
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