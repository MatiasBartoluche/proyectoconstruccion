package clases;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmpleadoObra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleadoObra;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obraEmpleado;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public EmpleadoObra() {
    }

    public EmpleadoObra(int id, Empleado empleado, Obra obra, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.empleadoObra = empleado;
        this.obraEmpleado = obra;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleadoObra;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleadoObra = empleado;
    }

    public Obra getObra() {
        return obraEmpleado;
    }

    public void setObra(Obra obra) {
        this.obraEmpleado = obra;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
