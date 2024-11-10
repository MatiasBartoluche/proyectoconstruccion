package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class GrupoTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_grupo;
    private String nombre_grupo;
    
    @OneToOne(cascade = CascadeType.MERGE)
    // crea la columna capataz_id en la tabla grupotrabajo, que hace referencia al legajo del empleado capataz
    @JoinColumn(name = "capataz_id", referencedColumnName = "legajo")
    private Empleado capataz;
    
    // Relación uno-a-muchos para los empleados subalternos, con cascada de actualizaciones
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_grupo") // Crea una columna 'id_grupo' en la tabla Empleado para esta relación
    private ArrayList<Empleado> empleados;

    public GrupoTrabajo() {
    }

    public GrupoTrabajo(String nombre_grupo, Empleado capataz, ArrayList<Empleado> empleados) {
        this.nombre_grupo = nombre_grupo;
        this.capataz = capataz;
        this.empleados = empleados;
    }

    public int getIdGrupo() {
        return id_grupo;
    }

    public void setIdGrupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombreGrupo() {
        return nombre_grupo;
    }

    public void setNombreGrupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public Empleado getCapataz() {
        return capataz;
    }

    public void setCapataz(Empleado capataz) {
        this.capataz = capataz;
    }
    public List<Empleado> getListaEmpleados() {
        return empleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
