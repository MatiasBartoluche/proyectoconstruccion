<%@page import="clases.Controlador"%>
<%@page import="clases.Usuario"%>
<%@page import="clases.Empleado"%>
<%@page import="clases.Rol"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
        <link type="text/css" rel="stylesheet" href="./css/estilos.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script type="text/javascript" src="./js/registrar.js"></script>
    </head>
    <body>

        <form action="registrar.jsp" method="GET">
            <p>Buscar empleado</p>
            <p>Ingrese su numero de legajo: <input type="text" placeholder="Legajo" name="buscarLegajo"></p>
            <button type="submit">Buscar</button>
        </form>
        
        <div id="resultadoBusquedaEmpleado">

            
        </div>
        
        <form action="SvRegistrar" method="POST">
            <select id="rol" name="rol">
                <!-- insertar desde javascript -->
            </select>
            
            <button type="submit">Registrar</button>
        </form>
    </body>
</html>
