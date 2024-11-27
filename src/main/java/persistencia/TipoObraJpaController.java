package persistencia;

import clases.TipoObra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class TipoObraJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public TipoObraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TipoObraJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(TipoObra tipo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(TipoObra tipo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            tipo = em.merge(tipo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = tipo.getId_tipo_obra();
            if (findTipoObra(id) == null) {
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
            TipoObra tipo;
            try {
                tipo = em.getReference(TipoObra.class, id);
                tipo.getId_tipo_obra();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(tipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public TipoObra findTipoObra(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoObra.class, id);
        } finally {
            em.close();
        }
    }

    public List<TipoObra> findTipoObraEntities(){
        return findTipoObraEntities(true, -1, -1);
    }
    
    public List<TipoObra> findTipoObraEntities(int maxResults, int firstResult){
        return findTipoObraEntities(false, maxResults, firstResult);
    }
    
    private List<TipoObra> findTipoObraEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoObra.class));
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