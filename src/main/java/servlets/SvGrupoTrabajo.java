package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.GrupoTrabajo;
import clasesDTO.GrupoTrabajoDTO;
import clases.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvGrupoTrabajo", urlPatterns = {"/SvGrupoTrabajo"})
public class SvGrupoTrabajo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        
        // capturo el legajo ingresado
        String mensaje = request.getParameter("mensaje");
        
        if(null != mensaje)switch (mensaje) {
            case "Capataz":
                armarGrupoTrabajo(response);
                break;
            case "Subcontratista":
                armarGrupoSubcontratista(response);
                break;
            case "grupos":
                buscarGrupos(response);
                break;
            case "borrar":
                int idGrupo = Integer.parseInt(request.getParameter("idBorrar"));
                borrarGrupo(response, idGrupo);
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
        // request.getReader retorna un Reader que permite leer la peticion
        BufferedReader leerDatos = request.getReader();
        //construyo el Gson con LocalDateAdapter
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        GrupoTrabajo grupoRecibido = gson.fromJson(leerDatos, GrupoTrabajo.class);
        
        String respuestaJson;
        
        try{
            //primero guardo el grupo, para generar un id
            controlador.crearGrupoTrabajo(grupoRecibido);
            // recupero el empleado capataz de la base de datos
            Empleado capataz = controlador.buscarEmpleado(grupoRecibido.getCapataz().getId());
            // guardo el grupo en el capataz
            capataz.setGrupo(grupoRecibido);
            // actualizo los datos del capataz en la base de datos
            controlador.editarEmpleado(capataz);
            
            // por cada empleado de la lista del grupo
            for(Empleado empleado : grupoRecibido.getListaEmpleados()){
                // recupero el empleado de la base de datos
                int idEmpleado = empleado.getId();
                Empleado actualizar = controlador.buscarEmpleado(idEmpleado);
                // guardo el grupo en el empleado
                actualizar.setGrupo(grupoRecibido);
                // actualizo los datos en la base de datos
                controlador.editarEmpleado(actualizar);
            }
            respuestaJson = "{\"mensaje\": \"El grupo de trabajo se ha creado con exito\"}";
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"No se pudo guardar el grupo de trabajo\", \"error\": \" "+e+" \"}";
        }   
        response.getWriter().write(respuestaJson);   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void armarGrupoTrabajo(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // inicializo un string con contenido json
        String respuestaJson = "{\"mensaje\": \"No existen empleados y/o capataces para armar un grupo de trabajo\"}";
        // consulto lista de empleados
        ArrayList<Empleado> listaEmpleados = controlador.buscarListaEmpleados();
        // inicializo listas de empleados y capataces
        ArrayList<Empleado> capataces = new ArrayList<>();
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        // si la lista de empleados no esta vacia
        if(listaEmpleados != null){
            for(Empleado emp : listaEmpleados){
                // empleado, capataz contrato "obrero" y que no pertenezca a un grupo
                if("Capataz".equals(emp.getJerarquia().getDescripcion())
                        && "Obrero".equals(emp.getContrato().getDescripcion())
                        && emp.getGrupo() == null){
                    capataces.add(emp); // agrego a la lista de capataces
                }
                // empleado contrato "obrero" que no sea capataz y no pertenezca a un grupo
                else if("Obrero".equals(emp.getContrato().getDescripcion())
                        && !"Capataz".equals(emp.getJerarquia().getDescripcion())
                        && emp.getGrupo() == null){
                    empleados.add(emp); // agrego a la lista de empleados
                }
            }
            // ambas listas debe contener al menos un elemento para poder crear un grupo
            if(!capataces.isEmpty() && !empleados.isEmpty()){
                Map<String, Object> listas = new HashMap<>();
                // agrego datos a la clase
                
                listas.put("capataces", capataces);
                listas.put("empleados", empleados);
                // conviero a json
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
                // cambio el valor del string
                respuestaJson = gson.toJson(listas);
            }
        }
        // envio el string
        response.getWriter().write(respuestaJson);
    }
    
    public void armarGrupoSubcontratista(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        //String respuestaJson = "{\"mensaje\": \"No existen empleados y/o Subcontratistas para armar un grupo de trabajo\"}";
        //response.getWriter().write();
    }
    
    public void buscarGrupos(HttpServletResponse response) throws IOException{
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No existen grupos de trabajo\"}";
        
        // primero, debo buscar la lista de grupos de trabajo en la base de datos
        ArrayList<GrupoTrabajo> grupos = controlador.buscarListaGruposTrabajo();
        // si la lista no es vacia, convertir el resultado en dto
        if(!grupos.isEmpty()){
            ArrayList<GrupoTrabajoDTO> gruposDTO = controlador.convertirListaGruposTrabajoDTO(grupos);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();           
            // sobreescribir la variable con el json de los grupos de trabajo
            respuestaJson = gson.toJson(gruposDTO);
        }
        response.getWriter().write(respuestaJson);
    }
    
    public void borrarGrupo(HttpServletResponse response, int idGrupo) throws IOException{
        String respuestaJson = "{\"mensaje\": \"El grupo se ha borrado con exito\"}";
        
        try{
            controlador.eliminarGrupo(idGrupo);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"no se ha podido borrar el grupo\", \"error\":\""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
}
