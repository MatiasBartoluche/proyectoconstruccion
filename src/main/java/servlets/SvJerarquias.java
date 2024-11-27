package servlets;

import clases.Controlador;
import clases.Jerarquia;
import com.google.gson.Gson;
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
                    System.out.println("borrar jerarquia: ");
                    break;
                }
            case "borrar":{
                    String idJerarquia = request.getParameter("idJerarquia");
                    int numeroJerarquia = Integer.parseInt(idJerarquia);
                    borrarJerarquia(request, response, numeroJerarquia);
                    System.out.println("borrar jerarquia: ");
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
        
        response.getWriter().write("{\"status\": true, \"mensaje\": \"El cargo fue registrado exitosamente\"}");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void cargarJerarquias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        List<Jerarquia> listaJerarquias;
        
        // realizo la consulta a la base de datos
        listaJerarquias = controlador.buscarListaJerarquias();
        
        // convertir la lista de objetos Jerarquia a Json
        Gson gson = new Gson();
        String rolesJson = gson.toJson(listaJerarquias);

        // enviar el Json a la pagina jsp
        response.getWriter().write(rolesJson);
    }

    private void editarJerarquia(HttpServletRequest request, HttpServletResponse response, int idJerarquia)  throws ServletException, IOException{
        System.out.println("editar jerarquia: "+idJerarquia);
        
        String descripcion = request.getParameter("descripcion");
        
        Jerarquia jerarquia = new Jerarquia();
        jerarquia.setIdJerarquia(idJerarquia);
        jerarquia.setDescripcion(descripcion);
        
        controlador.editarJerarquia(jerarquia);
        
        String respuestaJson = "{\"status\": true,  \"mensaje\":\"Los datos del cargo han sido actualizados\"}";
        response.getWriter().write(respuestaJson);
    }

    private void borrarJerarquia(HttpServletRequest request, HttpServletResponse response, int idJerarquia)  throws ServletException, IOException{
        System.out.println("borrar jerarquia: "+idJerarquia);
        
        controlador.eliminarJerarquia(idJerarquia);
        
        String respuestaJson = "{\"status\": true,  \"mensaje\":\"El cargo ha sido borrado con exito\"}";
        response.getWriter().write(respuestaJson);
    }
}
