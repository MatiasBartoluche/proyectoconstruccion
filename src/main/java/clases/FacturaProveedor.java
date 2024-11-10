package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FacturaProveedor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_factura;
    
    private LocalDate fecha_emision;
    private BigDecimal monto;

    public FacturaProveedor() {
    }

    public int getIdFactura() {
        return id_factura;
    }

    public void setIdFactura(int id_factura) {
        this.id_factura = id_factura;
    }

    public LocalDate getFechaEmision() {
        return fecha_emision;
    }

    public void setFechaEmision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    
}
