package clases;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inspeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private int numero_acta;
    
    @ManyToOne
    @JoinColumn(name = "id_obra", referencedColumnName = "id_obra")
    private Obra obraInspeccion;
    
    private String nombre_inspector;
    private LocalDate fecha_inspeccion;

    public Inspeccion() {
    }

    public int getNumeroActa() {
        return numero_acta;
    }

    public void setNumeroActa(int numero_acta) {
        this.numero_acta = numero_acta;
    }

    public String getNombreInspector() {
        return nombre_inspector;
    }

    public void setNombreInspector(String nombre_inspector) {
        this.nombre_inspector = nombre_inspector;
    }

    public LocalDate getFechaInspeccion() {
        return fecha_inspeccion;
    }

    public void setFechaInspeccion(LocalDate fecha_inspeccion) {
        this.fecha_inspeccion = fecha_inspeccion;
    }

    public Obra getObra() {
        return obraInspeccion;
    }

    public void setObra(Obra obra) {
        this.obraInspeccion = obra;
    }
    
    
}
