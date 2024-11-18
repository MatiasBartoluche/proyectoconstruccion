package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvDetalleEmpleado", urlPatterns = {"/SvDetalleEmpleado"})
public class SvDetalleEmpleado extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String detalleId = request.getParameter("detalleIdEmpleado");
        
        int numeroId = Integer.parseInt(detalleId);
        
        Empleado empleado = controlador.buscarEmpleado(numeroId);
        
        String fotoBase64 = null;
        if (empleado.getFotoDni() != null) {
            fotoBase64 = Base64.getEncoder().encodeToString(empleado.getFotoDni());
        }
        
        empleado.setFotoDniBase64(fotoBase64);
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        String empleadoJson = gson.toJson(empleado);
        
        response.getWriter().write(empleadoJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
