package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EntregaEPP implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_entrega;
    
    @ManyToOne
    @JoinColumn(name = "legajo", referencedColumnName = "legajo")
    private Empleado empleadoEPP;
    
    private Obra obraEPP;
    
    @OneToMany
    @JoinColumn(name = "id_epp") // Columna en la tabla EPP para almacenar el ID de EntregaEPP
    private ArrayList<EPP> lista_epp = new ArrayList<>();
    
    
    // lista del modelo de cada EPP
    private ArrayList<String> modelos = new ArrayList<>();
    // lista de la marca de cada EPP
    private ArrayList<String> marcas = new ArrayList<>();
    // lista boolean, true=epp certificado, false no certificado
    private ArrayList<Boolean> certificaciones = new ArrayList<>();
    // lista de fecha de entrega de cada EPP
    private ArrayList<LocalDate> fecha_entrega = new ArrayList<>();

    public EntregaEPP() {
    }

    public int getIdEntrega() {
        return id_entrega;
    }

    public void setIdEntrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public ArrayList<String> getModelos() {
        return modelos;
    }

    public void setModelos(ArrayList<String> modelos) {
        this.modelos = modelos;
    }

    public ArrayList<String> getMarcas() {
        return marcas;
    }

    public void setMarcas(ArrayList<String> marcas) {
        this.marcas = marcas;
    }

    public ArrayList<Boolean> getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(ArrayList<Boolean> certificaciones) {
        this.certificaciones = certificaciones;
    }

    public ArrayList<LocalDate> getFechaEntrega() {
        return fecha_entrega;
    }

    public void setFechaEntrega(ArrayList<LocalDate> fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Empleado getEmpleadoEPP() {
        return empleadoEPP;
    }

    public void setEmpleadoEPP(Empleado empleadoEPP) {
        this.empleadoEPP = empleadoEPP;
    }

    public ArrayList<EPP> getLista_epp() {
        return lista_epp;
    }

    public void setLista_epp(ArrayList<EPP> lista_epp) {
        this.lista_epp = lista_epp;
    }
    
    
}
