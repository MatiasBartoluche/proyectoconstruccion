package servlets;

import clases.Controlador;
import clases.Jerarquia;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvJerarquias", urlPatterns = {"/SvJerarquias"})
public class SvJerarquias extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String mensaje = request.getParameter("mensaje");
        
        if(null != mensaje)switch (mensaje) {
            case "cargar":
                cargarJerarquias(request, response);
                break;
            case "editar":{
                    String idJerarquia = request.getParameter("idJerarquia");
                    int numeroJerarquia = Integer.parseInt(idJerarquia);
                    editarJerarquia(request, response, numeroJerarquia);
                    break;
                }
            case "borrar":{
                    String idJerarquia = request.getParameter("idJerarquia");
                    int numeroJerarquia = Integer.parseInt(idJerarquia);
                    borrarJerarquia(response, numeroJerarquia);
                    break;
                }
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"status\": true, \"mensaje\": \"El cargo fue registrado exitosamente\"}";
        
        try{
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            Gson gson = new Gson();
            Jerarquia jerarquia = gson.fromJson(json, Jerarquia.class);

            controlador.crearJerarquia(jerarquia);
        }
        catch(JsonSyntaxException | IOException e){
            respuestaJson = "{\"status\": false, \"mensaje\": \"no se ha podido crear el cargo\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void cargarJerarquias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String respuestaJson;
        
        try{
            List<Jerarquia> listaJerarquias;
            // realizo la consulta a la base de datos
            listaJerarquias = controlador.buscarListaJerarquias();
            // convertir la lista de objetos Jerarquia a Json
            Gson gson = new Gson();
            respuestaJson = gson.toJson(listaJerarquias);
            // enviar el Json a la pagina jsp
        }
        catch(Exception e){
            respuestaJson = "{\"status\": false,  \"mensaje\":\"Ocurrio un error al intentar cargar los cargos\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void editarJerarquia(HttpServletRequest request, HttpServletResponse response, int idJerarquia)  throws ServletException, IOException{
        System.out.println("editar jerarquia: "+idJerarquia);
        
        String descripcion = request.getParameter("descripcion");
        String respuestaJson = "{\"status\": true,  \"mensaje\":\"Los datos del cargo han sido actualizados\"}";
        
        try{
            Jerarquia jerarquia = new Jerarquia();
            jerarquia.setIdJerarquia(idJerarquia);
            jerarquia.setDescripcion(descripcion);
            controlador.editarJerarquia(jerarquia);
        }
        catch(Exception e){
            respuestaJson = "{\"status\": false,  \"mensaje\":\"Ocurrio un error al intentar editar el cargo\", \"error\": \""+e+"\"}";
        }
        response.getWriter().write(respuestaJson);
    }

    private void borrarJerarquia(HttpServletResponse response, int idJerarquia)  throws ServletException, IOException{
        String respuestaJson;
        
        try{
            controlador.eliminarJerarquia(idJerarquia);
            respuestaJson = "{\"status\": true,  \"mensaje\":\"El cargo ha sido borrado con exito\"}";
        }
        catch(Exception e){
            respuestaJson = "{\"status\": false,  \"mensaje\":\"El cargo no se puede borrar, esta siendo ocupado por otros empleados\"}";
        }       
        response.getWriter().write(respuestaJson);
    }
}
