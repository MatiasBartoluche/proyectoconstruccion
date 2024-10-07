package servlets;

import clases.Controlador;
import clases.Rol;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.EmpleadoJpaController;


@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       //response.sendRedirect("registrar.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Rol rolSistemas = new Rol("Admin sistemas");
        Rol rolAdministrativo = new Rol("Administrativo");
        Rol rolAyudante = new Rol("Ayudante");
        Rol rolContador = new Rol("Contador");
        
        controlador.crearRol(rolSistemas);
        controlador.crearRol(rolAdministrativo);
        controlador.crearRol(rolAyudante);
        controlador.crearRol(rolContador);
        
        
        /*
        if("admin".equals(usuario) && "1234".equals(pass)){
            System.out.println("bienvenido");
            response.sendRedirect("home.jsp");
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario); // guardo el nombre de usuario
        }
        else{
            System.out.println("usuario y/o conrtasenai incorrectos");
            response.sendRedirect("index.jsp?error=1");
        }*/
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

/* token github */
/* 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 */