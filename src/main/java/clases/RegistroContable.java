package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RegistroContable implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_registro_contable;
    
    @ManyToOne
    @JoinColumn(name = "id_libor_diario")
    private LibroDiario libroDiario;
    
    private LocalDate fecha_registro;
    
    @OneToMany(mappedBy = "registroContable", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ArrayList<AsientoContable> asientos = new ArrayList<>();
    
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

    public ArrayList<AsientoContable> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<AsientoContable> asientos) {
        this.asientos = asientos;
    }

    public LibroDiario getLibroDiario() {
        return libroDiario;
    }

    public void setLibroDiario(LibroDiario libroDiario) {
        this.libroDiario = libroDiario;
    }
}
