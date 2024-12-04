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
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void borrarEmpleadoDelGrupo(HttpServletResponse response, int idEmpleado) throws IOException{
        String respuestaJson = "{\"mensaje\": \"El empleado se ha borrado del grupo con exito\"}";
        
        try{
            Empleado empleado = controlador.buscarEmpleado(idEmpleado);
            empleado.setGrupo(null);
            controlador.editarEmpleado(empleado);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"El empleado no se ha podido borrar del grupo\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
    
    public void recargarGrupo(HttpServletResponse response, int idGrupo) throws IOException{
        String respuestaJson;
        
        try{
            GrupoTrabajo grupo = controlador.buscarGrupo(idGrupo);
            
            GrupoTrabajoDTO grupoDTO = controlador.grupoTrabajoDTO(grupo);
            
            // si el capataz tiene una foto guardada, la convierto en base64
            EmpleadoDTO capatazDTO = grupoDTO.getCapataz();
            
            if (capatazDTO.getFotoDni() != null) {
                String fotoBase64 = null;
                fotoBase64 = Base64.getEncoder().encodeToString(capatazDTO.getFotoDni());
                capatazDTO.setFotoDniBase64(fotoBase64);
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
        String respuestaJson = "{\"mensaje\": \"No se han encontrado capataces\"}";
        
        ArrayList<Empleado> listaEmpleados = controlador.buscarPorDescripcionJerarquia("Capataz");
        
        if(listaEmpleados != null){
            ArrayList<EmpleadoDTO> empleadosDTO = controlador.convertirListaAEmpleadosDTO(listaEmpleados);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            respuestaJson = gson.toJson(empleadosDTO);
            
            for(Empleado emp: listaEmpleados){
                System.out.println("+++++++++++++++++++++ grupo: "+emp.getGrupo());
            }
        }
        response.getWriter().write(respuestaJson);
    }
}
