<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Construcciones</title>
        <link type="text/css" rel="stylesheet" href="./css/estilos.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
        <script src="./js/index.js"></script>
    </head>
    <body class="bodyIndex">

        <div class="ingresarUsuario">
            <h1>Bienvenido al sistema</h1>
            <div id="login">  
                <input type="text" name="usuario" id="usuario" placeholder="Usuario"/>
                <input type="password" name="pass" id="pass" placeholder="Contraseña"/>
                
                <button name="ingresar" value="ingresar" id="ingresar">Ingresar</button>
                <p id="mensajeIncorrecto"></p>
            </div>
            
            <form action="SvIndex" method="GET">
                <label>Si no estas registrado, puedes crear una cuenta</label>
                <button type="submit">Crear cuenta</button>
            </form>

        </div> 
    </body>
</html>
