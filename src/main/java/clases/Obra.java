package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_obra;
    
    // apunta al atributo "id_sociedad" de la clase Sociedad
    @ManyToOne
    @JoinColumn(name = "id_sociedad", nullable = false)
    private Sociedad sociedad;
    
    private String expediente_dgroc;
    private String expediente_dgfyco;
    private String nombre_obra;
    private double superficie_m2;
    private String calle;
    private int altura;
    private String localidad;
    //private Provincia provincia;
    private LocalDate fecha_inicio;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_tipo:obra", referencedColumnName = "id_tipoObra")
    private TipoObra tipo_obra;
    
    // OneToMany representa relacion n-n con actualizacion en cascada
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private ArrayList<EmpleadoObra> asignaciones;
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private ArrayList<historialEstadoObra> historial_estado_obra;
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private ArrayList<Plano> planos;
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private ArrayList<Inspeccion> inspecciones;
    
    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private ArrayList<HistorialART> historialART;

    public Obra() {
    }

    public int getIdObra() {
        return id_obra;
    }

    public void setIdObra(int id_obra) {
        this.id_obra = id_obra;
    }

    public String getExpedienteDgroc() {
        return expediente_dgroc;
    }

    public void setExpedienteDgroc(String expediente_dgroc) {
        this.expediente_dgroc = expediente_dgroc;
    }

    public String getExpedienteDgfyco() {
        return expediente_dgfyco;
    }

    public void setExpedienteDgfyco(String expediente_dgfyco) {
        this.expediente_dgfyco = expediente_dgfyco;
    }

    public String getNombreObra() {
        return nombre_obra;
    }

    public void setNombreObra(String nombre_obra) {
        this.nombre_obra = nombre_obra;
    }

    public double getSuperficieM2() {
        return superficie_m2;
    }

    public void setSuperficieM2(double superficie_m2) {
        this.superficie_m2 = superficie_m2;
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

    public LocalDate getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    public List<EmpleadoObra> getListaAsignaciones() {
        return asignaciones;
    }

    public void setListaAsignaciones(ArrayList<EmpleadoObra> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    // obtiene una lista de los empleados actuales de la obra
    public List<Empleado> getEmpleadosActualmenteAsignados() {
        return asignaciones.stream()
            .filter(asignacion -> asignacion.getFechaFin() == null) // Filtrar asignaciones activas
            .map(EmpleadoObra::getEmpleado) // Obtener el empleado de cada asignación
            .collect(Collectors.toList()); // Convertir a lista
    }

    public Sociedad getSociedad() {
        return sociedad;
    }

    public void setSociedad(Sociedad sociedad) {
        this.sociedad = sociedad;
    }

    public TipoObra getTipoObra() {
        return tipo_obra;
    }

    public void setTipoObra(TipoObra tipo_obra) {
        this.tipo_obra = tipo_obra;
    }

    public ArrayList<historialEstadoObra> getHistorialEstadoObra() {
        return historial_estado_obra;
    }

    public void setHistorialEstadoObra(ArrayList<historialEstadoObra> historial_estado_obra) {
        this.historial_estado_obra = historial_estado_obra;
    }

    public ArrayList<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(ArrayList<Plano> planos) {
        this.planos = planos;
    }

    public List<Inspeccion> getInspecciones() {
        return inspecciones;
    }

    public void setInspecciones(ArrayList<Inspeccion> inspecciones) {
        this.inspecciones = inspecciones;
    }

    public ArrayList<HistorialART> getHistorialART() {
        return historialART;
    }

    public void setHistorialART(ArrayList<HistorialART> historialART) {
        this.historialART = historialART;
    }
    
    // Método para añadir un nuevo plano
    public void agregarPlano(Plano plano) {
        plano.setObra(this);
        this.planos.add(plano);
    }
    
    // Método para añadir nueva inspeccion
    public void agregarInspeccion(Inspeccion inspeccion) {
        inspeccion.setObra(this);
        this.inspecciones.add(inspeccion);
    }
    
    // Método para obtener empleados actualmente asignados
    public List<Empleado> getEmpleadosActualmente() {
        return asignaciones.stream()
            .filter(asignacion -> asignacion.getFechaFin() == null) // Filtrar asignaciones activas
            .map(EmpleadoObra::getEmpleado) // Obtener el empleado de cada asignación
            .collect(Collectors.toList()); // Convertir a lista
    }
}
