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
public class Proveedor implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_proveedor;
    
    private String cuit;
    private String nombre;
    
    @OneToMany(mappedBy="proveedor", cascade = CascadeType.ALL)
    private ArrayList<FacturaProveedor> facturas;

    public Proveedor() {
    }

    public int getIdProveedor() {
        return id_proveedor;
    }

    public void setIdProveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public ArrayList<FacturaProveedor> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaProveedor> facturas) {
        this.facturas = facturas;
    }
}
