package clases;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Verifica si es una solicitud AJAX
            String requestedWith = httpRequest.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWith)) {
                chain.doFilter(request, response); // Es una solicitud AJAX, permite el acceso
            } else {
                // Bloquea el acceso o redirige
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso no permitido.");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
