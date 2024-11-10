package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private int legajo; // el legajo sera ingresado manualmente por el usuario del sistema
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_jerarquia", referencedColumnName = "id_jerarquia")
    private Jerarquia jerarquia;
    
    private String nombres;
    private String apellidos;
    private String cuil;
    private String calle;
    private int altura;
    private String localidad;
    private String telefono;
    private String telefono_familiar;
    private byte[] foto_dni;
    private String foto_dni_base64;
    private LocalDate fecha_ingreso; // LocalDate fecha sin hora
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    private Contrato contrato; // 0 = empleado de oficina, 1 = obrero, 2 = subcontratado
    
    private double sueldo_base;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_contrato")
    private Estado estado;
    
    private int antiguedad;
    private boolean despido; // true = despido, false = empleado vigente
    
    @OneToOne
    private GrupoTrabajo grupo;
    
    @OneToMany(mappedBy = "empleado", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<EmpleadoObra> asignaciones;

    public Empleado(int legajo, Jerarquia jerarquia, String nombres, String apellidos, String cuil, String calle, int altura, String localidad, String telefono, String telefono_familiar, byte[] foto_dni, String foto_dni_base64, LocalDate fecha_ingreso, Contrato contrato, double sueldo_base, Estado estado, int antiguedad, boolean despido, Obra obra, GrupoTrabajo grupo) {
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
        this.contrato = contrato;
        this.sueldo_base = sueldo_base;
        this.estado = estado;
        this.antiguedad = antiguedad;
        this.despido = despido;
        this.obra = obra;
        this.grupo = grupo;
    }

    public Empleado() {
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public Jerarquia getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(Jerarquia jerarquia) {
        this.jerarquia = jerarquia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoFamiliar() {
        return telefono_familiar;
    }

    public void setTelefonoFamiliar(String telefono_familiar) {
        this.telefono_familiar = telefono_familiar;
    }

    public byte[] getFotoDni() {
        return foto_dni;
    }

    public void setFotoDni(byte[] foto_dni) {
        this.foto_dni = foto_dni;
    }

    public LocalDate getFechaIngreso() {
        return fecha_ingreso;
    }

    public void setFechaIngreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getSueldoBase() {
        return sueldo_base;
    }

    public void setSueldoBase(double sueldo_base) {
        this.sueldo_base = sueldo_base;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean isDespido() {
        return despido;
    }

    public void setDespido(boolean despido) {
        this.despido = despido;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public GrupoTrabajo getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoTrabajo grupo) {
        this.grupo = grupo;
    }

  
    public String getFotoDniBase64() {
        return foto_dni_base64;
    }

    public void setFotoDniBase64(String foto_dni_base64) {
        this.foto_dni_base64 = foto_dni_base64;
    }

    public List<EmpleadoObra> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<EmpleadoObra> asignaciones) {
        this.asignaciones = asignaciones;
    }
}
