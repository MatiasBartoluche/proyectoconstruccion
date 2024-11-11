package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Presupuesto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_presupuesto;
    
    @Column(precision = 12, scale = 10)
    private BigDecimal porcentajeCAC;
    
    private LocalDate fechaVersion;
    
    

    public Presupuesto() {
    }

    public int getIdPresupuesto() {
        return id_presupuesto;
    }

    public void setIdPresupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public BigDecimal getPorcentajeCAC() {
        return porcentajeCAC;
    }

    public void setPorcentajeCAC(BigDecimal porcentajeCAC) {
        this.porcentajeCAC = porcentajeCAC;
    }

    public LocalDate getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(LocalDate fechaVersion) {
        this.fechaVersion = fechaVersion;
    }
}
