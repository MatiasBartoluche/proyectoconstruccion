package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FacturaSubcontratista implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_subcontratista;
    
    private int numero_factura;
    private LocalDate fecha_emision;
    
    @Column(precision = 14, scale = 7)
    private BigDecimal monto;
    
    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false) // apunto al id de la clase a relacionar
    private Subcontratista subcontratista;

    public FacturaSubcontratista() {
    }

    public int getId_subcontratista() {
        return id_subcontratista;
    }

    public void setId_subcontratista(int id_subcontratista) {
        this.id_subcontratista = id_subcontratista;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    
}
