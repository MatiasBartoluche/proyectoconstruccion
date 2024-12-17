package clases;

import clasesDTO.SeguroDTO;
import clasesDTO.SociedadDTO;
import java.util.ArrayList;
import persistencia.ControladorPersistenciaSociedades;

public class ControladorSociedades {
    
    ControladorPersistenciaSociedades controlPersistSoc = new ControladorPersistenciaSociedades();

    // --------------------- metodos para sociedades
    public void crearSociedad(Sociedad sociedad){
        controlPersistSoc.createSociedad(sociedad);
    }
    
    public void eliminarSociedad(int idSociedad){
        controlPersistSoc.deleteSociedad(idSociedad);
    }
    
    public void editarSociedad(Sociedad sociedad){
        controlPersistSoc.editSociedad(sociedad);
    }
    
    public Sociedad buscarSociedad(int idSociedad){
        return controlPersistSoc.findSociedad(idSociedad);
    }
    
    public ArrayList<Sociedad> buscarListaSociedades(){
        return controlPersistSoc.fintListaSociedades();
    }
    
    public SociedadDTO convertirSociedadDTO(Sociedad sociedad){
        return controlPersistSoc.convertirSociedadDTO(sociedad);
    }
    
    public ArrayList<SociedadDTO> convertirListaASociedadesDTO(ArrayList<Sociedad> sociedades){
        return controlPersistSoc.convertListaASociedadDTO(sociedades);
    }
    
    // ------------------------- metodos para seguros
    public void crearSeguro(Seguro seguro){
        controlPersistSoc.createSeguro(seguro);
    }
    
    public void eliminarSeguro(int idSeguro){
        controlPersistSoc.deleteSeguro(idSeguro);
    }
    
    public void editarSeguro(Seguro seguro){
        controlPersistSoc.editSeguro(seguro);
    }
    
    public Seguro buscarSeguro(int idSeguro){
        return controlPersistSoc.findSeguro(idSeguro);
    }
    
    public ArrayList<Seguro> buscarListaSeguros(){
        return controlPersistSoc.fintListaSeguros();
    }
    
    public SeguroDTO convertirSeguroDTO(Seguro seguro){
        return controlPersistSoc.convertirASeguroDTO(seguro);
    }
    
    public ArrayList<SeguroDTO> convertitListaSegurosDTO(ArrayList<Seguro> seguros){
        return controlPersistSoc.convertListaASegurooDTO(seguros);
    }
}
