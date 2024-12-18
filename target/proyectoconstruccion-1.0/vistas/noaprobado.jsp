<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        -->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
        <link href="${pageContext.request.contextPath}/css/boxicons-2.1.4.css" rel='stylesheet'>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="../js/index.js"></script>
    </head>
    
    <body>
        <div id="noAprobado">
            <h2>Usted no tiene autorizacion para ingresar al sistema</h2>
            <p>Su cuenta esta pendiente de autorizacion.</p>
            <p>Comuniquese con el administrador de sistemas para solucionar su situacion.</p>
            <button id="btnNoAprobado">Aceptar</button>
        </div>
    </body>
</html>
