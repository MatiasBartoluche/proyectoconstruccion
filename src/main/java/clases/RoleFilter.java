package clases;

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
        
        String rol = (sesion != null) ? (String) sesion.getAttribute("rolUsuario") : null;
        String uri = request.getRequestURI();

// ----------------------- exclusion del filto para recursos generales --------------------------------

        // excluir las paginas del filtro
        if (uri.contains("/index.jsp") || uri.contains("/registrar.jsp")
                || uri.contains("/noaprobado.jsp") || uri.contains("/pendiente.jsp")) {
            chain.doFilter(req, res);
            return;
        }
        
        // excluir los servlet del filtro
        if ( uri.contains("/SvIndex") ||  uri.contains("/SvRegistrar") || uri.contains("/SvLogout") 
                || uri.contains("/SvResultadoBuscarLegajo") || uri.contains("/proyectoconstruccion/SvNuevoEmpleado")
                || uri.contains("/proyectoconstruccion/SvEmpleados") || uri.contains("/proyectoconstruccion/SvDetalleEmpleado")
                || uri.contains("/proyectoconstruccion/SvJerarquias") || uri.contains("/proyectoconstruccion/SvGrupoTrabajo")
                || uri.contains("/proyectoconstruccion/SvDetalleGrupoTrabajo") || uri.contains("/proyectoconstruccion/SvSociedades")
                || uri.contains("/proyectoconstruccion/SvObras")){
            chain.doFilter(req, res);
            return;
        }
        
        // Permitir acceso a recursos estáticos (CSS, JS, imágenes, etc)
        if (uri.startsWith(request.getContextPath() + "/css")
                || uri.startsWith(request.getContextPath() + "/js")
                || uri.startsWith(request.getContextPath() + "/img")) {
            chain.doFilter(req, res);
            return;
        }
        
        // Si no hay sesión o rol, redirigir a la página de login
        if (sesion == null || rol == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
// ------------------------ restringir acceso a otras paginas segun el rol de usuario -----------------------
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