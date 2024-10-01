<%-- 
    Document   : home
    Created on : 1 oct 2024, 4:40:01 p. m.
    Author     : Bartoluche
--%>

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
            String username = (String) session.getAttribute("usuario"); // recupero el nombre de usuario
            if (username == null) {
                // Si no hay sesión, redirige al login
                response.sendRedirect("index.jsp");
            }
        %>
        <h1>Bienvenido, <%= username %>!</h1>
        <a href="SvLogout">Cerrar sesión</a>
        
    </body>
</html>
