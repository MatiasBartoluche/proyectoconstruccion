package clasesDTO;

import clases.LibroDiario;
import clases.Obra;
import java.util.ArrayList;

public class SociedadDTO{
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

    private ArrayList<SeguroDTO> segurosDTO = new ArrayList<>();
    
    private ArrayList<Obra> obrasDTO = new ArrayList<>();
    
    private LibroDiario libroDiario;

    public SociedadDTO() {
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
        return obrasDTO;
    }

    public void setObras(ArrayList<Obra> obras) {
        this.obrasDTO = obras;
    }

    public ArrayList<SeguroDTO> getSeguros() {
        return segurosDTO;
    }

    public void setSeguros(ArrayList<SeguroDTO> seguro) {
        this.segurosDTO = seguro;
    }
    
    // Método para añadir una nueva póliza con fecha de contratación
    public void contratarSeguro(SeguroDTO seguro) {
        this.segurosDTO.add(seguro);
    }

    public LibroDiario getLibroDiario() {
        return libroDiario;
    }

    public void setLibroDiario(LibroDiario libroDiario) {
        this.libroDiario = libroDiario;
    }
}

