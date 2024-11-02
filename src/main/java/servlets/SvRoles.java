package servlets;

import clases.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRoles", urlPatterns = {"/SvRoles"})
public class SvRoles extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(false);
        String rol = (sesion != null) ? (String) sesion.getAttribute("rolUsuario") : null;
        
        if("Admin sistemas".equals(rol) && request.getRequestURI().contains("/sistemas")){
            System.out.println("######################## inicio de sesion: "+rol);
        }
        else if("Administrativo".equals(rol) && request.getRequestURI().contains("/administrativo")){
            System.out.println("######################## inicio de sesion: "+rol);
        }
        else if("Ayudante".equals(rol) && request.getRequestURI().contains("/ayudante")){
            System.out.println("######################## inicio de sesion: "+rol);
        }
        else if("Contador".equals(rol) && request.getRequestURI().contains("/contador")){
            System.out.println("######################## inicio de sesion: "+rol);
        }
        else{
            response.sendRedirect("denegado.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
