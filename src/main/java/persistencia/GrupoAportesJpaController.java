package persistencia;

import clases.GrupoAportes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GrupoAportesJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public GrupoAportesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public GrupoAportesJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(GrupoAportes grupoAportes) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(grupoAportes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(GrupoAportes grupoAportes) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            grupoAportes = em.merge(grupoAportes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = grupoAportes.getIdGrupoAporte();
            if (findGrupoAportes(id) == null) {
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
            GrupoAportes grupoAportes;
            try {
                grupoAportes = em.getReference(GrupoAportes.class, id);
                grupoAportes.getIdGrupoAporte();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(grupoAportes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public GrupoAportes findGrupoAportes(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoAportes.class, id);
        } finally {
            em.close();
        }
    }

    public List<GrupoAportes> findGrupoAportesEntities(){
        return findGrupoAportesEntities(true, -1, -1);
    }
    
    public List<GrupoAportes> findGrupoAportesEntities(int maxResults, int firstResult){
        return findGrupoAportesEntities(false, maxResults, firstResult);
    }
    
    private List<GrupoAportes> findGrupoAportesEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GrupoAportes.class));
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