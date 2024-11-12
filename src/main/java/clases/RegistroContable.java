package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegistroContable implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_registro_contable;
    
    private LocalDate fecha_registro;
    
    private BigDecimal total_columna_debe;
    private BigDecimal total_columna_haber;

    public RegistroContable() {
    }

    public int getIdRegistroContable() {
        return id_registro_contable;
    }

    public void setIdRegistroContable(int id_registro_contable) {
        this.id_registro_contable = id_registro_contable;
    }

    public LocalDate getFechaRegistro() {
        return fecha_registro;
    }

    public void setFechaRegistro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public BigDecimal getTotalColumnaDebe() {
        return total_columna_debe;
    }

    public void setTotalColumnaDebe(BigDecimal total_columna_debe) {
        this.total_columna_debe = total_columna_debe;
    }

    public BigDecimal getTotalColumnaHaber() {
        return total_columna_haber;
    }

    public void setTotalColumnaHaber(BigDecimal total_columna_haber) {
        this.total_columna_haber = total_columna_haber;
    }
    
    
}
