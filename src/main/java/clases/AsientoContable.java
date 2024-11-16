package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AsientoContable implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_asiento;
    
    private String descripcion;
    
    @Column(precision = 20, scale = 10)
    private BigDecimal monto;
    
    @Enumerated(EnumType.STRING)
    private TipoAsiento tipo;
    
    @Enumerated(EnumType.STRING)
    private Columna columna;
    
    @ManyToOne
    @JoinColumn(name = "id_registro_contable")
    private RegistroContable registroContable;

    public AsientoContable() {
    }

    public int getId_asiento() {
        return id_asiento;
    }

    public void setId_asiento(int id_asiento) {
        this.id_asiento = id_asiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoAsiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsiento tipo) {
        this.tipo = tipo;
    }

    public Columna getColumna() {
        return columna;
    }

    public void setColumna(Columna columna) {
        this.columna = columna;
    }

    public RegistroContable getRegistroContable() {
        return registroContable;
    }

    public void setRegistroContable(RegistroContable registroContable) {
        this.registroContable = registroContable;
    }
    
}
