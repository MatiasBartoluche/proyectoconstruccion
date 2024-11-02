package servlets;

import clases.Usuario;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession sesion = request.getSession(false);
        
        //Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        
        String rol = (sesion != null) ? (String) sesion.getAttribute("rolUsuario") : null;
        String uri = request.getRequestURI();

        // excluir la pagina de login y su servlet del filtro
        if (uri.contains("/index.jsp") || uri.contains("/SvIndex")) {
            chain.doFilter(req, res);
            return;
        }
        
        // excluir la pagina de registro y su servlet del filtro
        if (uri.contains("/registrar.jsp") || uri.contains("/SvRegistrar")) {
            chain.doFilter(req, res);
            return;
        }
        
        // excluir la pagina de usuarios no autorizados
        if (uri.contains("/noaprobado.jsp")) {
            chain.doFilter(req, res);
            return;
        }
        
        if(uri.contains("/SvLogout")){
            chain.doFilter(req, res);
            return;
        }
        
        // Permitir acceso a recursos estáticos (CSS, JS, imágenes, etc)
        if (uri.startsWith(request.getContextPath() + "/css") || uri.startsWith(request.getContextPath() + "/js") ) {
            chain.doFilter(req, res);
            return;
        }
        
        // Si no hay sesión o rol, redirigir a la página de login
        if (sesion == null || rol == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        if (uri.contains("/sistemas") && "Admin sistemas".equals(rol)) {
            chain.doFilter(req, res); // Usuario Admin sistemas autorizado
        }
        else if (uri.contains("/administrativo") && "Administrativo".equals(rol)) {
            chain.doFilter(req, res); // Administrativo autorizado
        }
        else if (uri.contains("/ayudante") && "Ayudante".equals(rol)) {
            chain.doFilter(req, res); // Ayudante autorizado
        }
        else if (uri.contains("/contador") && "Contador".equals(rol)) {
            chain.doFilter(req, res); // Usuario regular autorizado
        }
        else {
            response.sendRedirect("denegado.jsp"); // Usuario no autorizado
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}