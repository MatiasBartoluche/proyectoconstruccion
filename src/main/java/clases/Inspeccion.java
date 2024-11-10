package clases;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inspeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private int numero_acta;
    
    private String nombre_inspector;
    private LocalDate fecha_inspeccion;

    public Inspeccion() {
    }

    public int getNumero_acta() {
        return numero_acta;
    }

    public void setNumero_acta(int numero_acta) {
        this.numero_acta = numero_acta;
    }

    public String getNombre_inspector() {
        return nombre_inspector;
    }

    public void setNombre_inspector(String nombre_inspector) {
        this.nombre_inspector = nombre_inspector;
    }

    public LocalDate getFecha_inspeccion() {
        return fecha_inspeccion;
    }

    public void setFecha_inspeccion(LocalDate fecha_inspeccion) {
        this.fecha_inspeccion = fecha_inspeccion;
    }
    
    
}
