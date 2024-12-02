package clasesDTO;

import clases.Empleado;
import java.util.ArrayList;
import java.util.List;

// Data transfer objects
public class GrupoTrabajoDTO {
    private int id_grupo;
    private String nombre_grupo;
    private EmpleadoDTO capataz;
    private List<EmpleadoDTO> empleados;

    public GrupoTrabajoDTO() {
    }

    public GrupoTrabajoDTO(int id_grupo, String nombre_grupo, EmpleadoDTO capataz, List<EmpleadoDTO> empleados) {
        this.id_grupo = id_grupo;
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

    public EmpleadoDTO getCapataz() {
        return capataz;
    }

    public void setCapataz(EmpleadoDTO capataz) {
        this.capataz = capataz;
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
}
