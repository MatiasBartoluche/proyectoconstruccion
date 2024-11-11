package clases;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Rubro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_rubro;
    
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "rubro_padre_id")
    private Rubro rubroPadre;
    
    @OneToMany(mappedBy = "rubroPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Rubro> subRubros = new ArrayList<>();
    
    @Column(precision = 20, scale = 10)
    private BigDecimal presupuesto;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal adelanto;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal saldo;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal saldoCAC;
    
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

    public int getIdRubro() {
        return id_rubro;
    }

    public void setIdRubro(int id_rubro) {
        this.id_rubro = id_rubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Rubro getRubroPadre() {
        return rubroPadre;
    }

    public ArrayList<Rubro> getSubRubros() {
        return subRubros;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public BigDecimal getAdelanto() {
        return adelanto;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getSaldoCAC() {
        return saldoCAC;
    }

    public float getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public BigDecimal getCert1() {
        return cert1;
    }

    public void setRubroPadre(Rubro rubroPadre) {
        this.rubroPadre = rubroPadre;
    }

    public void setSubRubros(ArrayList<Rubro> subRubros) {
        this.subRubros = subRubros;
    }

    public void setPresupuesto(BigDecimal presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setAdelanto(BigDecimal adelanto) {
        this.adelanto = adelanto;
    }

    public void setSaldo(BigDecimal saldoDescuento) {
        this.saldo = saldoDescuento;
    }

    public void setSaldoCAC(BigDecimal saldoCAC) {
        this.saldoCAC = saldoCAC;
    }

    public void setPorcentajeAvance(float porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public void setCert1(BigDecimal cert1) {
        this.cert1 = cert1;
    }


}
