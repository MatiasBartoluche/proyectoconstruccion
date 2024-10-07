package persistencia;

import clases.Empleado;
import clases.Rol;
import clases.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistencia {
    
    // creando instancias de cada JpaController
    
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    
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
        
        ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>(lista);
        
        return listaEmpleados;
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
        
        ArrayList<Rol> listaRoles = new ArrayList<Rol>(lista);
        
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
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>(lista);
        
        return listaUsuarios;
    }


}
