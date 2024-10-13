package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estado implements Serializable{
        
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_estado;
    String descripcion;

    public Estado() {
    }

    public Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstado() {
        return id_estado;
    }

    public void setIdEstado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
