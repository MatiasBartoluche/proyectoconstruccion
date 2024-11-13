package persistencia;

import clases.Contrato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ContratoJpaController implements Serializable{

    private static final long serialVersionUID = 1L;
            
    
    private EntityManagerFactory emf = null;

    public ContratoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ContratoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Contrato contrato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Contrato contrato) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            contrato = em.merge(contrato);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = contrato.getIdContrato();
            if (findContrato(id) == null) {
                throw new EntityNotFoundException("El contrato con id " + id + " no existe.");
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
            Contrato contrato;
            try {
                contrato = em.getReference(Contrato.class, id);
                contrato.getIdContrato();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Contrato con id " + id + " no existe.");
            }
            em.remove(contrato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Contrato findContrato(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contrato.class, id);
        } finally {
            em.close();
        }
    }

    public List<Contrato> findContratoEntities(){
        return findContratoEntities(true, -1, -1);
    }
    
    public List<Contrato> findContratoEntities(int maxResults, int firstResult){
        return findContratoEntities(false, maxResults, firstResult);
    }
    
    
    private List<Contrato> findContratoEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contrato.class));
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
