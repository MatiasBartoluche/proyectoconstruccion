package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.GrupoTrabajo;
import clases.LocalDateAdapter;
import clases.Subcontratista;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvGrupoTrabajo", urlPatterns = {"/SvGrupoTrabajo"})
public class SvGrupoTrabajo extends HttpServlet {

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
        
        // capturo el legajo ingresado
        String mensaje = request.getParameter("mensaje");
        
        if("Capataz".equals(mensaje)){
            armarGrupoTrabajo(response);
        }
        else if("Subcontratista".equals(mensaje)){
            armarGrupoSubcontratista(response);
        }
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
        
        GrupoTrabajo grupoRecibido = gson.fromJson(leerDatos, GrupoTrabajo.class);
        
        String respuestaJson;
        
        try{
            //primero guardo el grupo, para generar un id
            controlador.crearGrupoTrabajo(grupoRecibido);
            
            // recupero el empleado capataz de la base de datos
            Empleado capataz = controlador.buscarEmpleado(grupoRecibido.getCapataz().getId());
            // guardo el grupo en el capataz
            capataz.setGrupo(grupoRecibido);
            // actualizo los datos del capataz en la base de datos
            controlador.editarEmpleado(capataz);
            
            // por cada empleado de la lista del grupo
            for(Empleado empleado : grupoRecibido.getListaEmpleados()){
                // recupero el empleado de la base de datos
                int idEmpleado = empleado.getId();
                Empleado actualizar = controlador.buscarEmpleado(idEmpleado);
                // guardo el grupo en el empleado
                actualizar.setGrupo(grupoRecibido);
                // actualizo los datos en la base de datos
                controlador.editarEmpleado(actualizar);
            }

            respuestaJson = "{\"mensaje\": \"El grupo de trabajo se ha creado con exito\"}";
        }
        catch(Exception e){
            respuestaJson = "{\"mensaje\": \"No se pudo guardar el grupo de trabajo\", \"error\": \" "+e+" \"}";
        }   
        response.getWriter().write(respuestaJson);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void armarGrupoTrabajo(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No existen empleados y/o capataces para armar un grupo de trabajo\"}";
        
        ArrayList<Empleado> listaEmpleados = controlador.buscarListaEmpleados();
        
        ArrayList<Empleado> capataces = new ArrayList<>();
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        // si la lista de empleados no esta vacia
        if(listaEmpleados != null){
            for(Empleado emp : listaEmpleados){
                // empleado, capataz contrato "obrero" y que no pertenezca a un grupo
                if("Capataz".equals(emp.getJerarquia().getDescripcion())
                        && "Obrero".equals(emp.getContrato().getDescripcion())
                        && emp.getGrupo() == null){
                    capataces.add(emp);
                }
                // empleado contrato "obrero" que no sea capataz y no pertenezca a un grupo
                else if("Obrero".equals(emp.getContrato().getDescripcion())
                        && !"Capataz".equals(emp.getJerarquia().getDescripcion())
                        && emp.getGrupo() == null){
                    empleados.add(emp);
                }
            }
            // ambas listas debe contener al menos un elemento para poder crear un grupo
            if(!capataces.isEmpty() && !empleados.isEmpty()){
                CargarDatos datos = new CargarDatos();
                datos.setCapataces(capataces);
                datos.setEmpleados(empleados);

                Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

                respuestaJson = gson.toJson(datos);
            }
        }
        
        response.getWriter().write(respuestaJson);
    }
    
    public void armarGrupoSubcontratista(HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String respuestaJson = "{\"mensaje\": \"No existen empleados y/o Subcontratistas para armar un grupo de trabajo\"}";
        
        
        
        //response.getWriter().write();
    }
    
// clase que se usa de fotma local para enviar dos listas a traves de un json
    private class CargarDatos{
        List<Empleado> capataces;
        List<Empleado> empleados;
        List<Subcontratista>subcontratistas;
        
        public CargarDatos(){}

        public List<Empleado> getCapataces() {
            return capataces;
        }

        public void setCapataces(List<Empleado> Capataces) {
            this.capataces = Capataces;
        }

        public List<Empleado> getEmpleados() {
            return empleados;
        }

        public void setEmpleados(List<Empleado> empleados) {
            this.empleados = empleados;
        }

        public List<Subcontratista> getSubcontratistas() {
            return subcontratistas;
        }

        public void setSubcontratistas(List<Subcontratista> subcontratistas) {
            this.subcontratistas = subcontratistas;
        }

        
    }
}
