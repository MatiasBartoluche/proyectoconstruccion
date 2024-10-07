package persistencia;

import clases.Rol;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;


public class RolJpaController implements Serializable {
    
    
    private EntityManagerFactory emf = null;

    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public RolJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Rol rol) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Rol rol) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            rol = em.merge(rol);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int id = rol.getIdRol();
            if (findRol(id) == null) {
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getIdRol();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El Rol con id " + id + " no existe.");
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Rol findRol(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener todos los usuarios
    public List<Rol> findRolEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT * FROM construccion.rol", Rol.class).getResultList();
        } finally {
            em.close();
        }
    }
    
}