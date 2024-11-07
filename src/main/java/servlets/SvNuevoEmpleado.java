package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Jerarquia;
import clases.Rol;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvNuevoEmpleado", urlPatterns = {"/SvNuevoEmpleado"})
public class SvNuevoEmpleado extends HttpServlet {
    
    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<Jerarquia> listaJerarquias = new ArrayList<>();
        List<Contrato> listaContratos = new ArrayList<>();
        // realizo la consulta a la base de datos
        listaJerarquias = control.buscarListaJerarquias();
        listaContratos = control.buscarListaContratos();
        
        CargarDatos datos = new CargarDatos(listaJerarquias, listaContratos);
        
        // convertir la lista de objetos Rol a Json
        Gson gson = new Gson();
        String datosJson = gson.toJson(datos);

        // enviar el Json a la pagina jsp
        
        System.out.println("----------------------------"+datosJson);
        response.getWriter().write(datosJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // clase que se usa de fotma local para enviar dos listas a traves de un json
    private class CargarDatos{
        List<Jerarquia> jerarquias;
        List<Contrato> contratos;
        
        CargarDatos(List<Jerarquia> listaJerarquias, List<Contrato> listaContratos){
            this.jerarquias = listaJerarquias;
            this.contratos = listaContratos;
        }
    }
}
