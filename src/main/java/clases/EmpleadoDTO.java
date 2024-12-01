package clases;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmpleadoDTO {
    private int id_empleado;
    private int legajo;
    private Jerarquia jerarquia;
    
    private String nombres;
    private String apellidos;
    private String cuil;
    private String calle;
    private int altura;
    private String piso;
    private String localidad;
    private String telefono;
    private String telefono_familiar;
    private byte[] foto_dni;
    private String foto_dni_base64;
    private LocalDate fecha_ingreso; // LocalDate fecha sin hora
    private int antiguedad;
    private boolean despido; // true = despido, false = empleado vigente
    
    private BigDecimal sueldo_base;
    
    private Contrato contrato;

    private EstadoEmpleado estado;

    private GrupoTrabajoDTO grupoDTO;

    private ArrayList<EmpleadoObra> asignaciones = new ArrayList<>();
    
    private ArrayList<HistorialART> historialART = new ArrayList<>();

    private ArrayList<LiquidacionSueldo> liquidaciones = new ArrayList<>();
 
    private ArrayList<EntregaEPP> planillaEPP = new ArrayList<>();
    
    private ArrayList<Asistencia> asistencias;

    public EmpleadoDTO() {
    }

    public int getIdEmpleado() {
        return id_empleado;
    }

    public void setIdEmpleado(int id_empleado) {
        this.id_empleado = id_empleado;
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

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
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

    public String getFotoDniBase64() {
        return foto_dni_base64;
    }

    public void setFotoDniBase64(String foto_dni_base64) {
        this.foto_dni_base64 = foto_dni_base64;
    }

    public LocalDate getFechaIngreso() {
        return fecha_ingreso;
    }

    public void setFechaIngreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
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

    public BigDecimal getSueldoBase() {
        return sueldo_base;
    }

    public void setSueldoBase(BigDecimal sueldo_base) {
        this.sueldo_base = sueldo_base;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }

    public void setEstado(EstadoEmpleado estado) {
        this.estado = estado;
    }

    public GrupoTrabajoDTO getGrupo() {
        return grupoDTO;
    }

    public void setGrupo(GrupoTrabajoDTO grupoDTO) {
        this.grupoDTO = grupoDTO;
    }

    public ArrayList<EmpleadoObra> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(ArrayList<EmpleadoObra> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public ArrayList<HistorialART> getHistorialART() {
        return historialART;
    }

    public void setHistorialART(ArrayList<HistorialART> historialART) {
        this.historialART = historialART;
    }

    public ArrayList<LiquidacionSueldo> getLiquidaciones() {
        return liquidaciones;
    }

    public void setLiquidaciones(ArrayList<LiquidacionSueldo> liquidaciones) {
        this.liquidaciones = liquidaciones;
    }

    public ArrayList<EntregaEPP> getPlanillaEPP() {
        return planillaEPP;
    }

    public void setPlanillaEPP(ArrayList<EntregaEPP> planillaEPP) {
        this.planillaEPP = planillaEPP;
    }

    public ArrayList<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(ArrayList<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
    
}
