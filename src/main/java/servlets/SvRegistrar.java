package servlets;

import clases.Controlador;
import clases.Empleado;
import clases.LocalDateAdapter;
import clases.Rol;
import clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRegistrar", urlPatterns = {"/SvRegistrar"})
public class SvRegistrar extends HttpServlet {
    
    Controlador control = new Controlador();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        
        String username = request.getParameter("username");
        boolean existeUsuario = false;
        
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        listaUsuarios = control.buscarListaUsuarios();
        
        for(Usuario usuario : listaUsuarios){
            if(usuario.getUsuario().equals(username)){
                existeUsuario = true;
            }
            else{
                existeUsuario = false;
            }
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // creacion de un objeto en json para enviar respuesta a la pagina
        response.getWriter().write("{\"existe\": " + existeUsuario + "}");
    }

/* -------------------------------------------------------------------------------------------------- */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Leer el cuerpo de la solicitud JSON
        StringBuilder jsonBuffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        // almaceno el string obtenido del json
        String jsonData = jsonBuffer.toString();
        // instancia de Gson, incluyo el adapter en el builder para evitar problemas de parse a objetos LocalDate
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        
        // leer el string y parse a objeto Usuario
        Usuario usuario = gson.fromJson(jsonData, Usuario.class);
        
        String claveRecibida = usuario.getClave();
        
        String salt = generarSalt();
        System.out.println(claveRecibida);
        System.out.println(salt);
        
        try {
            String claveEncriptada = encriptarClave(claveRecibida, salt);
            
            usuario.setClave(claveEncriptada); // al usuario recibido, le agrego la clave nueva
            usuario.setSalt(salt); // le agrego salt
            
            // crear usuario con el objeto recibido
            control.crearUsuario(usuario);
            // Enviar una respuesta al cliente

            //response.getWriter().write("{\"mensaje\":\"Datos recibidos correctamente\"}");
            response.getWriter().write("{\"mensaje\": true}");
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SvRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write("{\"mensaje\": false}");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // generar un salt por cada nuevo usuario
    private String generarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    // utilizo la clave que recibo de registro.jsp, el salt generado y hago una nueva encriptacion
    private String encriptarClave(String claveRecibida, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String saltedPassword = salt + claveRecibida;
        byte[] hashBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}
