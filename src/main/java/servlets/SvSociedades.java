package servlets;

import clases.ControladorSociedades;
import clases.LocalDateAdapter;
import clases.Sociedad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        if("sociedades".equals(mensaje)){
            cargarSociedades(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String mensaje = request.getParameter("mensaje");
        
        if("nuevo".equals(mensaje)){
            nuevaSociedad(request, response);
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
                respuestaJson = gson.toJson(listaSociedades);
            }
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"No se ha podido cargar la lista de sociedades\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

}