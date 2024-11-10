package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plano implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_plano;
    
    private String directorio;
    private String descripcion;
    private double superficiem2;

    public Plano() {
    }

    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSuperficiem2() {
        return superficiem2;
    }

    public void setSuperficiem2(double superficiem2) {
        this.superficiem2 = superficiem2;
    }
    
    
}
