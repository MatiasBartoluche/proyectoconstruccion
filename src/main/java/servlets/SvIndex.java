package servlets;

import clases.Controlador;
import clases.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvIndex", urlPatterns = {"/SvIndex"})
public class SvIndex extends HttpServlet{

    private static final long serialVersionUID = 1L;

    
    Controlador controlador = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // redirigir al formulario de creacion de nuevos usuarios
        response.sendRedirect("registrar.jsp");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //capturo el usuario y clave ingresados
        String usuarioIngresado = request.getParameter("usuario");
        String claveIngresada = request.getParameter("clave");
        
        // consulto la lista de usuarios
        List<Usuario> listaUsuarios;
        listaUsuarios = controlador.buscarListaUsuarios();
        
        // armo un map para guardar informacion y convertirlo en json
        Map <String, Object> armarJson = new HashMap<>();
        
        // inicio el map con valores para usuario no encontrado
        armarJson.put("status", "error");
        armarJson.put("message", "Usuario y/o clave incorrectos");
        
        for(Usuario usuario : listaUsuarios){
            // variable que almacenara el resultado de la clave ingresada por el usuario
            // mas el salt de la clase "Usuario"
            String claveEncriptada = null;
            String saltGuardado = usuario.getSalt();
            try {
                // utilizo la clave ingresada y el salt del usuario iterado en este momento, para generar la clave encriptada
                // y luego comparar esta variable "claveEncriptada" con la clave guardada de este usuario
                claveEncriptada = encriptarClave(claveIngresada, saltGuardado);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SvIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(usuario.usuarioExiste(usuarioIngresado, claveEncriptada)){
                    // elimino los valores del map para usuario no encontrado
                    armarJson.remove("status");
                    armarJson.remove("message");

                    // agrego valores para usuario encontrado
                    armarJson.put("status", "success");
                    armarJson.put("rol", usuario.getRol().getDescripcion());
                    armarJson.put("autorizado", usuario.isAprobado());
                
                if(usuario.isAprobado()){
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario", usuario);
                    
                    String rolUsuario = usuario.getRol().getDescripcion();
                    sesion.setAttribute("rolUsuario", rolUsuario);
                    
                    String redirectUrl = obtenerUrl(rolUsuario);
                    
                    armarJson.put("redirectUrl", redirectUrl);
                    break;
                }
                else{
                    System.out.println("No autorizado");
                    break;
                }
            }
            else{
                System.out.println("El usuario no existe");
            }
        }
        
        // instancia de objeto Gson
        Gson gson = new Gson();
        
        // convierto el map a Json
        String json = gson.toJson(armarJson);
        
        // envio el json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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

    private String obtenerUrl(String rolUsuario) {
        String url = null;
        
        if(null != rolUsuario)switch (rolUsuario) {
            case "Admin sistemas":
                url = "/proyectoconstruccion/vistas/sistemas/home.jsp";
                break;
            case "Administrativo":
                url = "/proyectoconstruccion/vistas/administrativo/home.jsp";
                break;
            case "Ayudante":
                url = "/proyectoconstruccion/vistas/ayudante/home.jsp";
                break;
            case "Contador":
                url = "/proyectoconstruccion/vistas/contador/home.jsp";
                break;
            default:
                break;
        }
        return url;
    }
}
