<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Construcciones</title>
        <link type="text/css" rel="stylesheet" href="./css/estilos.css">
    </head>
    <body class="bodyIndex">

        <!--
        <div class="ingresarUsuario">
            <h1>Bienvenido al sistema</h1>
            <form action="SvIndex" method="POST">
                
                <input type="text" name="usuario" placeholder="Usuario"/>
                <input type="password" name="pass" placeholder="Contraseña"/>
                <button type="submit" name="verificar" value="Verificar">Ingresar</button>
            </form>
            
            <form action="SvIndex" method="GET">
                <label>Si no estas registrado, puedes crear una cuenta</label>
                <button type="submit">Crear cuenta</button>
            </form>

        </div>
        -->
        
        <form action="SvIndex" method="POST">
            <input type="number" name="id" placeholder="id"/>
            <input type="text" name="nombre" placeholder="nombre"/>
            <button type="submit" name="verificar" value="Verificar">Ingresar</button>
        </form>
        
    </body>
</html>
