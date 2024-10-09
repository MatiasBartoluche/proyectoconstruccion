package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.Rol;
import com.google.gson.Gson;
import java.io.IOException;
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
        
        List<Rol> listaRoles = new ArrayList<Rol>();
        
        // realizo la consulta a la base de datos
        listaRoles = control.buscarListaRoles();
        
        // convertir la lista de objetos Rol a Json
        Gson gson = new Gson();
        String rolesJson = gson.toJson(listaRoles);
        
        //HttpSession sesion = request.getSession();

        // enviar el Json a la pagina jsp
        response.getWriter().write(rolesJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // capturo el id del rol que se selecciono para crear un nuevo usuario
        String rolSeleccionado = request.getParameter("rol");
        System.out.println("############################# opcion seleccionada: "+rolSeleccionado);
        
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
