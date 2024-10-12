package clases;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Empleado implements Serializable {
    
    @Id
    private int legajo; // el legajo sera ingresado manualmente por el usuario del sistema
    
    @OneToOne
    private Jerarquia jerarquia;
    private String nombres;
    private String apellidos;
    private String cuil;
    private String calle;
    private int altura;
    private String localidad;
    private String telefono;
    private String telefono_familiar;
    private String foto_dni;
    private LocalDate fecha_ingreso; // LocalDate fecha sin hora
    private int tipo_contrato; // 0 = empleado de oficina, 1 = obrero, 2 = subcontratado
    private double sueldo_base;
    private int id_estado_empleado;
    private int antiguedad;
    private boolean despido; // true = despido, false = empleado vigente
    private int id_obra;
    private int id_grupo;

    public Empleado(
            int legajo, 
            Jerarquia jerarquia, 
            String nombres, 
            String apellidos, 
            String cuil, 
            String calle, 
            int altura, 
            String localidad, 
            String telefono, 
            String telefono_familiar, 
            String foto_dni, 
            LocalDate fecha_ingreso, 
            int tipo_contrato, 
            double sueldo_base, 
            int id_estado_empleado, 
            int antiguedad, 
            boolean despido, 
            int id_obra, 
            int id_grupo) {
        this.legajo = legajo;
        this.jerarquia = jerarquia;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cuil = cuil;
        this.calle = calle;
        this.altura = altura;
        this.localidad = localidad;
        this.telefono = telefono;
        this.telefono_familiar = telefono_familiar;
        this.foto_dni = foto_dni;
        this.fecha_ingreso = fecha_ingreso;
        this.tipo_contrato = tipo_contrato;
        this.sueldo_base = sueldo_base;
        this.id_estado_empleado = id_estado_empleado;
        this.antiguedad = antiguedad;
        this.despido = despido;
        this.id_obra = id_obra;
        this.id_grupo = id_grupo;
    }

    public Empleado() {
    }

    public int getLegajo() {
        return legajo;
    }

    public Jerarquia getJerarquia() {
        return jerarquia;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCuil() {
        return cuil;
    }

    public String getCalle() {
        return calle;
    }

    public int getAltura() {
        return altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefonoFamiliar() {
        return telefono_familiar;
    }

    public String getFotoDni() {
        return foto_dni;
    }

    public LocalDate getFechaIngreso() {
        return fecha_ingreso;
    }

    public int getTipoContrato() {
        return tipo_contrato;
    }

    public double getSueldoBase() {
        return sueldo_base;
    }

    public int getIdEstadoEmpleado() {
        return id_estado_empleado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public boolean isDespido() {
        return despido;
    }

    public int getIdObra() {
        return id_obra;
    }

    public int getIdGrupo() {
        return id_grupo;
    }

    /* ############################ setters ##################################*/
    
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void setJerarquia(Jerarquia jerarquia) {
        this.jerarquia = jerarquia;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTelefonoFamiliar(String telefono_familiar) {
        this.telefono_familiar = telefono_familiar;
    }

    public void setFotoDni(String foto_dni) {
        this.foto_dni = foto_dni;
    }

    public void setFechaIngreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setTipoContrato(int tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public void setSueldoBase(double sueldo_base) {
        this.sueldo_base = sueldo_base;
    }

    public void setIdEstadoEmpleado(int id_estado_empleado) {
        this.id_estado_empleado = id_estado_empleado;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public void setDespido(boolean despido) {
        this.despido = despido;
    }

    public void setIdObra(int id_obra) {
        this.id_obra = id_obra;
    }

    public void setIdGrupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
    
    
}
