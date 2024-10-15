package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_rol;
    private String descripcion;

    public Rol() {
    }

    public Rol(int id_rol, String descripcion) {
        this.id_rol = id_rol;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return id_rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdRol(int id_rol) {
        this.id_rol = id_rol;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
