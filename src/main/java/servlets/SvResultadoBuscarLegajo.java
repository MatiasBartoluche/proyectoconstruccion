package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import clases.Rol;
import clases.Usuario;
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
        List<Rol> listaRoles = new ArrayList<>();
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
        
        
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // capturo el legajo ingresado
        String legajo = request.getParameter("legajo");

        // convierto a int
        int numeroLegajo = Integer.parseInt(legajo);
        // busco todos los usuarios
        List<Usuario> listaUsuarios = control.buscarListaUsuarios();
        
        System.out.println("------------------------"+listaUsuarios);
        
        // boolean para verificar si el legajo esta asociado a algun usuario ya existente
        // true = empleado ya tiene usuario
        // false = empleado no tiene usuario
        boolean existeUsuario = false;
        String respuestaJson = "";
        
        if(listaUsuarios != null){
            for(Usuario usuario : listaUsuarios){
                if(usuario.getEmpleado().getLegajo() == numeroLegajo){
                    existeUsuario = true;
                    break; // dejo de recorrer la lista, ya encontre un usuario asociado a ese legajo
                }
                else{
                    // nunca encontre usuario asociado a ese legajo
                     existeUsuario = false;
                }
            }
        }
        else{
            System.out.println("++++++++++++++++++++++++++++ no hay usuarios registrados");
        }
        
        if(existeUsuario){
            // el empleado ya tiene un usuario asociado a su legajo, preparo un json false para la pagina
            respuestaJson = "{\"empleado\": false}";
        }
        else{
            // busco un empleado con el legajo ingresado
            Empleado empleado = control.buscarEmpleado(numeroLegajo);
            if(empleado == null){
                // no existe empleado con el legajo ingresado, preparo un Json nulo para la pagina
                respuestaJson = "{\"empleado\": null}";
            }
            else{
                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
                String empleadoJson = gson.toJson(empleado);
                respuestaJson = empleadoJson;
            }
        }
        response.getWriter().write(respuestaJson);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
