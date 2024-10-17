package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import clases.Rol;
import clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRegistrar", urlPatterns = {"/SvRegistrar"})
public class SvRegistrar extends HttpServlet {
    
    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        
        String username = request.getParameter("username");
        boolean existeUsuario = false;
        
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        listaUsuarios = control.buscarListaUsuarios();
        
        for(Usuario usuario : listaUsuarios){
            if(usuario.getUsuario().equals(username)){
                existeUsuario = true;
            }
            else{
                existeUsuario = false;
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // creacion de un objeto en json para enviar respuesta a la pagina
        response.getWriter().write("{\"existe\": " + existeUsuario + "}");
    }

/* -------------------------------------------------------------------------------------------------- */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Leer el cuerpo de la solicitud JSON
        StringBuilder jsonBuffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        // almaceno el string obtenido del json
        String jsonData = jsonBuffer.toString();
        // instancia de Gson, incluyo el adapter en el builder para evitar problemas de parse a objetos LocalDate
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        // ller el string y parse a objeto Usuario
        Usuario usuario = gson.fromJson(jsonData, Usuario.class);
        
        // crear usuario con el objeto recibido
        control.crearUsuario(usuario);
        // Enviar una respuesta al cliente

        //response.getWriter().write("{\"mensaje\":\"Datos recibidos correctamente\"}");
        response.getWriter().write("{\"mensaje\": true}");

        //response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
