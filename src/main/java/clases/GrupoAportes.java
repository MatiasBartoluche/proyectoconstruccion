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

@Entity
public class GrupoAportes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_grupo_aporte;
    
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupo_aporte_id") // Columna en la tabla Aporte para almacenar el ID de GrupoAporte
    private ArrayList<Aporte> aportes = new ArrayList<>();

    public GrupoAportes() {
    }

    public int getIdGrupoAporte() {
        return id_grupo_aporte;
    }

    public void setIdGrupoAporte(int id_grupo_aporte) {
        this.id_grupo_aporte = id_grupo_aporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Aporte> getAportes() {
        return aportes;
    }

    public void setAportes(ArrayList<Aporte> aportes) {
        this.aportes = aportes;
    }
}
