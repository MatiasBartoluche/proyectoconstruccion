
package persistencia;

import clases.Empleado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;


public class EmpleadoJpaController implements Serializable {
    
    
    private EntityManagerFactory emf = null;

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EmpleadoJpaController(){
        emf = Persistence.createEntityManagerFactory("construccionPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear usuario
    public void create(Empleado empleado) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Editar usuario
    public void edit(Empleado empleado) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            empleado = em.merge(empleado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            int legajo = empleado.getLegajo();
            if (findEmpleado(legajo) == null) {
                throw new EntityNotFoundException("El Empleado con legajo " + legajo + " no existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Eliminar usuario
    public void destroy(int legajo) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, legajo);
                empleado.getLegajo();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("El empleado con legajo " + legajo + " no existe.");
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Encontrar usuario por legajo
    public Empleado findEmpleado(int legajo) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, legajo);
        } finally {
            em.close();
        }
    }

    // Obtener todos los usuarios
    public List<Empleado> findEmpleadoEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT * FROM construccion.empleado", Empleado.class).getResultList();
        } finally {
            em.close();
        }
    }
    
}
