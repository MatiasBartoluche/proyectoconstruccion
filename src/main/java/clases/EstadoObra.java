package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstadoObra implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_estado_obra;
    private String descripcion;

    public EstadoObra() {
    }

    public int getId_estado_obra() {
        return id_estado_obra;
    }

    public void setId_estado_obra(int id_estado_obra) {
        this.id_estado_obra = id_estado_obra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
