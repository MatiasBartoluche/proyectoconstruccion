package servlets;

import clases.ControladorObras;
import clases.ControladorSociedades;
import clases.LocalDateAdapter;
import clases.Obra;
import clases.Sociedad;
import clases.TipoObra;
import clasesDTO.SociedadDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvObras", urlPatterns = {"/SvObras"})
public class SvObras extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    ControladorObras controladorObras = new ControladorObras();
    ControladorSociedades controladorSoc = new ControladorSociedades();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if("tipo".equals(mensaje)){
            cargarTipoObra(response);
        }
        else if("sociedades".equals(mensaje)){
            cargarSociedades(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if("nuevaObra".equals(mensaje)){
            nuevaObra(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarTipoObra(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"no existen tipos de obra almacenados\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            ArrayList<TipoObra> listaTipos = controladorObras.buscarListaTipoObra();
            if(listaTipos != null){
                respuestaJson = gson.toJson(listaTipos);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al tratar de cargar los tipo de obra\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void cargarSociedades(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"no existen sociedades almacenadas\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try{
            ArrayList<Sociedad> listaSociedades = controladorSoc.buscarListaSociedades();
            if(listaSociedades != null){
                
                ArrayList<SociedadDTO> sociedadesDTO = controladorSoc.convertirListaASociedadesDTO(listaSociedades);
                respuestaJson = gson.toJson(sociedadesDTO);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al tratar de cargar las sociedades\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void nuevaObra(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String obraRecibida = request.getParameter("obra");
        
        String respuestaJson = "{\"mensaje\": \"La obra se ha guardado con exito. Si desea registrar otra obra, haga click en el boton 'Nueva Obra', de lo contrario haga click en el boton 'Cerrar' \"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            Obra obra = gson.fromJson(obraRecibida, Obra.class);
            controladorObras.crearObra(obra);
        }
        catch(JsonSyntaxException e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar guardar la obra\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

}
