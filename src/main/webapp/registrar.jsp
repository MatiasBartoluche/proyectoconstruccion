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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./js/registrar.js"></script>
    </head>
    <body>

        <div id="contenidoRegistrar">

            <div id="buscarEmpleado">
                <H2>Buscar empleado</h2>
                <p>Ingrese su numero de legajo: <input type="text" placeholder="Legajo" name="buscarLegajo" id="inputLegajo"></p>
                <button id="btnBuscarEmpleado">Buscar</button>
            </div>

            <div id="resultadoBusquedaEmpleado">


            </div>

            <div id="formularioRegistro">
                <select id="rol" name="rol">
                    <!-- insertar desde javascript -->
                </select>

                <button type="submit">Registrar</button>
            </div> 
            
        </div>
        
    </body>
</html>
