package clases;

import clasesDTO.SeguroDTO;
import clasesDTO.SociedadDTO;
import java.util.ArrayList;
import persistencia.ControladorPersistenciaObras;

public class ControladorObras {
    
    ControladorPersistenciaObras controlPersistObras = new ControladorPersistenciaObras();

    // --------------------- metodos para TipoObra
    public void crearTipoObra(TipoObra tipo){
        controlPersistObras.createTipoObra(tipo);
    }
    
    public void eliminarTipoObra(int idTipo){
        controlPersistObras.deleteTipoObra(idTipo);
    }
    
    public void editarTipoObra(TipoObra tipo){
        controlPersistObras.editTipoObra(tipo);
    }
    
    public TipoObra buscarTipoObra(int idTipo){
        return controlPersistObras.findTipoObra(idTipo);
    }
    
    public ArrayList<TipoObra> buscarListaTipoObra(){
        return controlPersistObras.fintListaTipoObra();
    }
    
    /*public SociedadDTO convertirSociedadDTO(Sociedad sociedad){
        return controlPersistSoc.convertirSociedadDTO(sociedad);
    }
    
    public ArrayList<SociedadDTO> convertirListaASociedadesDTO(ArrayList<Sociedad> sociedades){
        return controlPersistSoc.convertListaASociedadDTO(sociedades);
    }*/
    
    // ------------------------- metodos para obras
    public void crearObra(Obra obra){
        controlPersistObras.createObra(obra);
    }
    
    public void eliminarObra(int idObra){
        controlPersistObras.deleteObra(idObra);
    }
    
    public void editarObra(Obra obra){
        controlPersistObras.editObra(obra);
    }
    
    public Obra buscarObra(int idObra){
        return controlPersistObras.findObra(idObra);
    }
    
    public ArrayList<Obra> buscarListaObras(){
        return controlPersistObras.fintListaObras();
    }
}
