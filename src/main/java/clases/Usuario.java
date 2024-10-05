package clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_usuario;
    private int legajo;
    private String usuario;
    private String clave;
    private int id_rol;
    private boolean aprobado = false;
    private String auditoria;
    
    public Usuario() {
    }
    
    public Usuario(int id_usuario, int legajo, String usuario, String clave, int id_rol) {
        this.id_usuario = id_usuario;
        this.legajo = legajo;
        this.usuario = usuario;
        this.clave = clave;
        this.id_rol = id_rol;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public int getId_rol() {
        return id_rol;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public String getAuditoria() {
        return auditoria;
    }

}
