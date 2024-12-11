package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import clases.RedimensionarImagen;
import clasesDTO.EmpleadoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
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
        
        String respuestaJson = "{\"mensaje\": \"No se ha encontrado empleados\"}";
        // busco los empleados
        ArrayList<Empleado> empleados = controlador.buscarListaEmpleados();
        if(empleados != null){
            // convierto la lista de empleados a lista de EmpleadoDTO
            ArrayList<EmpleadoDTO> empleadosDTO = controlador.convertirListaAEmpleadosDTO(empleados);

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            // convierto la lista de EmpleadoDTO a json
            respuestaJson = gson.toJson(empleadosDTO);
            // envio larespuesta en json
        }
        response.getWriter().write(respuestaJson);
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
            RedimensionarImagen rImg = new RedimensionarImagen();
            String fotoBase64 = empleadoRecibido.getFotoDniBase64();
            byte[] foto = rImg.redimensionarImagenBase64(fotoBase64, 300, 300);

            empleadoRecibido.setFotoDni(foto);
        }
        
        String enviarJson;
        Empleado verificarEmpleado = controlador.buscarEmpleadoPorLegajo(empleadoRecibido.getLegajo());
      
        if(verificarEmpleado == null){
            controlador.editarEmpleado(empleadoRecibido);
            enviarJson = "{\"status\": true,  \"mensaje\":\"Los datos del empleado han sido actualizados\"}";
        }
        else{
            // si los legajos coinciden y los id coinciden
            if(verificarEmpleado.getLegajo() == empleadoRecibido.getLegajo()
                    && verificarEmpleado.getId() == empleadoRecibido.getId()){
                        controlador.editarEmpleado(empleadoRecibido);
                        enviarJson = "{\"status\": true,  \"mensaje\":\"Los datos del empleado han sido actualizados\"}";
            }
            else{
                enviarJson = "{\"status\": false,  \"mensaje\":\"El legajo ingresado coincide con el legajo de un empleado ya registrado\"}";
            }
        }
        response.getWriter().write(enviarJson);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
