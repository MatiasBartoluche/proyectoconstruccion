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
public class Asistencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_asistencia;
    
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleadoAsistencia;
    
    private LocalDate fecha;
    private boolean presente;
    private boolean justificada;
    private String motivo;

    public Asistencia() {
    }

    public int getIdAsistencia() {
        return id_asistencia;
    }

    public void setIdAsistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public boolean isJustificada() {
        return justificada;
    }

    public void setJustificada(boolean justificada) {
        this.justificada = justificada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Empleado getEmpleadoAsistencia() {
        return empleadoAsistencia;
    }

    public void setEmpleadoAsistencia(Empleado empleadoAsistencia) {
        this.empleadoAsistencia = empleadoAsistencia;
    }
}
