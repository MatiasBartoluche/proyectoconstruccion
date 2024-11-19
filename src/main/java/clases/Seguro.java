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
public class Seguro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_seguro;
    
    private int numero_poliza;
    LocalDate fecha_contratacion;
    LocalDate fecha_vencimiento;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_sociedad", referencedColumnName = "id_sociedad")
    private Sociedad sociedadSeguro;

    public Seguro() {
    }

    public int getIdSeguro() {
        return id_seguro;
    }

    public void setIdSeguro(int id_seguro) {
        this.id_seguro = id_seguro;
    }

    public int getNumeroPoliza() {
        return numero_poliza;
    }

    public void setNumeroPoliza(int numero_poliza) {
        this.numero_poliza = numero_poliza;
    }

    public LocalDate getFechaContratacion() {
        return fecha_contratacion;
    }

    public void setFechaContratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public LocalDate getFechaVencimiento() {
        return fecha_vencimiento;
    }

    public void setFechaVencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Sociedad getSociedad() {
        return sociedadSeguro;
    }

    public void setSociedad(Sociedad sociedad) {
        this.sociedadSeguro = sociedad;
    }
}
