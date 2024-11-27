package persistencia;

import clases.EmpleadoObra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class EmpleadoObraJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public EmpleadoObraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EmpleadoObraJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(EmpleadoObra empObra) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empObra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(EmpleadoObra empObra) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            empObra = em.merge(empObra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = empObra.getId();
            if (findEmpleadoObra(id) == null) {
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
            EmpleadoObra empObra;
            try {
                empObra = em.getReference(EmpleadoObra.class, id);
                empObra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(empObra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public EmpleadoObra findEmpleadoObra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpleadoObra.class, id);
        } finally {
            em.close();
        }
    }

    public List<EmpleadoObra> findEmpleadoObraEntities(){
        return findEmpleadoObraEntities(true, -1, -1);
    }
    
    public List<EmpleadoObra> findEmpleadoObraEntities(int maxResults, int firstResult){
        return findEmpleadoObraEntities(false, maxResults, firstResult);
    }
    
    private List<EmpleadoObra> findEmpleadoObraEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmpleadoObra.class));
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