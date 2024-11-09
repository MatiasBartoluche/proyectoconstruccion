package clases;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

public class Obra {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_obra;
    //private Sociedad sociedad;
    private String expediente_dgroc;
    private String expediente_dgfyco;
    private String nombre_obra;
    private double superficie_m2;
    private String calle;
    private int altura;
    private String localidad;
    //private Provincia provincia;
    private LocalDate fecha_inicio;
    //private TipoObra tipo_obra;
    
    // OneToMany representa relacion n-n con actualizacion en cascada
    
    //@OneToMany(mappedBy = "oficina", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //private List<ObraEmpleado> lista_empleados;

    public Obra() {
    }

    public int getIdObra() {
        return id_obra;
    }

    public void setIdObra(int id_obra) {
        this.id_obra = id_obra;
    }

    public String getExpedienteDgroc() {
        return expediente_dgroc;
    }

    public void setExpedienteDgroc(String expediente_dgroc) {
        this.expediente_dgroc = expediente_dgroc;
    }

    public String getExpedienteDgfyco() {
        return expediente_dgfyco;
    }

    public void setExpedienteDgfyco(String expediente_dgfyco) {
        this.expediente_dgfyco = expediente_dgfyco;
    }

    public String getNombreObra() {
        return nombre_obra;
    }

    public void setNombreObra(String nombre_obra) {
        this.nombre_obra = nombre_obra;
    }

    public double getSuperficieM2() {
        return superficie_m2;
    }

    public void setSuperficieM2(double superficie_m2) {
        this.superficie_m2 = superficie_m2;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
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

    public LocalDate getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
}
