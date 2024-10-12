package servlets;

import clases.Contrato;
import clases.Controlador;
import clases.Empleado;
import clases.Estado;
import clases.Jerarquia;
import clases.Rol;
import clases.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.EmpleadoJpaController;


@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet {
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
/*############################ lista de roles ################################*/
        // leer los roles existentes en la base de datos
        List<Rol> listaRoles = new ArrayList<>();
        listaRoles = controlador.buscarListaRoles();
        HttpSession sesion = request.getSession();
        // guardar la lista de roles para que otros jsp puedan leerlos
        sesion.setAttribute("listaRoles", listaRoles);
        
/*############################ lista de usuarios #############################*/
        // leer los usuarios existentes en la base de datos
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = controlador.buscarListaUsuarios();
        // guardar la lista de roles para que otros jsp puedan leerlos
        sesion.setAttribute("listaUsuarios", listaUsuarios);
       
/*############################ lista de empleados ############################*/
        // leer los empleados existentes en la base de datos
        List<Empleado> listaEmpleados = new ArrayList<>();
        listaEmpleados = controlador.buscarListaEmpleados();
        sesion.setAttribute("listaEmpleados", listaEmpleados);
        // guardar la lista de roles para que otros jsp puedan leerlos
        sesion.setAttribute("listaRoles", listaRoles);
       
        
        // redirigir al formulario de creacion de nuevos usuarios
        response.sendRedirect("registrar.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Rol rolSistemas = new Rol("Admin sistemas");
        Rol rolAdministrativo = new Rol("Administrativo");
        Rol rolAyudante = new Rol("Ayudante");
        Rol rolContador = new Rol("Contador");
        
        controlador.crearRol(rolSistemas);
        controlador.crearRol(rolAdministrativo);
        controlador.crearRol(rolAyudante);
        controlador.crearRol(rolContador);
        
        Jerarquia administrativo = new Jerarquia("Administrativo");
        Jerarquia contador = new Jerarquia("Contador");
        Jerarquia ayudanteAdmin = new Jerarquia("Ayudante administrativo");
        Jerarquia adminSistemas = new Jerarquia("Admin Sistemas");
        Jerarquia ayudanteAlbanil = new Jerarquia("Ayudante albanil");
        Jerarquia oficial = new Jerarquia("Oficial");
        Jerarquia capataz = new Jerarquia("Capataz");
        Jerarquia carpintero = new Jerarquia("Carpintero");
        Jerarquia plomero = new Jerarquia("Plomero");
        Jerarquia balancin = new Jerarquia("Balancin");
        
        /*controlador.crearJerarquia(adminSistemas);
        controlador.crearJerarquia(administrativo);
        controlador.crearJerarquia(contador);
        controlador.crearJerarquia(ayudanteAdmin);
        controlador.crearJerarquia(ayudanteAlbanil);
        controlador.crearJerarquia(oficial);
        controlador.crearJerarquia(capataz);
        controlador.crearJerarquia(carpintero);
        controlador.crearJerarquia(plomero);
        controlador.crearJerarquia(balancin);*/

        
        Contrato obrero = new Contrato("Obrero");
        Contrato oficina = new Contrato("Oficina");
        Contrato subcontratado = new Contrato("Subcontratado");
        
        /*controlador.crearContrato(obrero);
        controlador.crearcontrato(oficina);
        controlador.crearContrato(subcontratado);*/
        
        Estado activo = new Estado("Activo");
        Estado art = new Estado("ART");
        Estado vacaciones = new Estado("Vacaciones");
        Estado libre = new Estado("Dia libre");
        
        /*controlador.crearEstado(activo);
        controlador.crearEstado(art);
        controlador.crearEstado(vacaciones);
        controlador.crearEstado(libre);*/
        
        Empleado empleado = new Empleado();
        
        empleado.setLegajo(1234);
        empleado.setJerarquia(administrativo);
        empleado.setNombres("juan");
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
        
        Usuario usuario = new Usuario();
        
        usuario.setEmpleado(empleado);
        usuario.setUsuario("usuarioJuan1234");
        usuario.setClave("1234");
        usuario.setRol(rolSistemas);
        usuario.setAprobado(false);
        usuario.setAuditoria("");
        
        controlador.crearUsuario(usuario);
        
        
        /*
        if("admin".equals(usuario) && "1234".equals(pass)){
            System.out.println("bienvenido");
            response.sendRedirect("home.jsp");
            
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario); // guardo el nombre de usuario
        }
        else{
            System.out.println("usuario y/o conrtasenai incorrectos");
            response.sendRedirect("index.jsp?error=1");
        }*/
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

/* token github */
/* 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 */