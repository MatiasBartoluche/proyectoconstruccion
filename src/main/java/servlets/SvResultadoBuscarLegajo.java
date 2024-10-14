package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import clases.Rol;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvResultadoBuscarLegajo", urlPatterns = {"/SvResultadoBuscarLegajo"})
public class SvResultadoBuscarLegajo extends HttpServlet {
    
    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        response.setContentType("text/plain");

        String legajo = request.getParameter("legajo");   
        
        // busco el empleado con el legajo recibido
        int numeroLegajo = Integer.parseInt(legajo);
        
        Empleado empleado = control.buscarEmpleado(numeroLegajo);
        
        if(empleado != null){
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String empleadoJson = gson.toJson(empleado);

            response.getWriter().write(empleadoJson);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
