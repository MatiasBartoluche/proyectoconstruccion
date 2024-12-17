package clasesDTO;

import java.time.LocalDate;

public class SeguroDTO {
    private int id_seguro;
    private String nombre;
    private String cuit;
    private int numero_poliza;
    LocalDate fecha_contratacion;
    LocalDate fecha_vencimiento;
    private SociedadDTO sociedadSeguro;

    public SeguroDTO() {
    }

    public int getIdSeguro() {
        return id_seguro;
    }

    public void setIdSeguro(int id_seguro) {
        this.id_seguro = id_seguro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public int getNumeroPoliza() {
        return numero_poliza;
    }

    public void setNumeroPoliza(int numero_poliza) {
        this.numero_poliza = numero_poliza;
    }

    public LocalDate getFechaContratacion() {
        return fecha_contratacion;
    }

    public void setFechaContratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public LocalDate getFechaVencimiento() {
        return fecha_vencimiento;
    }

    public void setFechaVencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public SociedadDTO getSociedad() {
        return sociedadSeguro;
    }

    public void setSociedad(SociedadDTO sociedadDTO) {
        this.sociedadSeguro = sociedadDTO;
    }
}
