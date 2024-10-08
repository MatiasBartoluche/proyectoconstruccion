package clases;

import java.util.ArrayList;
import persistencia.ControladorPersistencia;

public class Controlador {
    
    // creando instancia de ControladorPersistencia
    // de esta manera, tengo acceso a todos los metodos de todos los JpaController de todas las clases
    // a traves de una sola instancia
    ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    
    // ######################### creando metodos para Empleado ##################
    
    public void crearEmpleado(Empleado empleado){
        controladorPersistencia.crearEmpleado(empleado);
    }
    
    public void eliminarEmpleado(int idEmpleado){
        controladorPersistencia.eliminarEmpleado(idEmpleado);
    }
    
    public void editarEmpleado(Empleado empleado){
        controladorPersistencia.editarEmpleado(empleado);
    }
    
    public Empleado buscarEmpleado(int idEmpleado){
        return controladorPersistencia.traerEmpleado(idEmpleado);
    }
    
    public ArrayList<Empleado> buscarListaEmpleados(){
        return controladorPersistencia.traerListaEmpleado();
    }
    
    // ########################## creando metodos para Rol ######################
    
    public void crearRol(Rol rol){
        controladorPersistencia.crearRol(rol);
    }
    
    public void eliminarRol(int idRol){
        controladorPersistencia.eliminarRol(idRol);
    }
    
    public void editarRol(Rol rol){
        controladorPersistencia.editarRol(rol);
    }
    
    public Rol buscarRol(int idRol){
        return controladorPersistencia.traerRol(idRol);
    }
    
    public ArrayList<Rol> buscarListaRoles(){
        return controladorPersistencia.traerListaRoles();
    }
    
    // ########################## creando metodos para usuario ##################
    
    public void crearUsuario(Usuario usuario){
        controladorPersistencia.crearUsuario(usuario);
    }
    
    public void eliminarUsuario(int idUsuario){
        controladorPersistencia.eliminarUsuario(idUsuario);
    }
    
    public void editarUsuario(Usuario usuario){
        controladorPersistencia.editarUsuario(usuario);
    }
    
    public Usuario buscarUsuario(int idUsuario){
        return controladorPersistencia.traerUsuario(idUsuario);
    }
    
    public ArrayList<Usuario> buscarListaUsuarios(){
        return controladorPersistencia.traerListaUsuarios();
    }
    
}
