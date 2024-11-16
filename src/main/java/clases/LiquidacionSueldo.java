package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LiquidacionSueldo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_liquidacion;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "grupo_aportes_id") // Define la columna en la tabla LiquidarSueldo que almacena el ID de GrupoAportes
    private GrupoAportes grupoAportes;
    
    @OneToOne
    @JoinColumn(name = "legajo")
    private Empleado empleadoLiquidacion;
    
    @Column(precision = 14, scale = 7)
    private BigDecimal sueldo_base;
    
    private int antiguedad;
    
    private LocalDate fecha_emision;
    private LocalDate fecha_pago;
    private LocalDate fecha_deposito_jubilacion;

    public LiquidacionSueldo() {
    }

    public int getId_liquidacion() {
        return id_liquidacion;
    }

    public void setId_liquidacion(int id_liquidacion) {
        this.id_liquidacion = id_liquidacion;
    }

    public GrupoAportes getGrupoAportes() {
        return grupoAportes;
    }

    public void setGrupoAportes(GrupoAportes grupoAportes) {
        this.grupoAportes = grupoAportes;
    }

    public Empleado getEmpleado() {
        return empleadoLiquidacion;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleadoLiquidacion = empleado;
    }

    public BigDecimal getSueldo_base() {
        return sueldo_base;
    }

    public void setSueldo_base(BigDecimal sueldo_base) {
        this.sueldo_base = sueldo_base;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public LocalDate getFecha_deposito_jubilacion() {
        return fecha_deposito_jubilacion;
    }

    public void setFecha_deposito_jubilacion(LocalDate fecha_deposito_jubilacion) {
        this.fecha_deposito_jubilacion = fecha_deposito_jubilacion;
    }
    
    
}
