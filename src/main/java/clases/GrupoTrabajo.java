package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class GrupoTrabajo implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_grupo;
    String nombre_grupo;
    
    @OneToOne
    Empleado capataz;
    
    @OneToMany
    ArrayList<Empleado> lista_empleados;

    public GrupoTrabajo() {
    }

    public GrupoTrabajo(String nombre_grupo, Empleado capataz, ArrayList<Empleado> lista_empleados) {
        this.nombre_grupo = nombre_grupo;
        this.capataz = capataz;
        this.lista_empleados = lista_empleados;
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
        return lista_empleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> lista_empleados) {
        this.lista_empleados = lista_empleados;
    }
}
