package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aporte implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_aporte;
    
    private String detalle_aporte;
    private float porcentaje;
    
    // 0 = aporte de descuento, 1 = aporte de beneficio
    private int tipo_aporte;

    public Aporte() {
    }

    public int getId_aporte() {
        return id_aporte;
    }

    public void setId_aporte(int id_aporte) {
        this.id_aporte = id_aporte;
    }

    public String getDetalle_aporte() {
        return detalle_aporte;
    }

    public void setDetalle_aporte(String detalle_aporte) {
        this.detalle_aporte = detalle_aporte;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getTipo_aporte() {
        return tipo_aporte;
    }

    public void setTipo_aporte(int tipo_aporte) {
        this.tipo_aporte = tipo_aporte;
    }
}
