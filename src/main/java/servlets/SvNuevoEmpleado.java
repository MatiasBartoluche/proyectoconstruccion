package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Empleado;
import clases.EstadoEmpleado;
import clases.GrupoTrabajo;
import clases.Jerarquia;
import clases.LocalDateAdapter;
import clases.RedimensionarImagen;
import clasesDTO.GrupoTrabajoDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        
        // realizo la consulta a la base de datos
        ArrayList<Jerarquia> listaJerarquias = control.buscarListaJerarquias();
        ArrayList<Contrato> listaContratos = control.buscarListaContratos();
        ArrayList<EstadoEmpleado>listaEstados = control.buscarListaEstados();
        ArrayList<GrupoTrabajo> listaGrupos = control.buscarListaGruposTrabajo();
        
        ArrayList<GrupoTrabajoDTO> gruposDTO = control.convertirListaGruposTrabajoDTO(listaGrupos);

        // creacion de un HashMap para guardar listas como pares clave-valor
        Map<String, Object> armarJson = new HashMap<>();
        armarJson.put("jerarquias", listaJerarquias);
        armarJson.put("contratos", listaContratos);
        armarJson.put("estados", listaEstados);
        armarJson.put("grupos", gruposDTO);
        
        // convertir la lista de a Json
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String respuestaJson = gson.toJson(armarJson);

        // enviar el Json a la pagina jsp
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
}
