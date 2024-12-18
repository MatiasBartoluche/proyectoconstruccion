package persistencia;

import clases.Obra;
import clases.TipoObra;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistenciaObras {
    ObraJpaController obraJpa = new ObraJpaController();
    TipoObraJpaController tipoObraJpa = new TipoObraJpaController();
    
    // -------------------------- metodos para TipoObra
    public void createTipoObra(TipoObra tipo){
        tipoObraJpa.create(tipo);
    }

    public void deleteTipoObra(int idTipo) {
        tipoObraJpa.destroy(idTipo);
    }

    public void editTipoObra(TipoObra tipo) {
        try{
            tipoObraJpa.edit(tipo);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistenciaObras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoObra findTipoObra(int idTipo) {
        return tipoObraJpa.findTipoObra(idTipo);
    }

    public ArrayList<TipoObra> fintListaTipoObra() {
        List<TipoObra> lista = tipoObraJpa.findTipoObraEntities();
        ArrayList<TipoObra> listaTipoObra = new ArrayList<>(lista);
        return listaTipoObra;
    }
    
    /*public SociedadDTO convertirSociedadDTO(Sociedad sociedad){
        return sociedadJpa.convertirASociedadDTO(sociedad);
    }
    
    public ArrayList<SociedadDTO> convertListaASociedadDTO(List<Sociedad> sociedades){
        List<SociedadDTO> lista = sociedadJpa.convertirListaASociedadDTO(sociedades);
        ArrayList<SociedadDTO> sociedadesDTO = new ArrayList<>(lista);
        return sociedadesDTO;
    }*/
    
    //-------------------------------------- metodos para obras
    public void createObra(Obra obra){
        obraJpa.create(obra);
    }

    public void deleteObra(int idObra) {
        obraJpa.destroy(idObra);
    }

    public void editObra(Obra obra) {
        try{
            obraJpa.edit(obra);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistenciaObras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Obra findObra(int idObra) {
        return obraJpa.findObra(idObra);
    }

    public ArrayList<Obra> fintListaObras() {
        List<Obra> lista = obraJpa.findObraEntities();
        ArrayList<Obra> listaObra = new ArrayList<>(lista);
        return listaObra;
    }
}
