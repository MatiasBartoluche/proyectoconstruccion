package clases;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_usuario;
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Empleado empleado;
    
    private String usuario;
    private String clave;
    private String salt;
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;
    
    private boolean aprobado = false;
    private String auditoria;
    
    public Usuario() {
    }

    public Usuario(Empleado empleado, String usuario, String clave, String salt, Rol rol, String auditoria) {
        this.empleado = empleado;
        this.usuario = usuario;
        this.clave = clave;
        this.salt = salt;
        this.rol = rol;
        this.auditoria = auditoria;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
    
    public boolean usuarioExiste(String usuario, String clave){
        boolean existe = false;
        
        if(this.usuario.equals(usuario) && this.clave.equals(clave)){
            existe = true;
        }
        return existe;
    }
}
