package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoObra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_tipo_obra;
    
    private String descripcion;

    public TipoObra() {
    }

    public int getId_tipo_obra() {
        return id_tipo_obra;
    }

    public void setId_tipo_obra(int Id_tipo_obra) {
        this.id_tipo_obra = Id_tipo_obra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
