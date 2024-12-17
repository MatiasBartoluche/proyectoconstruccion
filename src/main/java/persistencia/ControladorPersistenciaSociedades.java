package persistencia;

import clases.Seguro;
import clases.Sociedad;
import clasesDTO.SeguroDTO;
import clasesDTO.SociedadDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistenciaSociedades {
    SociedadJpaController sociedadJpa = new SociedadJpaController();
    SeguroJpaController seguroJpa = new SeguroJpaController();
    
    // -------------------------- metodos para sociedades
    public void createSociedad(Sociedad sociedad){
        sociedadJpa.create(sociedad);
    }

    public void deleteSociedad(int idSociedad) {
        sociedadJpa.destroy(idSociedad);
    }

    public void editSociedad(Sociedad sociedad) {
        try{
            sociedadJpa.edit(sociedad);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistenciaSociedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Sociedad findSociedad(int idSociedad) {
        return sociedadJpa.findSociedad(idSociedad);
    }

    public ArrayList<Sociedad> fintListaSociedades() {
        List<Sociedad> lista = sociedadJpa.findSociedadEntities();
        ArrayList<Sociedad> listaSociedades = new ArrayList<>(lista);
        return listaSociedades;
    }
    
    public SociedadDTO convertirSociedadDTO(Sociedad sociedad){
        return sociedadJpa.convertirASociedadDTO(sociedad);
    }
    
    public ArrayList<SociedadDTO> convertListaASociedadDTO(List<Sociedad> sociedades){
        List<SociedadDTO> lista = sociedadJpa.convertirListaASociedadDTO(sociedades);
        ArrayList<SociedadDTO> sociedadesDTO = new ArrayList<>(lista);
        return sociedadesDTO;
    }
    
    //-------------------------------------- metodos para seguros
    public void createSeguro(Seguro seguro){
        seguroJpa.create(seguro);
    }

    public void deleteSeguro(int idSeguro) {
        seguroJpa.destroy(idSeguro);
    }

    public void editSeguro(Seguro seguro) {
        try{
            seguroJpa.edit(seguro);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistenciaSociedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Seguro findSeguro(int idSeguro) {
        return seguroJpa.findSeguro(idSeguro);
    }

    public ArrayList<Seguro> fintListaSeguros() {
        List<Seguro> lista = seguroJpa.findSeguroEntities();
        ArrayList<Seguro> listaSeguros = new ArrayList<>(lista);
        return listaSeguros;
    }
    
    public SeguroDTO convertirASeguroDTO(Seguro seguro){
        return seguroJpa.convertirASeguroDTO(seguro);
    }
    
    public ArrayList<SeguroDTO> convertListaASegurooDTO(List<Seguro> seguros){
        List<SeguroDTO> lista = seguroJpa.convertirListaASeguroDTO(seguros);
        ArrayList<SeguroDTO> segurosDTO = new ArrayList<>(lista);
        return segurosDTO;
    }
}
