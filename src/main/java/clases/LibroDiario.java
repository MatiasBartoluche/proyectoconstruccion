package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class LibroDiario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_libro_diario;
    
    @OneToOne
    @JoinColumn(name = "id_sociedad")
    private Sociedad sociedadLibroDiario;
    
    @OneToMany(mappedBy = "libroDiario", cascade = CascadeType.MERGE, orphanRemoval = true)
    private ArrayList<RegistroContable> registros = new ArrayList<>();

    public LibroDiario() {
    }

    public int getIiLibroDiario() {
        return id_libro_diario;
    }

    public void setIdLibroDiario(int id_libro_diario) {
        this.id_libro_diario = id_libro_diario;
    }

    public Sociedad getSociedad() {
        return sociedadLibroDiario;
    }

    public void setSociedad(Sociedad sociedad) {
        this.sociedadLibroDiario = sociedad;
    }

    public ArrayList<RegistroContable> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<RegistroContable> registros) {
        this.registros = registros;
    }
}
