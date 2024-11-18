package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEmpleados", urlPatterns = {"/SvEmpleados"})
public class SvEmpleados extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<Empleado> empleados = controlador.buscarListaEmpleados();
        
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        String listaEmpleados = gson.toJson(empleados);
        
        response.getWriter().write(listaEmpleados);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // request.getReader retorna un Reader que permite leer la peticion
        BufferedReader leerDatos = request.getReader();
        
        //construyo el Gson con LocalDateAdapter
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        // Convertir JSON a objeto Java y guardarlo en la variable de tipo Empleado
        Empleado empleadoRecibido = gson.fromJson(leerDatos, Empleado.class);
  
        if(empleadoRecibido.getFotoDniBase64() != null){
            String fotoBase64 = empleadoRecibido.getFotoDniBase64();
            byte[] foto = Base64.getDecoder().decode(fotoBase64);

            empleadoRecibido.setFotoDni(foto);
        }
        
      
        controlador.editarEmpleado(empleadoRecibido);
        String enviarJson = "{\"status\": true,  \"mensaje\":\"Los datos del empleado han sido actualizados\"}";
        
        response.getWriter().write(enviarJson);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
