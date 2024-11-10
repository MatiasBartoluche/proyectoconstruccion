package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sociedad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_sociedad;
    
    // apunta al atributo "sociedad" de la clase "seguro"
    @OneToMany(mappedBy = "sociedadSeguro", cascade = CascadeType.ALL)
    private ArrayList<Seguro> seguros;
    
    private String cuit_sociedad;
    private String razon_social;
    private String telefono;
    private String mail;
    private String Calle;
    private int altura;
    private String localidad;
    //private Provincia provincia;
    
    // mappedBy="sociedad", relacion bidireccional, apunta al atributo "sociedad" de la clase "Obra"
    @OneToMany(mappedBy = "sociedadObra", cascade = CascadeType.ALL)
    private ArrayList<Obra> obras;

    public Sociedad() {
    }

    public Sociedad(String cuit_sociedad, String razon_social, String telefono, String mail, String Calle, int altura, String localidad, ArrayList<Obra> obras) {
        this.cuit_sociedad = cuit_sociedad;
        this.razon_social = razon_social;
        this.telefono = telefono;
        this.mail = mail;
        this.Calle = Calle;
        this.altura = altura;
        this.localidad = localidad;
        this.obras = obras;
    }

    public int getIdSociedad() {
        return id_sociedad;
    }

    public void setIdSociedad(int id_sociedad) {
        this.id_sociedad = id_sociedad;
    }

    public String getCuitSociedad() {
        return cuit_sociedad;
    }

    public void setCuitSociedad(String cuit_sociedad) {
        this.cuit_sociedad = cuit_sociedad;
    }

    public String getRazonSocial() {
        return razon_social;
    }

    public void setRazonSocial(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public ArrayList<Obra> getObras() {
        return obras;
    }

    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    public ArrayList<Seguro> getSeguro() {
        return seguros;
    }

    public void setSeguro(ArrayList<Seguro> seguro) {
        this.seguros = seguro;
    }
    
    // Método para añadir una nueva póliza con fecha de contratación
    public void contratarSeguro(Seguro poliza, LocalDate fechaContratacion, LocalDate fechaVencimiento) {
        poliza.setSociedad(this);
        poliza.setFechaContratacion(fechaContratacion);
        poliza.setFechaVencimiento(fechaVencimiento);
        this.seguros.add(poliza);
    }
}
