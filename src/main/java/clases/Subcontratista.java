package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Subcontratista implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_subcontratista;
    
    private String cuit;
    private String nombre;
    private String descripcion;
    
    // apunta al atributo de tipo "Subcontratista" 
    @OneToMany(mappedBy="subcontratistaFactura", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ArrayList<FacturaSubcontratista> facturas = new ArrayList<>();

    public Subcontratista() {
    }

    public int getIdSubcontratista() {
        return id_subcontratista;
    }

    public void setIdSubcontratista(int id_subcontratista) {
        this.id_subcontratista = id_subcontratista;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<FacturaSubcontratista> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaSubcontratista> facturas) {
        this.facturas = facturas;
    }
}
