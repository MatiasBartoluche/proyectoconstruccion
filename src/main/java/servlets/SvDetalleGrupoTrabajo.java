package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.GrupoTrabajo;
import clases.LocalDateAdapter;
import clasesDTO.EmpleadoDTO;
import clasesDTO.GrupoTrabajoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvDetalleGrupoTrabajo", urlPatterns = {"/SvDetalleGrupoTrabajo"})
public class SvDetalleGrupoTrabajo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if(null != mensaje)switch (mensaje) {
            case "borrarEmpleado":
                int idBorrarEmpleado = Integer.parseInt(request.getParameter("idBorrarEmpleado"));
                borrarEmpleadoDelGrupo(response, idBorrarEmpleado);
                break;
            case "recargarGrupo":
                int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
                recargarGrupo(response, idGrupo);
                break;
            case "capataz":
                cargarCapataz(response);
                break;
            case "empleados":
                cargarEmpleados(response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if("cambiar".equals(mensaje)){
            cambiarCapataz(request, response);
        }
        else if("empleados".equals(mensaje)){
            agregarEmpleados(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    
// ------------------------ implementacion de metodos para manejar diferentes solicitudes -----------------
    
    public void borrarEmpleadoDelGrupo(HttpServletResponse response, int idEmpleado) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String respuestaJson = "{\"mensaje\": \"El empleado se ha borrado del grupo con exito\"}";
        
        try{
            // recupero de la base de datos el empleado y elimino su asociacion al grupo
            Empleado empleado = controlador.buscarEmpleado(idEmpleado);
            empleado.setGrupo(null);
            // guardo empleado con grupo = null
            controlador.editarEmpleado(empleado);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"El empleado no se ha podido borrar del grupo\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
    
    public void recargarGrupo(HttpServletResponse response, int idGrupo) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String respuestaJson;
        
        try{
            GrupoTrabajo grupo = controlador.buscarGrupo(idGrupo);
            // convierto a GrupoTrabajoDTO para poder serializarlo a json
            GrupoTrabajoDTO grupoDTO = controlador.grupoTrabajoDTO(grupo);
            EmpleadoDTO capatazDTO = grupoDTO.getCapataz();

            // si el capataz tiene una foto guardada, la convierto en base64
            if (capatazDTO.getFotoDni() != null) {
                String fotoBase64 = Base64.getEncoder().encodeToString(capatazDTO.getFotoDni());
                capatazDTO.setFotoDniBase64(fotoBase64);
                // guardo la foto en base64 al capataz y lo agrego al grupo
                grupoDTO.setCapataz(capatazDTO);
            }
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            respuestaJson = gson.toJson(grupoDTO);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"El grupo no se ha podido recargar\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
    
    public void cargarCapataz(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String respuestaJson = "";
        
        // busco a todos los empleados que sean capataz

        try{
            ArrayList<Empleado> listaEmpleados = controlador.buscarPorDescripcionJerarquia("Capataz");
            if(listaEmpleados != null){
                // convierto la lista de capataz a lista de EmpleadoDTO
                ArrayList<EmpleadoDTO> empleadosDTO = controlador.convertirListaAEmpleadosDTO(listaEmpleados);
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
                respuestaJson = gson.toJson(empleadosDTO);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"No se han encontrado capataces\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
    
    public void cambiarCapataz(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String respuestaJson;
        
        Map<String, Object> armarJson = new HashMap<>();
        
        // recibo el id del capataz actual, el id del nuevo capataz
        // y un boolean que determina si se intercambiaran capataces de cada grupo
        int idCapatazActual = Integer.parseInt(request.getParameter("actual"));
        int idNuevoCapataz = Integer.parseInt(request.getParameter("nuevo"));
        boolean intercambiar = Boolean.parseBoolean(request.getParameter("intercambiar"));
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            
        try{
            Empleado capatazActual = controlador.buscarEmpleado(idCapatazActual);
            Empleado nuevoCapataz = controlador.buscarEmpleado(idNuevoCapataz);
            EmpleadoDTO capatazDTO = controlador.convertirAEmpleadoDTO(nuevoCapataz);
            GrupoTrabajo grupo = capatazActual.getGrupo();
            // no se realiza intercambio de capataces
            if(intercambiar == false){
                // agrego el nuevo capataz al grupo
                grupo.setCapataz(nuevoCapataz);
                // borrar el capataz actual del grupo
                capatazActual.setGrupo(null);
                // agrego el grupo al nuevo capataz
                nuevoCapataz.setGrupo(grupo);
                // guardo los nuevos datos
                controlador.editarGrupo(grupo);
                controlador.editarEmpleado(capatazActual);
                controlador.editarEmpleado(nuevoCapataz);
                // armo la respuesta json con el hashmap
                armarJson.put("status", true);
                armarJson.put("mensaje", "El capataz se ha cambiado con exito");
                armarJson.put("capataz", capatazDTO);
            }
            else if(intercambiar == true){
                GrupoTrabajo nuevoGrupo = nuevoCapataz.getGrupo();

                grupo.setCapataz(nuevoCapataz);
                nuevoGrupo.setCapataz(capatazActual);
                
                capatazActual.setGrupo(nuevoGrupo);
                nuevoCapataz.setGrupo(grupo);
                
                controlador.editarGrupo(grupo);
                controlador.editarGrupo(nuevoGrupo);
                controlador.editarEmpleado(capatazActual);
                controlador.editarEmpleado(nuevoCapataz);
                
                armarJson.put("status", true);
                armarJson.put("mensaje", "Los capataces se han intercambiado con exito");
                armarJson.put("capataz", capatazDTO);
            }
        }
        catch(Exception e){
            armarJson.put("status", false);
            armarJson.put("mensaje", "No se ha podido renovar el capataz");
            armarJson.put("error", e);
        }
        // convierto el hashmap a json
        respuestaJson = gson.toJson(armarJson);
        response.getWriter().write(respuestaJson);
    }
    
    public void cargarEmpleados(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"status\": \""+false+"\" ,\"mensaje\": \"No se han encontrado empleados\"}";
        Map<String, Object> armarJson = new HashMap<>();
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        try{
            // busco empleados que no sean capataz y que su contrato sea "Obrero"
            ArrayList<Empleado> listaEmpleados = controlador.buscarPorJerarquiaContrato("Capataz", false, "Obrero", true);
            if(listaEmpleados != null){
                ArrayList<EmpleadoDTO> empleadosDTO = controlador.convertirListaAEmpleadosDTO(listaEmpleados);
                armarJson.put("status", true);
                armarJson.put("empleados", empleadosDTO);
                respuestaJson = gson.toJson(armarJson);
            }
        }
        catch(Exception e){
            armarJson.put("status", false);
            armarJson.put("mensaje", "Ha ocurrido un error en la carga de empleados");
            armarJson.put("error", e);
            respuestaJson = gson.toJson(armarJson);
        }
        response.getWriter().write(respuestaJson);
    }

    private void agregarEmpleados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // inicializo un string que se usara para enviar la respuesta json a la pagina
        String respuestaJson;
        // inicializo un HashMap que servira ára crear una respuesta json
        Map<String, Object> armarJson = new HashMap<>();
        // recupero el json del grupo de tranajo recibido
        String grupoRequest =  request.getParameter("grupo");
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        GrupoTrabajo grupoRecibido = gson.fromJson(grupoRequest, GrupoTrabajo.class);
        
        try{
            GrupoTrabajo grupo = controlador.buscarGrupo(grupoRecibido.getIdGrupo());
            if(grupo != null){
                for(Empleado emp : grupoRecibido.getListaEmpleados()){
                    Empleado empleado = controlador.buscarEmpleado(emp.getId());
                    // a este empleado le asigno el grupo
                    empleado.setGrupo(grupo);
                    // guardo este empleado
                    controlador.editarEmpleado(empleado);
                    //agrego este empleado al grupo
                    grupo.agregarEmpleado(empleado);
                }
                // cuando termina el for, guardo el grupo con sus nuevos empleados
                controlador.editarGrupo(grupo);
            }
            // adjunto al hashmap pares clave-valor para una respuesta correcta
            armarJson.put("mensaje", "Se han agregado los nuevos empleados al grupo");
            armarJson.put("status", true);
            // sobreescribo la variable
            respuestaJson = gson.toJson(armarJson);
        }
        catch(Exception e){
            // adjunto al hashmap pares clave-valor para una respuesta erronea
            armarJson.put("mensaje", "Ha ocurrido un error al agregar empleados al grupo");
            armarJson.put("status", false);
            armarJson.put("error", e);
            respuestaJson = gson.toJson(armarJson);
        }
        // enviar respuesta
        response.getWriter().write(respuestaJson);
    }
}
