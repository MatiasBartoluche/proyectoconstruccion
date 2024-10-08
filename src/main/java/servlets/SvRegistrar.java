package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.Rol;
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
  
        //String accion = request.getParameter("accion");
        
        /*try{
            if(accion != null){
                System.out.println("####################### imprimiendo desde SvRegistrar");
                switch(accion){
                    case "buscarLegajo":
                        System.out.println("####################### buscando legajo");
                }
            }
        }
        catch(Exception e){
            
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*String buscarEmpleado = request.getParameter("buscarLegajo");
        
        int legajo = Integer.parseInt(buscarEmpleado);
        
        Empleado empleado = control.buscarEmpleado(legajo);
        
        HttpSession sesion = request.getSession();
        
        sesion.setAttribute("buscarEmpleado", empleado);*/
        

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
