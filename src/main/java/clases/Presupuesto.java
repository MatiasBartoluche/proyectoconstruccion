package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Presupuesto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_presupuesto;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal porcentajeCAC;
    
    private LocalDate fechaVersion;
    
    @OneToMany(mappedBy = "presupuestoRubto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Rubro> rubros = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_obra", nullable = false)
    private Obra obraPresupuesto;
    
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

    public ArrayList<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(ArrayList<Rubro> rubros) {
        this.rubros = rubros;
    }

    public Obra getObraPresupuesto() {
        return obraPresupuesto;
    }

    public void setObraPresupuesto(Obra obraPresupuesto) {
        this.obraPresupuesto = obraPresupuesto;
    }
}
