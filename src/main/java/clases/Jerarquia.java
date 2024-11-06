package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jerarquia implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_jerarquia;
    private String descripcion;

    public Jerarquia() {
    }

    public Jerarquia(String descripcion) {
        this.id_jerarquia = id_jerarquia;
        this.descripcion = descripcion;
    }

    public int getIdJerarquia() {
        return id_jerarquia;
    }

    public void setIdJerarquia(int id_jerarquia) {
        this.id_jerarquia = id_jerarquia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
