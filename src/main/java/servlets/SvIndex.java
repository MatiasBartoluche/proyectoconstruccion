package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       //redirijo a la pagina de registro de usuarios
       System.out.println("###########################redireccionando a registrar.jsp");
       //request.getRequestDispatcher("/vistas/registrar.jsp").forward(request, response);
       response.sendRedirect("registrar.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");
        
        System.out.println("###########################"+usuario+pass);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
