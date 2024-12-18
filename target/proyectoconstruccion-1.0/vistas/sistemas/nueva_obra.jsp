<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        -->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
        <link href="${pageContext.request.contextPath}/css/boxicons-2.1.4.css" rel='stylesheet'>
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/moment.js"></script>
        <script src="${pageContext.request.contextPath}/js/nueva_obra.js"></script>
        <title>Nueva obra</title>
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

        <div id="mensajeModalNuevaObra" class="modal">
            <div class="modal-content">
                <span class="close"></span>
                <h2>Mensaje</h2>
                <div id="contenedorTextoModal">

                </div>
                
                <div id="mensajesAdvertencia"></div>
                
                <div class="botonesModal">
                    <button id="btnModalObraAceptar">Aceptar</button>
                    <button id="btnModalObraNueva">Nueva obra</button>
                    <button id="btnModalObraCerrar">Cerrar</button>
                </div>
            </div>
        </div>         
        
    <header class="cabecera">
        Header
    </header>

    <nav class="sidebar close">
        <header class="cabeceraNav">
            <div class="text logo">
                <span class="name">
                    <%= usuario.getEmpleado().getApellidos()%>, <%= usuario.getEmpleado().getNombres()%>
                </span>
                
                <span class="rol">
                    <%= usuario.getRol().getDescripcion()%>
                </span>
            </div>
            <i class="bx bx-menu toggle"></i>
        </header>

        <div class="menu-bar">
            <div class="menu">
                <ul class="menu-links">

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-food-menu icon"></i>
                            <span class="text nav-text">Sociedades</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-buildings icon"></i>
                            <span class="text nav-text">Obras</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./empleados.jsp">
                            <i class="bx bx-hard-hat icon"></i>
                            <span class="text nav-text">Empleados</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-user-plus icon"></i>
                            <span class="text nav-text">Subcontratista</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-cart-add icon"></i>
                            <span class="text nav-text">Proveedores</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-line-chart icon"></i>
                            <span class="text nav-text">Contabilidad</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->
                    
                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="../pendiente.jsp">
                            <i class="bx bx-laptop icon"></i>
                            <span class="text nav-text">Sistemas</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->
                </ul>
            </div>

            <div class="bottom-content">
                <li class="">
                    <a href="${pageContext.request.contextPath}/servlets/SvLogout">
                        <i class="bx bx-log-out icon"></i>
                        <span class="text nav-text">Salir</span>
                    </a>
                </li>

                <li class="mode">
                    <div class="sun-moon">
                        <i class="bx bx-moon icon moon"></i>
                        <i class="bx bx-sun icon sun"></i>
                    </div>

                    <span class="mode-text text">Dark</span>
                    <div class="toggle-switch">
                        <span class="switch"></span>
                    </div>
                </li>
            </div>
        </div>
    </nav>        
        
    <section class="home">
        <article id="nuevaObra">
            <h2>Datos administrativos</h2>
            <div class="datosObra" id="datosAdministrativosObra">
                <label>Expediente DGROC</label>
                <input type="text" id="expedienteDGROCnuevaObra" placeholder="DGROC">
                <label>Expediente DGFYCO</label>
                <input type="text" id="expedienteDGFYCOnuevaObra" placeholder="DGFYCO">
                <label>Nombre de la obra</label>
                <input type="text" id="nombreNuevaObra" placeholder="Nombre obra">
                <label>Superficie metros cuadrados</label>
                <input type="number" id="superficieNuevaObra" placeholder="Superficie">
            </div>
            <div class="datosObra" id="ubicacionNuevaObra">
                <label>Direccion</label>
                <input type="text" id="direccionNuevaObra" placeholder="Direccion">
                <label>Altura</label>
                <input type="text" id="alturaNuevaObra" placeholder="Altura">
                <label>Localidad</label>
                <input type="text" id="localidadNuevaObra" placeholder="Localidad">
            </div>
            <div class="datosObra" id="fechaTipoNuevaObra">
                <label>Fecha de inicio</label>
                <input type="date" id="fechaInicioObra">
                <label>Tipo de obra</label>
                <select id="selectTipoObra"></select>
            </div>
            
            <h2>Datos de la sociedad</h2>
            <div class="datosObra" id="datosSociedadNuevaObra">
                <label>Sociedad Encargada</label>
                <select id="selectSociedadesObra"></select>
                <label>CUIT</label>
                <input type="text" id="cuitSociedadNuevaObra" disabled>
                <label>Seguro</label>
                <input type="text" id="nombreSeguroNuevaObra" disabled>
                <label>Numero de poliza</label>
                <input type="number" id="polizaNuevaObra" disabled>
                <label>Contratacion</label>
                <input type="date" id="fechaVigenciaPoliza" disabled>
                <label>vencimiento</label>
                <input type="date" id="fechaVencimientoPoliza">
            </div>
            <div class="datosObra">
                <button id="btnGuardarObra">Guardar</button>
            </div>
        </article>
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
       
    </body>
</html>