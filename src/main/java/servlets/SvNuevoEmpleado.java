package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Empleado;
import clases.Jerarquia;
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

@WebServlet(name = "SvNuevoEmpleado", urlPatterns = {"/SvNuevoEmpleado"})
public class SvNuevoEmpleado extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
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
        
        List<Jerarquia> listaJerarquias;
        List<Contrato> listaContratos;
        // realizo la consulta a la base de datos
        listaJerarquias = control.buscarListaJerarquias();
        listaContratos = control.buscarListaContratos();
        
        // instancia de la clase "CargarDatos" que recibe dos listas como parametros
        // para enviarlas a la pagina
        CargarDatos datos = new CargarDatos();
        
        datos.setContratos(listaContratos);
        datos.setJerarquias(listaJerarquias);
        
        // convertir la lista de a Json
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

        String enviarJson;

        // si el empleado recibido no es nulo
        if(empleadoRecibido != null){
            // verifico que no exista un empleado con legajo igual al legajo recibido
            Empleado buscarEmpleadoPorLegajo = control.buscarEmpleado(empleadoRecibido.getLegajo());
            if(buscarEmpleadoPorLegajo == null){
                // si no encuentra ningun empleado con legajo igual al legajo recibido, crea un nuevo empleado
                control.crearEmpleado(empleadoRecibido);
                enviarJson = "{\"status\": true, \"mensaje\": \"El empleado fue registrado exitosamente\"}";
            }
            else{
                enviarJson = "{\"status\": false,  \"mensaje\":\"El numero de legajo ingresado ya esta asociado a otro empleado\"}";
            }
        }
        else{
            enviarJson = "{\"status\": null,\"mensaje\": \" Ha ocurrido un error al enviar los datos al servidor \"}";
        }
        response.getWriter().write(enviarJson);
        //"{\"message\":\"Empleado registrado exitosamente!\"}"
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // clase que se usa de fotma local para enviar dos listas a traves de un json
    private class CargarDatos{
        List<Jerarquia> jerarquias;
        List<Contrato> contratos;
        
        public CargarDatos(){}

        public List<Jerarquia> getJerarquias() {
            return jerarquias;
        }

        public void setJerarquias(List<Jerarquia> jerarquias) {
            this.jerarquias = jerarquias;
        }

        public List<Contrato> getContratos() {
            return contratos;
        }

        public void setContratos(List<Contrato> contratos) {
            this.contratos = contratos;
        }
    }
}
