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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
        <script src="./js/registrar.js"></script>
    </head>
    <body>

        <div id="contenidoRegistrar">

            <div id="buscarEmpleado">
                <h2>Buscar empleado</h2>
                <div>
                    <label>Ingrese su numero de legajo</label>
                    <input type="number" placeholder="Legajo" name="buscarLegajo" id="inputLegajo">
                </div>
                <button id="btnBuscarEmpleado">Buscar</button>
            </div>

            <div id="resultadoBusquedaEmpleado"></div>

            <div id="formularioRegistro">
                <p>Ingrese su usuario y clave de acceso, dicho usuario y clave deben estar compuestos de al menos ocho caracteres</p>
                <p>Ingrese su usuario <input type="text" id="usuario" name="usuario" placeholder="Usuario" required></p>
                <p id="mensajeUsuario"></p>
                
                <p>Ingrese su clave <input type="password" id="clave" name="clave" placeholder="clave" required></p>
                <p id="mensajeClave"></p>
                
                <p>Repita su clave <input type="password" id="repetirClave" name="repetirClave" placeholder="Repita su clave" required></p>
                <p id="mensajeRepetirClave"></p>
                
                <p>Ingrese el cargo a desarrollar en el sistema</p>
                <select id="rol" name="rol">
                    <!-- insertar desde javascript -->
                </select>
                <p id="mensajeCompletar"></p>
                <button id="registrar">Registrar</button>
            </div> 
            
            <div id="mensajeCreacion">
                <p>El usuario fue creado con exito</p>
                <button id="btnUsuarioCreado">Aceptar</button>
            </div>
        </div>
        
    </body>
</html>
