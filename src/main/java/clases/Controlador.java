package clases;

import clasesDTO.EmpleadoDTO;
import clasesDTO.GrupoTrabajoDTO;
import java.util.ArrayList;
import java.util.Map;
import persistencia.ControladorPersistencia;

public class Controlador {
    
    // creando instancia de ControladorPersistencia
    // de esta manera, tengo acceso a todos los metodos de todos los JpaController de todas las clases
    // a traves de una sola instancia
    ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    
    // ######################### creando metodos para Contrato ##################
    
    public void crearContrato(Contrato contrato){
        controladorPersistencia.createContrato(contrato);
    }
    
    public void eliminarContrato(int idContrato){
        controladorPersistencia.deleteContrato(idContrato);
    }
    
    public void editarContrato(Contrato contrato){
        controladorPersistencia.editContrato(contrato);
    }
    
    public Contrato buscarContrato(int idContrato){
        return controladorPersistencia.findContrato(idContrato);
    }
    
    public ArrayList<Contrato> buscarListaContratos(){
        return controladorPersistencia.fintListaContratos();
    }
    
    // ######################### creando metodos para Empleado ##################
    
    public void crearEmpleado(Empleado empleado){
        controladorPersistencia.createEmpleado(empleado);
    }
    
    public void eliminarEmpleado(int idEmpleado){
        controladorPersistencia.deleteEmpleado(idEmpleado);
    }
    
    public void editarEmpleado(Empleado empleado){
        controladorPersistencia.editEmpleado(empleado);
    }
    
    public Empleado buscarEmpleado(int idEmpleado){
        return controladorPersistencia.findEmpleado(idEmpleado);
    }
    
    public ArrayList<Empleado> buscarListaEmpleados(){
        return controladorPersistencia.findListaEmpleado();
    }
    
    public Empleado buscarEmpleadoPorLegajo(int legajo){
        return controladorPersistencia.findEmpleadoByLegajo(legajo);
    }
    
    public ArrayList<Empleado> buscarEmpleadosPorAtributos(Map<String, Object> parametros){
        return controladorPersistencia.findEmpleadosByAttributes(parametros);
    }
    
    public ArrayList<Empleado> buscarEmpleadosPorGrupo(int idGrupo, String nombre){
        return controladorPersistencia.findEmpleadoByGroup(idGrupo, nombre);
    }
    
    public EmpleadoDTO convertirAEmpleadoDTO(Empleado empleado){
        return controladorPersistencia.convertAEmpleadoDTO(empleado);
    }
    
    public ArrayList<EmpleadoDTO> convertirListaAEmpleadosDTO(ArrayList<Empleado> empleados){
        return controladorPersistencia.convertListaAEmpleadoDTO(empleados);
    }
    
    public ArrayList<Empleado> buscarPorDescripcionJerarquia(String descripcion){
        return controladorPersistencia.findByDescripcionJerarquia(descripcion);
    }
    
    public ArrayList<Empleado> buscarPorJerarquiaContrato(String descJerarquia, boolean jerarquia, String descContrato, boolean contrato){
        return controladorPersistencia.findByJerarquiaContrato(descJerarquia, jerarquia, descContrato, contrato);
    }
    // ###################### creando metodos para Estado #######################
    
    
    public void crearEstadoEmpleado(EstadoEmpleado estado){
        controladorPersistencia.creteEstadoEmpleado(estado);
    }
    
    public void eliminarEstado(int idEstado){
        controladorPersistencia.deleteEstadoEmpleado(idEstado);
    }
    
    public void editarEstado(EstadoEmpleado estado){
        controladorPersistencia.editEstadoEmpleado(estado);
    }
    
    public EstadoEmpleado buscarEstado(int idEstado){
        return controladorPersistencia.findEstadoEmpleado(idEstado);
    }
    
    public ArrayList<EstadoEmpleado> buscarListaEstados(){
        return controladorPersistencia.findListaEstados();
    }
    
    // ###################### creando metodos para grupoTrabajo #################
    
    public void crearGrupoTrabajo(GrupoTrabajo grupo){
        controladorPersistencia.createGrupo(grupo);
    }
    
    public void eliminarGrupo(int idGrupo){
        controladorPersistencia.deleteGrupo(idGrupo);
    }
    
    public void editarGrupo(GrupoTrabajo grupo){
        controladorPersistencia.editGrupo(grupo);
    }
    
    public GrupoTrabajo buscarGrupo(int idGrupo){
        return controladorPersistencia.findGrupo(idGrupo);
    }
    
    public ArrayList<GrupoTrabajo> buscarListaGruposTrabajo(){
        return controladorPersistencia.findListaGruposTrabajo();
    }
    
    public GrupoTrabajoDTO grupoTrabajoDTO(GrupoTrabajo grupo){
        return controladorPersistencia.convertGrupoTrabajoDTO(grupo);
    }
    
    public ArrayList<GrupoTrabajoDTO> convertirListaGruposTrabajoDTO(ArrayList<GrupoTrabajo> grupos){
        return controladorPersistencia.convertListaGrupoTrabajoDTO(grupos);
    }
    
    // ######################## creando metodos para Jerarquia ##################
    
    public void crearJerarquia(Jerarquia jerarquia){
        controladorPersistencia.createJerarquia(jerarquia);
    }
    
    public void eliminarJerarquia(int idJerarquia){
        controladorPersistencia.deleteJerarquia(idJerarquia);
    }
    
    public void editarJerarquia(Jerarquia jerarquia){
        controladorPersistencia.editJerarquia(jerarquia);
    }
    
    public Jerarquia buscarJerarquia(int idJerarquia){
        return controladorPersistencia.findJerarquia(idJerarquia);
    }
    
    public ArrayList<Jerarquia> buscarListaJerarquias(){
        return controladorPersistencia.findListaJerarquias();
    }
    
    // ########################## creando metodos para Rol ######################
    
    public void crearRol(Rol rol){
        controladorPersistencia.createRol(rol);
    }
    
    public void eliminarRol(int idRol){
        controladorPersistencia.deleteRol(idRol);
    }
    
    public void editarRol(Rol rol){
        controladorPersistencia.editRol(rol);
    }
    
    public Rol buscarRol(int idRol){
        return controladorPersistencia.findRol(idRol);
    }
    
    public ArrayList<Rol> buscarListaRoles(){
        return controladorPersistencia.findListaRoles();
    }
    
    // ########################## creando metodos para usuario ##################
    
    public void crearUsuario(Usuario usuario){
        controladorPersistencia.createUsuario(usuario);
    }
    
    public void eliminarUsuario(int idUsuario){
        controladorPersistencia.findUsuario(idUsuario);
    }
    
    public void editarUsuario(Usuario usuario){
        controladorPersistencia.editUsuario(usuario);
    }
    
    public Usuario buscarUsuario(int idUsuario){
        return controladorPersistencia.findUsuario(idUsuario);
    }
    
    public ArrayList<Usuario> buscarListaUsuarios(){
        return controladorPersistencia.findListaUsuarios();
    }   
}
