package persistencia;

import clases.Obra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ObraJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public ObraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ObraJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Obra obra) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Obra obra) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            obra = em.merge(obra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = obra.getIdObra();
            if (findObra(id) == null) {
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
            Obra obra;
            try {
                obra = em.getReference(Obra.class, id);
                obra.getIdObra();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(obra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Obra findObra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Obra.class, id);
        } finally {
            em.close();
        }
    }

    public List<Obra> findObraEntities(){
        return findObraEntities(true, -1, -1);
    }
    
    public List<Obra> findObraEntities(int maxResults, int firstResult){
        return findObraEntities(false, maxResults, firstResult);
    }
    
    private List<Obra> findObraEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Obra.class));
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