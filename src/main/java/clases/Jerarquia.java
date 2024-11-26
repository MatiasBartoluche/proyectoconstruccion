package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jerarquia implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_jerarquia;
    
    private String descripcion;

    public Jerarquia() {
    }

    public Jerarquia(String descripcion) {
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
