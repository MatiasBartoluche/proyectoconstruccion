<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Construcciones</title>
        <link type="text/css" rel="stylesheet" href="./css/estilos.css">
    </head>
    <body class="bodyIndex">

        <div class="ingresarUsuario">
            <h1>Bienvenido al sistema</h1>
            <form>
                <!--placeholder texto de ejemplo para cada input-->
                <input type="text" name="usuario" placeholder="Usuario"/>
                <input type="password" name="pass" placeholder="Contraseña"/>
                <button type="submit">Ingresar</button>
            </form>
            
            <form>
                <label>Si no estas registrado, puedes crear una cuenta</label>
                <button>Crear cuenta</button>
            </form>

        </div>
        
    </body>
</html>
