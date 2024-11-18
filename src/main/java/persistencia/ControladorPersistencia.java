package persistencia;

import clases.Contrato;
import clases.Empleado;
import clases.EstadoEmpleado;
import clases.GrupoTrabajo;
import clases.Jerarquia;
import clases.Rol;
import clases.Usuario;
import java.util.ArrayList;
import java.util.List;
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
    
    public void crearContrato(Contrato contrato) {
        contratoJpa.create(contrato);
    }

    public void eliminarContrato(int idContrato) {
        contratoJpa.destroy(idContrato);
    }

    public void editarContrato(Contrato contrato) {
        try{
            contratoJpa.edit(contrato);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Contrato traerContrato(int idContrato) {
        return contratoJpa.findContrato(idContrato);
    }

    public ArrayList<Contrato> traerListaContratos() {
        List<Contrato> lista = contratoJpa.findContratoEntities();
        
        ArrayList<Contrato> listaContratos = new ArrayList<>(lista);
        
        return listaContratos;
    }
    
    // ############################ creando metodos para EmpleadoJpaController ############################
    
    public void crearEmpleado(Empleado empleado) {
        empleadoJpa.create(empleado);
    }

    public void eliminarEmpleado(int idEmpleado) {
        empleadoJpa.destroy(idEmpleado);
    }

    public void editarEmpleado(Empleado empleado) {
        try{
            empleadoJpa.edit(empleado);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado traerEmpleado(int idEmpleado) {
        return empleadoJpa.findEmpleado(idEmpleado);
    }

    public ArrayList<Empleado> traerListaEmpleado() {
        List<Empleado> lista = empleadoJpa.findEmpleadoEntities();
        
        ArrayList<Empleado> listaEmpleados = new ArrayList<>(lista);
        
        return listaEmpleados;
    }
    
    public Empleado findEmpleadoByLegajo(int Legajo){
        return empleadoJpa.findEmpleadoByLegajo(Legajo);
    }
    
    // ###################### creando metodos para EstadoEmpleadoJpaController ###################################
   
    public void crearEstadoEmpleado(EstadoEmpleado estado) {
        estadoJpa.create(estado);
    }

    public void eliminarEstadoEmpleado(int idEstado) {
        estadoJpa.destroy(idEstado);
    }

    public void editarEstadoEmpleado(EstadoEmpleado estado) {
        try{
            estadoJpa.edit(estado);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EstadoEmpleado traerEstadoEmpleado(int idEstado) {
        return estadoJpa.findEstado(idEstado);
    }

    public ArrayList<EstadoEmpleado> traerListaEstados() {
        List<EstadoEmpleado> lista = estadoJpa.findEstadoEntities();
        
        ArrayList<EstadoEmpleado> listaEstados = new ArrayList<>(lista);
        
        return listaEstados;
    }
    
    // ###################### creando metodos para GrupoTrabajoJpaController #############################
    
    public void crearGrupo(GrupoTrabajo grupo) {
        grupoJpa.create(grupo);
    }

    public void eliminarGrupo(int idGrupo) {
        grupoJpa.destroy(idGrupo);
    }

    public void editarGrupo(GrupoTrabajo grupo) {
        try{
            grupoJpa.edit(grupo);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GrupoTrabajo traerGrupo(int idGrupo) {
        return grupoJpa.findGrupoTrabajo(idGrupo);
    }

    public ArrayList<GrupoTrabajo> traerListaGruposTrabajo() {
        List<GrupoTrabajo> lista = grupoJpa.findGrupoTrabajoEntities();
        
        ArrayList<GrupoTrabajo> listaGrupos = new ArrayList<>(lista);
        
        return listaGrupos;
    }
    
    // ############################## creando metodos para JerarquiaJpaController ########################
    
    public void crearJerarquia(Jerarquia jerarquia) {
        jerarquiaJpa.create(jerarquia);
    }

    public void eliminarJerarquia(int idJerarquia) {
        jerarquiaJpa.destroy(idJerarquia);
    }

    public void editarJerarquia(Jerarquia jerarquia) {
        try{
            jerarquiaJpa.edit(jerarquia);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Jerarquia traerJerarquia(int idJerarquia) {
        return jerarquiaJpa.findJerarquia(idJerarquia);
    }

    public ArrayList<Jerarquia> traerListaJerarquias() {
        List<Jerarquia> lista = jerarquiaJpa.findJerarquiaEntities();
        
        ArrayList<Jerarquia> listaJerarquias = new ArrayList<>(lista);
        
        return listaJerarquias;
    }
    
    // ################################# creando metodos para RolJpaController ###########################

    public void crearRol(Rol rol) {
        rolJpa.create(rol);
    }
    
    public void eliminarRol(int idRol) {
        rolJpa.destroy(idRol);
    }

    public void editarRol(Rol rol) {
        try{
            rolJpa.edit(rol);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Rol traerRol(int idRol) {
        return rolJpa.findRol(idRol);
    }

    public ArrayList<Rol> traerListaRoles() {
        List<Rol> lista = rolJpa.findRolEntities();
        
        ArrayList<Rol> listaRoles = new ArrayList<>(lista);
        
        return listaRoles;
    }

    // ############################# creando metodos para UsuarioJpaController #############################

    public void crearUsuario(Usuario usuario) {
        usuarioJpa.create(usuario);
    }

    public void eliminarUsuario(int idUsuario) {
        usuarioJpa.destroy(idUsuario);
    }

    public void editarUsuario(Usuario usuario) {
        try{
            usuarioJpa.edit(usuario);
        }
        catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int idUsuario) {
        return usuarioJpa.findUsuario(idUsuario);
    }

    public ArrayList<Usuario> traerListaUsuarios() {
        List<Usuario> lista = usuarioJpa.findUsuarioEntities();
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>(lista);
        
        return listaUsuarios;
    }


}
