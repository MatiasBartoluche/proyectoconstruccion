package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.GrupoTrabajo;
import clases.LocalDateAdapter;
import clasesDTO.GrupoTrabajoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.time.LocalDate;
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
        
        if("borrarEmpleado".equals(mensaje)){
            int idBorrarEmpleado = Integer.parseInt(request.getParameter("idBorrarEmpleado"));
            borrarEmpleadoDelGrupo(response, idBorrarEmpleado);
        }
        else if("recargarGrupo".equals(mensaje)){
            int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
            recargarGrupo(response, idGrupo);
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
            
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            
            respuestaJson = gson.toJson(grupoDTO);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"El grupo no se ha podido recargar\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
}