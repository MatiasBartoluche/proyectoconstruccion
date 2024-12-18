package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sociedad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_sociedad;
    private String nombre;
    private String cuit_sociedad;
    private String razon_social;
    private String telefono;
    private String mail;
    private String calle;
    private int altura;
    private String piso;
    private String localidad;
    //private Provincia provincia;
    
    // apunta al atributo "sociedad" de la clase "seguro"
    @OneToMany(mappedBy = "sociedadSeguro", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ArrayList<Seguro> seguros = new ArrayList<>();
    
    // mappedBy="sociedadObra", relacion bidireccional, apunta al atributo "sociedad" de la clase "Obra"
    @OneToMany(mappedBy = "sociedadObra", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private ArrayList<Obra> obras = new ArrayList<>();
    
    @OneToOne(mappedBy = "sociedadLibroDiario", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private LibroDiario libroDiario;

    public Sociedad() {
    }

    public Sociedad(String cuit_sociedad, String razon_social, String telefono, String mail, String calle, int altura, String localidad, ArrayList<Obra> obras) {
        this.cuit_sociedad = cuit_sociedad;
        this.razon_social = razon_social;
        this.telefono = telefono;
        this.mail = mail;
        this.calle = calle;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return calle;
    }

    public void setCalle(String Calle) {
        this.calle = Calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
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

    public ArrayList<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(ArrayList<Seguro> seguro) {
        this.seguros = seguro;
    }
    
    // Método para añadir una nueva póliza con fecha de contratación
    public void contratarSeguro(Seguro seguro) {
        this.seguros.add(seguro);
    }

    public LibroDiario getLibroDiario() {
        return libroDiario;
    }

    public void setLibroDiario(LibroDiario libroDiario) {
        this.libroDiario = libroDiario;
    }
}
