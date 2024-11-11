package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rubro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_rubro;
    
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "rubro_padre_id")
    private Rubro rubroPadre;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal presupuesto;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal adelanto;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal saldoDescuento;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal presupuestoCAC;
    
    
    // float para porcentajes chicos
    private float porcentajeAvance;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal cert1;

    public Rubro() {
    }

    public Rubro(String nombre, BigDecimal presupuesto) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    public int getId_rubro() {
        return id_rubro;
    }

    public void setId_rubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public BigDecimal getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(BigDecimal adelanto) {
        this.adelanto = adelanto;
    }

    public BigDecimal getSaldoDescuento() {
        return saldoDescuento;
    }

    public void setSaldoDescuento(BigDecimal saldoDescuento) {
        this.saldoDescuento = saldoDescuento;
    }

    public BigDecimal getPresupuestoCAC() {
        return presupuestoCAC;
    }

    public void setPresupuestoCAC(BigDecimal presupuestoCAC) {
        this.presupuestoCAC = presupuestoCAC;
    }

    public float getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(float porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public BigDecimal getCert1() {
        return cert1;
    }

    public void setCert1(BigDecimal cert1) {
        this.cert1 = cert1;
    }

    public Rubro getRubroPadre() {
        return rubroPadre;
    }

    public void setRubroPadre(Rubro rubroPadre) {
        this.rubroPadre = rubroPadre;
    }

}
