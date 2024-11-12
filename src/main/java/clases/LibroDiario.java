package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LibroDiario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_libro_diario;

    public LibroDiario() {
    }

    public int getIiLibroDiario() {
        return id_libro_diario;
    }

    public void setIdLibroDiario(int id_libro_diario) {
        this.id_libro_diario = id_libro_diario;
    }
    
    
}
