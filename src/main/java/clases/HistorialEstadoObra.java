package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HistorialEstadoObra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_historial;
    
    @ManyToOne
    @JoinColumn(name="id_obra", nullable = false)
    private Obra obraHistorial;
    
    @ManyToOne
    @JoinColumn(name="id_estado_obra", nullable = false)
    private EstadoObra estado;
    
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    public HistorialEstadoObra() {
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public Obra getObra() {
        return obraHistorial;
    }

    public void setObra(Obra obra) {
        this.obraHistorial = obra;
    }

    public EstadoObra getEstado() {
        return estado;
    }

    public void setEstado(EstadoObra estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    
}
