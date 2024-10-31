package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Empleado;
import clases.Estado;
import clases.Jerarquia;
import clases.Rol;
import clases.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.EmpleadoJpaController;


@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet{

    private static final long serialVersionUID = 1L;
    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
/*############################ lista de roles ################################*/
        // leer los roles existentes en la base de datos
        //List<Rol> listaRoles = new ArrayList<>();
        //listaRoles = controlador.buscarListaRoles();
        //HttpSession sesion = request.getSession();
        // guardar la lista de roles para que otros jsp puedan leerlos
        //sesion.setAttribute("listaRoles", listaRoles);
        
/*############################ lista de usuarios #############################*/
        // leer los usuarios existentes en la base de datos
        //List<Usuario> listaUsuarios = new ArrayList<>();
        //listaUsuarios = controlador.buscarListaUsuarios();
        // guardar la lista de roles para que otros jsp puedan leerlos
        //sesion.setAttribute("listaUsuarios", listaUsuarios);
       
/*############################ lista de empleados ############################*/
        // leer los empleados existentes en la base de datos
        //List<Empleado> listaEmpleados = new ArrayList<>();
        //listaEmpleados = controlador.buscarListaEmpleados();
        //sesion.setAttribute("listaEmpleados", listaEmpleados);
        // guardar la lista de roles para que otros jsp puedan leerlos
        //sesion.setAttribute("listaRoles", listaRoles);
       
        
        // redirigir al formulario de creacion de nuevos usuarios
        response.sendRedirect("registrar.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        /*Rol rolSistemas = new Rol("Admin sistemas");
        Rol rolAdministrativo = new Rol("Administrativo");
        Rol rolAyudante = new Rol("Ayudante");
        Rol rolContador = new Rol("Contador");
        
        controlador.crearRol(rolSistemas);
        controlador.crearRol(rolAdministrativo);
        controlador.crearRol(rolAyudante);
        controlador.crearRol(rolContador);*/
       
        /*Jerarquia administrativo = new Jerarquia("Administrativo");
        Jerarquia contador = new Jerarquia("Contador");
        Jerarquia ayudanteAdmin = new Jerarquia("Ayudante administrativo");
        Jerarquia adminSistemas = new Jerarquia("Admin Sistemas");
        Jerarquia ayudanteAlbanil = new Jerarquia("Ayudante albanil");
        Jerarquia oficial = new Jerarquia("Oficial");
        Jerarquia capataz = new Jerarquia("Capataz");
        Jerarquia carpintero = new Jerarquia("Carpintero");
        Jerarquia plomero = new Jerarquia("Plomero");
        Jerarquia balancin = new Jerarquia("Balancin");
        
        controlador.crearJerarquia(adminSistemas);
        controlador.crearJerarquia(administrativo);
        controlador.crearJerarquia(contador);
        controlador.crearJerarquia(ayudanteAdmin);
        controlador.crearJerarquia(ayudanteAlbanil);
        controlador.crearJerarquia(oficial);
        controlador.crearJerarquia(capataz);
        controlador.crearJerarquia(carpintero);
        controlador.crearJerarquia(plomero);
        controlador.crearJerarquia(balancin);

        
        Contrato obrero = new Contrato("Obrero");
        Contrato oficina = new Contrato("Oficina");
        Contrato subcontratado = new Contrato("Subcontratado");
        
        controlador.crearContrato(obrero);
        controlador.crearContrato(oficina);
        controlador.crearContrato(subcontratado);
        
        Estado activo = new Estado("Activo");
        Estado art = new Estado("ART");
        Estado vacaciones = new Estado("Vacaciones");
        Estado libre = new Estado("Dia libre");
        
        controlador.crearEstado(activo);
        controlador.crearEstado(art);
        controlador.crearEstado(vacaciones);
        controlador.crearEstado(libre);
        
        Empleado empleado = new Empleado();
        
        empleado.setLegajo(555);
        empleado.setJerarquia(administrativo);
        empleado.setNombres("jacinto");
        empleado.setApellidos("perez");
        empleado.setCuil("20-12345678-9");
        empleado.setCalle("calle falsa");
        empleado.setAltura(123);
        empleado.setLocalidad("Berazategui");
        empleado.setTelefono("1234567890");
        empleado.setTelefonoFamiliar("4287-1234");
        empleado.setFotoDni("");
        empleado.setFechaIngreso(LocalDate.of(2024,10,7));
        empleado.setContrato(oficina);
        empleado.setSueldoBase(100000.21);
        empleado.setEstado(activo);
        empleado.setAntiguedad(0);
        empleado.setDespido(false);
        empleado.setIdObra(0);
        empleado.setGrupo(null);
        
        controlador.crearEmpleado(empleado);
        
        /*Usuario u = new Usuario();
        
        u.setEmpleado(empleado);
        u.setUsuario("usuarioJuan1234");
        u.setClave("1234");
        u.setRol(rolSistemas);
        u.setAprobado(false);
        u.setAuditoria("");
        
        controlador.crearUsuario(u);*/
        
        //capturo el usuario y clave ingresados

        String usuarioIngresado = request.getParameter("usuario");
        String claveIngresada = request.getParameter("clave");
        // consulto la lista de usuarios
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = controlador.buscarListaUsuarios();
        int cont = 1;
        
        Map <String, Object> armarJson = new HashMap<>();
        
        for (Usuario usuario : listaUsuarios) {
            String claveGuardada = usuario.getClave();
            String saltGuardado = usuario.getSalt();
            
            System.out.println("---------------------------------------------");
            System.out.println("usuarios contados: "+cont);
            System.out.println("Usuario ingresado: "+usuarioIngresado);
            System.out.println("Clave ingresada: "+claveIngresada);
            System.out.println("Clave guardada: "+claveGuardada);
            System.out.println("Salt guardado: "+saltGuardado);
            
            cont = cont + 1;
            
            try {
                //debo hacer un hash con la clave recibida del formulario y el salt del usuario
                // y comparar el resultado con la clave ingresada
                String claveEncriptada = encriptarClave(claveIngresada, saltGuardado);
                System.out.println(claveEncriptada);
                
                if(usuario.usuarioExiste(usuarioIngresado, claveEncriptada)){
                    if(usuario.isAprobado()){
                        System.out.println("############### autenticar usuario #################");
                        System.out.println("---------------------------------------------");
                        // creacion de una sesion
                        HttpSession sesion = request.getSession();
                        // guardo la informacion del usuario
                        sesion.setAttribute("usuario", usuario);
                        // construyo un json de usuario encontrado
                        armarJson.put("mensaje", true);
                        armarJson.put("autorizado", true);
                        armarJson.put("rol", usuario.getRol().getDescripcion());
                        break;
                    }
                    else{
                        System.out.println("############### usuario no autorizado #################");
                        System.out.println("---------------------------------------------");
                        armarJson.put("mensaje", true);
                        armarJson.put("autorizado", false);
                        break;
                    }
                }
                else {
                    System.out.println("############### no exixte el usuario #################");
                    System.out.println("---------------------------------------------");
                    // construyo un json de usuario no encontrado
                    armarJson.put("mensaje", false);
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SvIndex.class.getName()).log(Level.SEVERE, null, ex);
                armarJson.put("mensaje", false);
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(armarJson);
        
        response.getWriter().write(json);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // utilizo la clave que recibo de index.jsp, el salt generado y hago una nueva encriptacion
    private String encriptarClave(String claveRecibida, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltedPassword = salt + claveRecibida;
        byte[] hashBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
/* token github */
/* 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 */