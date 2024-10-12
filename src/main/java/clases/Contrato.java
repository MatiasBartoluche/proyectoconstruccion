package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contrato implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_contrato;
    String descripcion;

    public Contrato() {
    }

    public Contrato(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdContrato() {
        return id_contrato;
    }

    public void setIdContrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
