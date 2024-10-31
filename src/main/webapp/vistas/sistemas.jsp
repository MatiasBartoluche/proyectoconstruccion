<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>

        <%@ page session="true" %>
        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario"); // recupero el nombre de usuario
            if (usuario == null) {
                // Si no hay sesión, redirige al login
                response.sendRedirect("index.jsp");
            }
        %>
        <h1>Bienvenido, <%= usuario.getEmpleado().getApellidos() %>, <%=usuario.getEmpleado().getNombres() %></h1>
        <h1> <%=usuario.getRol().getDescripcion() %> </h1>
        
        <p>pagina sistemas</p>
        
        <a href="../SvLogout">Cerrar sesión</a>
        
    </body>
</html>
