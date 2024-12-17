package persistencia;

import clases.Seguro;
import clases.Sociedad;
import clasesDTO.SeguroDTO;
import clasesDTO.SociedadDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class SeguroJpaController implements Serializable{

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf = null;

    public SeguroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SeguroJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Seguro seg) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(seg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Seguro seg) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            seg = em.merge(seg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = seg.getIdSeguro();
            if (findSeguro(id) == null) {
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
            Seguro seg;
            try {
                seg = em.getReference(Seguro.class, id);
                seg.getIdSeguro();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El aporte con id " + id + " no existe.");
            }
            em.remove(seg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Seguro findSeguro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seguro.class, id);
        } finally {
            em.close();
        }
    }

    public List<Seguro> findSeguroEntities(){
        return findSeguroEntities(true, -1, -1);
    }
    
    public List<Seguro> findSeguroEntities(int maxResults, int firstResult){
        return findSeguroEntities(false, maxResults, firstResult);
    }
    
    private List<Seguro> findSeguroEntities(boolean all, int maxResults, int firstResult){
        EntityManager em = getEntityManager();
        try{
            //Query query = em.createQuery("SELECT rol FROM rol");
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seguro.class));
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

    public SeguroDTO convertirASeguroDTO(Seguro seguro) {
        SeguroDTO segDTO = new SeguroDTO();
        
        segDTO.setIdSeguro(seguro.getIdSeguro());
        segDTO.setNombre(seguro.getNombre());
        segDTO.setCuit(seguro.getCuit());
        segDTO.setNumeroPoliza(seguro.getNumeroPoliza());
        segDTO.setFechaContratacion(seguro.getFechaContratacion());
        segDTO.setFechaVencimiento(seguro.getFechaVencimiento());
        
        Sociedad sociedad = seguro.getSociedad();
        SociedadDTO socDTO = new SociedadDTO();
        
        socDTO.setIdSociedad(sociedad.getIdSociedad());
        socDTO.setNombre(sociedad.getNombre());
        socDTO.setCuitSociedad(sociedad.getCuitSociedad());
        socDTO.setRazonSocial(sociedad.getRazonSocial());
        socDTO.setTelefono(sociedad.getTelefono());
        socDTO.setMail(sociedad.getMail());
        socDTO.setCalle(sociedad.getCalle());
        socDTO.setAltura(sociedad.getAltura());
        socDTO.setPiso(sociedad.getPiso());
        socDTO.setLocalidad(sociedad.getLocalidad());
        
        segDTO.setSociedad(socDTO);

        return segDTO;
    }
    
    public List<SeguroDTO> convertirListaASeguroDTO(List<Seguro> seguros) {
        if (seguros == null || seguros.isEmpty()) {
            return Collections.emptyList();
        }

        List<SeguroDTO> listaDTO = new ArrayList<>();
        for (Seguro seg : seguros) {
            listaDTO.add(convertirASeguroDTO(seg));
        }
        return listaDTO;
    }
}