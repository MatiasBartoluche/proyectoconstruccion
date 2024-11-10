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
public class Seguro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_seguro;
    
    private int numero_poliza;
    LocalDate fecha_contratacion;
    LocalDate fecha_vencimiento;
    
    /*@ManyToOne
    @JoinColumn(name = "id_sociedad", referencedColumnName = "id_sociedad")
    private Sociedad sociedad;*/

    public Seguro() {
    }

    public int getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(int id_seguro) {
        this.id_seguro = id_seguro;
    }

    public int getNumero_poliza() {
        return numero_poliza;
    }

    public void setNumero_poliza(int numero_poliza) {
        this.numero_poliza = numero_poliza;
    }

    public LocalDate getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
    
    
}
