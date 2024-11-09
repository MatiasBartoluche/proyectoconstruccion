package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Empleado;
import clases.Jerarquia;
import clases.LocalDateAdapter;
import clases.Rol;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
        response.getWriter().write(datosJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        // Convertir JSON a objeto Java
        Empleado empleado = gson.fromJson(reader, Empleado.class);
        
        String fotoBase64 = empleado.getFotoDniBase64();
        
        if(fotoBase64 == null){
            System.out.println("++++++++++++++++++++++++++++++++ no hay foto");
        }
        else{
            byte[] foto = Base64.getDecoder().decode(empleado.getFotoDniBase64().split(",")[1]); // Eliminar el prefijo "data:image/..."

            empleado.setFotoDni(foto);
            System.out.println("++++++++++++++++++++++++++++++++ hay foto");
        }
       
        System.out.println("++++++++++++++++++++++++++++++++"+empleado);
        String enviarJson;

        if(empleado != null){
            System.out.println("----------------------- empleado recibido");
            enviarJson = "{\"mensaje\": true}";
        }
        else{
            System.out.println("----------------------- null");
            enviarJson = "{\"mensaje\": false}";
        }
        response.getWriter().write(enviarJson);
        //"{\"message\":\"Empleado registrado exitosamente!\"}"
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
