package clases;

import java.util.ArrayList;
import persistencia.ControladorPersistencia;

public class Controlador {
    
    // creando instancia de ControladorPersistencia
    // de esta manera, tengo acceso a todos los metodos de todos los JpaController de todas las clases
    // a traves de una sola instancia
    ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    
    // ######################### creando metodos para Contrato ##################
    
    public void crearContrato(Contrato contrato){
        controladorPersistencia.crearContrato(contrato);
    }
    
    public void eliminarContrato(int idContrato){
        controladorPersistencia.eliminarContrato(idContrato);
    }
    
    public void editarContrato(Contrato contrato){
        controladorPersistencia.editarContrato(contrato);
    }
    
    public Contrato buscarContrato(int idContrato){
        return controladorPersistencia.traerContrato(idContrato);
    }
    
    public ArrayList<Contrato> buscarListaContratos(){
        return controladorPersistencia.traerListaContratos();
    }
    
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
    
    // ###################### creando metodos para Estado #######################
    
    
    public void crearEstado(EstadoEmpleado estado){
        controladorPersistencia.crearEstado(estado);
    }
    
    public void eliminarEstado(int idEstado){
        controladorPersistencia.eliminarEstado(idEstado);
    }
    
    public void editarEstado(EstadoEmpleado estado){
        controladorPersistencia.editarEstado(estado);
    }
    
    public EstadoEmpleado buscarEstado(int idEstado){
        return controladorPersistencia.traerEstado(idEstado);
    }
    
    public ArrayList<EstadoEmpleado> buscarListaEstados(){
        return controladorPersistencia.traerListaEstados();
    }
    
    // ###################### creando metodos para grupoTrabajo #################
    
    public void crearGrupoTrabajo(GrupoTrabajo grupo){
        controladorPersistencia.crearGrupo(grupo);
    }
    
    public void eliminarGrupo(int idGrupo){
        controladorPersistencia.eliminarGrupo(idGrupo);
    }
    
    public void editarGrupo(GrupoTrabajo grupo){
        controladorPersistencia.editarGrupo(grupo);
    }
    
    public GrupoTrabajo buscarGrupo(int idGrupo){
        return controladorPersistencia.traerGrupo(idGrupo);
    }
    
    public ArrayList<GrupoTrabajo> buscarListaGruposTrabajo(){
        return controladorPersistencia.traerListaGruposTrabajo();
    }
    
    // ######################## creando metodos para Jerarquia ##################
    
    public void crearJerarquia(Jerarquia jerarquia){
        controladorPersistencia.crearJerarquia(jerarquia);
    }
    
    public void eliminarJerarquia(int idJerarquia){
        controladorPersistencia.eliminarJerarquia(idJerarquia);
    }
    
    public void editarJerarquia(Jerarquia jerarquia){
        controladorPersistencia.editarJerarquia(jerarquia);
    }
    
    public Jerarquia buscarJerarquia(int idJerarquia){
        return controladorPersistencia.traerJerarquia(idJerarquia);
    }
    
    public ArrayList<Jerarquia> buscarListaJerarquias(){
        return controladorPersistencia.traerListaJerarquias();
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
