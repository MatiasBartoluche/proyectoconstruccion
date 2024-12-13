package persistencia;

import clases.Sociedad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistenciaSociedades {
    SociedadJpaController sociedadJpa = new SociedadJpaController();
    
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
}
