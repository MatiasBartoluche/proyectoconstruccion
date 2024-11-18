package clases;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HistorialART implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_historial_art;
    
    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obraART;
    
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleadoART;
    
    private String descripcion;
    
    private LocalDate fecha_Accidente;
    private LocalDate fecha_alta;

    public HistorialART() {
    }

    public int getId_historial_art() {
        return id_historial_art;
    }

    public void setId_historial_art(int id_historial_art) {
        this.id_historial_art = id_historial_art;
    }

    public Obra getObra() {
        return obraART;
    }

    public void setObra(Obra obra) {
        this.obraART = obra;
    }

    public Empleado getEmpleado() {
        return empleadoART;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleadoART = empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_Accidente() {
        return fecha_Accidente;
    }

    public void setFecha_Accidente(LocalDate fecha_Accidente) {
        this.fecha_Accidente = fecha_Accidente;
    }

    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
