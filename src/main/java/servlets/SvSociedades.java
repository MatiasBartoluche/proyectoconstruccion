package servlets;

import clases.ControladorSociedades;
import clases.LocalDateAdapter;
import clases.Seguro;
import clases.Sociedad;
import clasesDTO.SeguroDTO;
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


@WebServlet(name = "SvSociedades", urlPatterns = {"/SvSociedades"})
public class SvSociedades extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    ControladorSociedades controladorSoc = new ControladorSociedades();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if(null != mensaje)switch (mensaje) {
            case "sociedades":
                cargarSociedades(response);
                break;
            case "detalleSociedad":
                cargarDetalleSociedad(request, response);
                break;
            case "seguros":
                cargarSeguros(response);
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
        
        if(null != mensaje)switch (mensaje) {
            case "nuevo":
                nuevaSociedad(request, response);
                break;
            case "modificar":
                modificarSociedad(request, response);
                break;
            case "borrarSociedad":
                borrarSociedad(request, response);
                break;
            case "nuevoSeguro":
                nuevoSeguro(request, response);
                break;
            case "borrarSeguro":
                borrarSeguro(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void nuevaSociedad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String sociedadRecibida = request.getParameter("sociedad");
        
        System.out.println(sociedadRecibida);
        String respuestaJson = "{\"mensaje\": \"La sociedad se ha registrado con exito\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            Sociedad sociedad = gson.fromJson(sociedadRecibida, Sociedad.class);
            
            controladorSoc.crearSociedad(sociedad);
        }
        catch(JsonSyntaxException e){
            respuestaJson = "{\"mensaje\": \"El empleado no se ha podido borrar del grupo\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void cargarSociedades(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No hay sociedades guardadas\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            ArrayList<Sociedad> listaSociedades = controladorSoc.buscarListaSociedades();
            if(listaSociedades != null){
                ArrayList<SociedadDTO> sociedadesDTO = controladorSoc.convertirListaASociedadesDTO(listaSociedades);
                
                respuestaJson = gson.toJson(sociedadesDTO);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"No se ha podido cargar la lista de sociedades\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void cargarDetalleSociedad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No se ha encontrado detalles de esa sociedad\"}";
        int idSociedad = Integer.parseInt(request.getParameter("idSociedad"));
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            Sociedad soc = controladorSoc.buscarSociedad(idSociedad);
            if(soc != null){
                SociedadDTO socDTO = controladorSoc.convertirSociedadDTO(soc);
                respuestaJson = gson.toJson(socDTO);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar cargar los datos de la sociedad\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void modificarSociedad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String sociedadRecibida = request.getParameter("sociedad");
        String respuestaJson = "{\"mensaje\": \"Los datos se han modificado con exito\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            if(sociedadRecibida != null){
                Sociedad sociedad = gson.fromJson(sociedadRecibida, Sociedad.class);
                controladorSoc.editarSociedad(sociedad);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar modificar los datos de la sociedad\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void borrarSociedad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"La sociedad se ha borado con exito\"}";
        int idSociedad = Integer.parseInt(request.getParameter("idSociedad"));
        
        try{
            // busco la sociedad a eliminar
            Sociedad socEliminar = controladorSoc.buscarSociedad(idSociedad);
            // primero elimino todos los seguros pertenecientes a la sociedad
            for(Seguro seg : socEliminar.getSeguros()){
                controladorSoc.eliminarSeguro(seg.getIdSeguro());
            }
            // elimino la sociedad
            controladorSoc.eliminarSociedad(idSociedad);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar borrar la sociedad\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void nuevoSeguro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"El seguro se ha registrado con exito\"}";
        String seguroRecibido = request.getParameter("seguro");
        int idSociedad = Integer.parseInt(request.getParameter("idSociedad"));
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try{
            // convierto el json en una clase "Seguro"
            Seguro seguro = gson.fromJson(seguroRecibido, Seguro.class);
            // busco la sociedad que esta contratando el seguro
            Sociedad sociedad = controladorSoc.buscarSociedad(idSociedad);
            // asigno la sociedad a la que pertenece este uevo seguro
            seguro.setSociedad(sociedad);
            // agrego el seguro a la lista de seguros de la sociedad
            sociedad.contratarSeguro(seguro);
            // almaceno la sociedad y el seguro
            controladorSoc.crearSeguro(seguro);
            controladorSoc.editarSociedad(sociedad);
        }
        catch(JsonSyntaxException e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar registrar el seguro\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void borrarSeguro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"El seguro se ha borado con exito\"}";
        int idSeguro = Integer.parseInt(request.getParameter("id"));
        
        //Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            controladorSoc.eliminarSeguro(idSeguro);
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar borrar el seguro\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void cargarSeguros(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No existen seguros\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        try{
            ArrayList<Seguro> seguros = controladorSoc.buscarListaSeguros();
            
            if(seguros != null){
                ArrayList<SeguroDTO> segurosDTO = controladorSoc.convertitListaSegurosDTO(seguros);
                respuestaJson = gson.toJson(segurosDTO);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"Ha ocurrido un error al intentar recuperar los seguros\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }
}
