package clases;

import java.util.ArrayList;
import persistencia.ControladorPersistenciaSociedades;

public class ControladorSociedades {
    
    ControladorPersistenciaSociedades controlPersistSoc = new ControladorPersistenciaSociedades();
    
    public void crearSociedad(Sociedad sociedad){
        controlPersistSoc.createSociedad(sociedad);
    }
    
    public void eliminarSociedad(int idSociedad){
        controlPersistSoc.deleteSociedad(idSociedad);
    }
    
    public void editarContrato(Sociedad sociedad){
        controlPersistSoc.editSociedad(sociedad);
    }
    
    public Sociedad buscarSociedad(int idSociedad){
        return controlPersistSoc.findSociedad(idSociedad);
    }
    
    public ArrayList<Sociedad> buscarListaSociedades(){
        return controlPersistSoc.fintListaSociedades();
    }
}
