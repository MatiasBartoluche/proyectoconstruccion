package clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_usuario;
    
    @OneToOne
    private Empleado empleado;
    private String usuario;
    private String clave;
    
    @OneToOne
    private Rol rol;
    private boolean aprobado = false;
    private String auditoria;
    
    public Usuario() {
    }

    public Usuario(int id_usuario, Empleado empleado, String usuario, String clave, Rol rol, String auditoria) {
        this.id_usuario = id_usuario;
        this.empleado = empleado;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.auditoria = auditoria;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }
    
    

}
