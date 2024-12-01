package clases;

import java.util.ArrayList;
import java.util.List;

// Data transfer objects
public class GrupoTrabajoDTO {
    private int id_grupo;
    private String nombre_grupo;
    private Empleado capataz;
    private List<EmpleadoDTO> empleados = new ArrayList<>();

    public GrupoTrabajoDTO() {
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

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
}