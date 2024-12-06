package persistencia;

import clases.Contrato;
import clases.Empleado;
import clases.EstadoEmpleado;
import clases.GrupoTrabajo;
import clasesDTO.GrupoTrabajoDTO;
import clases.Jerarquia;
import clases.Rol;
import clases.Usuario;
import clasesDTO.EmpleadoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistencia {
    
    // creando instancias de cada JpaController
    
    ContratoJpaController contratoJpa = new ContratoJpaController();
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    EstadoEmpleadoJpaController estadoJpa = new EstadoEmpleadoJpaController();
    GrupoTrabajoJpaController grupoJpa = new GrupoTrabajoJpaController();
    JerarquiaJpaController jerarquiaJpa = new JerarquiaJpaController();
    RolJpaController rolJpa = new RolJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    
    // ############################ Creando metodos para ContratoJpaController ############################
    
    public void createContrato(Contrato contrato) {
        contratoJpa.create(contrato);
    }

    public void deleteContrato(int idContrato) {
        contratoJpa.destroy(idContrato);
    }

    public void editContrato(Contrato contrato) {
        try{
            contratoJpa.edit(contrato);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Contrato findContrato(int idContrato) {
        return contratoJpa.findContrato(idContrato);
    }

    public ArrayList<Contrato> fintListaContratos() {
        List<Contrato> lista = contratoJpa.findContratoEntities();
        ArrayList<Contrato> listaContratos = new ArrayList<>(lista);
        return listaContratos;
    }
    
    // ############################ creando metodos para EmpleadoJpaController ############################
    
    public void createEmpleado(Empleado empleado) {
        empleadoJpa.create(empleado);
    }

    public void deleteEmpleado(int idEmpleado) {
        empleadoJpa.destroy(idEmpleado);
    }

    public void editEmpleado(Empleado empleado) {
        try{
            empleadoJpa.edit(empleado);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado findEmpleado(int idEmpleado) {
        return empleadoJpa.findEmpleado(idEmpleado);
    }

    public ArrayList<Empleado> findListaEmpleado() {
        List<Empleado> lista = empleadoJpa.findEmpleadoEntities();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>(lista);
        return listaEmpleados;
    }
    
    public Empleado findEmpleadoByLegajo(int Legajo){
        return empleadoJpa.findEmpleadoByLegajo(Legajo);
    }
    
    public ArrayList<Empleado> findEmpleadosByAttributes(Map<String, Object> parametros){
        List<Empleado> lista = empleadoJpa.findEmpleadosByAttributes(parametros);
        ArrayList<Empleado> listaEmpleados = new ArrayList<>(lista); 
        return listaEmpleados;
    }
    
    public ArrayList<Empleado> findEmpleadoByGroup(int idGrupo, String nombreGrupo){
        List<Empleado> lista = empleadoJpa.findEmpleadosByGroup(idGrupo, nombreGrupo);
        ArrayList<Empleado> listaEmpleados = new ArrayList<>(lista);
        return listaEmpleados;
    }
    
    public EmpleadoDTO convertAEmpleadoDTO(Empleado emp){
        return empleadoJpa.convertirAEmpleadoDTO(emp);
    }
    
    public ArrayList<EmpleadoDTO> convertListaAEmpleadoDTO(List<Empleado> empleados){
        List<EmpleadoDTO> lista = empleadoJpa.convertirListaAEmpleadoDTO(empleados);
        ArrayList<EmpleadoDTO> empleadosDTO = new ArrayList<>(lista);
        return empleadosDTO;
    }
    
    public ArrayList<Empleado> findByDescripcionJerarquia(String descripcion){
        List<Empleado> lista = empleadoJpa.findByDescripcionJerarquia(descripcion);
        ArrayList<Empleado> empleadosJerarquia= new ArrayList<>(lista);
        return empleadosJerarquia;
    }
    
    public ArrayList<Empleado> findByJerarquiaContrato(String descJerarquia, boolean jerarquia, String descContrato, boolean contrato){
        List<Empleado> lista = empleadoJpa.findEmpleadosByJerarquiaContrato(descJerarquia, jerarquia, descContrato, contrato);
        ArrayList<Empleado> listaEmpleados = new ArrayList<>(lista);
        return listaEmpleados;
    }
    
    // ###################### creando metodos para EstadoEmpleadoJpaController ###################################
   
    public void creteEstadoEmpleado(EstadoEmpleado estado) {
        estadoJpa.create(estado);
    }

    public void deleteEstadoEmpleado(int idEstado) {
        estadoJpa.destroy(idEstado);
    }

    public void editEstadoEmpleado(EstadoEmpleado estado) {
        try{
            estadoJpa.edit(estado);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EstadoEmpleado findEstadoEmpleado(int idEstado) {
        return estadoJpa.findEstado(idEstado);
    }

    public ArrayList<EstadoEmpleado> findListaEstados() {
        List<EstadoEmpleado> lista = estadoJpa.findEstadoEntities();
        ArrayList<EstadoEmpleado> listaEstados = new ArrayList<>(lista);
        return listaEstados;
    }
    
    // ###################### creando metodos para GrupoTrabajoJpaController #############################
    
    public void createGrupo(GrupoTrabajo grupo) {
        grupoJpa.create(grupo);
    }

    public void deleteGrupo(int idGrupo) {
        grupoJpa.destroy(idGrupo);
    }

    public void editGrupo(GrupoTrabajo grupo) {
        try{
            grupoJpa.edit(grupo);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GrupoTrabajo findGrupo(int idGrupo) {
        return grupoJpa.findGrupoTrabajo(idGrupo);
    }

    public ArrayList<GrupoTrabajo> findListaGruposTrabajo() {
        List<GrupoTrabajo> lista = grupoJpa.findGrupoTrabajoEntities();
        
        ArrayList<GrupoTrabajo> listaGrupos = new ArrayList<>(lista);
        
        return listaGrupos;
    }
    
    public GrupoTrabajoDTO convertGrupoTrabajoDTO(GrupoTrabajo grupo){
        return grupoJpa.convertirGrupoTrabajoDTO(grupo);
    }
    
    public ArrayList<GrupoTrabajoDTO> convertListaGrupoTrabajoDTO(List<GrupoTrabajo> grupos){
        List<GrupoTrabajoDTO> lista = grupoJpa.convertirListaGrupoTrabajoDTO(grupos);
        ArrayList<GrupoTrabajoDTO> gruposDTO = new ArrayList<>(lista);
        return gruposDTO;
    }
    
    // ############################## creando metodos para JerarquiaJpaController ########################
    
    public void createJerarquia(Jerarquia jerarquia) {
        jerarquiaJpa.create(jerarquia);
    }

    public void deleteJerarquia(int idJerarquia) {
        jerarquiaJpa.destroy(idJerarquia);
    }

    public void editJerarquia(Jerarquia jerarquia) {
        try{
            jerarquiaJpa.edit(jerarquia);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Jerarquia findJerarquia(int idJerarquia) {
        return jerarquiaJpa.findJerarquia(idJerarquia);
    }

    public ArrayList<Jerarquia> findListaJerarquias() {
        List<Jerarquia> lista = jerarquiaJpa.findJerarquiaEntities();
        
        ArrayList<Jerarquia> listaJerarquias = new ArrayList<>(lista);
        
        return listaJerarquias;
    }
    
    // ################################# creando metodos para RolJpaController ###########################

    public void createRol(Rol rol) {
        rolJpa.create(rol);
    }
    
    public void deleteRol(int idRol) {
        rolJpa.destroy(idRol);
    }

    public void editRol(Rol rol) {
        try{
            rolJpa.edit(rol);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Rol findRol(int idRol) {
        return rolJpa.findRol(idRol);
    }

    public ArrayList<Rol> findListaRoles() {
        List<Rol> lista = rolJpa.findRolEntities();
        
        ArrayList<Rol> listaRoles = new ArrayList<>(lista);
        
        return listaRoles;
    }

    // ############################# creando metodos para UsuarioJpaController #############################

    public void createUsuario(Usuario usuario) {
        usuarioJpa.create(usuario);
    }

    public void deleteUsuario(int idUsuario) {
        usuarioJpa.destroy(idUsuario);
    }

    public void editUsuario(Usuario usuario) {
        try{
            usuarioJpa.edit(usuario);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario findUsuario(int idUsuario) {
        return usuarioJpa.findUsuario(idUsuario);
    }

    public ArrayList<Usuario> findListaUsuarios() {
        List<Usuario> lista = usuarioJpa.findUsuarioEntities();
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>(lista);
        
        return listaUsuarios;
    }


}
