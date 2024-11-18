<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <script src="${pageContext.request.contextPath}/js/detalle_empleado.js"></script>
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
        
        <div id="mensajeModalModificarEmpleado" class="modal">
            <div class="modal-content">
                <span class="close"></span>
                <h2>Mensaje</h2>
                <div id="contenedorTextoModal">

                </div>
                <div class="botonesModal">
                    <button id="cerrarDetalleModal">Aceptar</button>
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
                        <a href="./sociedades.html">
                            <i class="bx bx-food-menu icon"></i>
                            <span class="text nav-text">Sociedades</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./obrass.html">
                            <i class="bx bx-buildings icon"></i>
                            <span class="text nav-text">Obras</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./empleados.html">
                            <i class="bx bx-hard-hat icon"></i>
                            <span class="text nav-text">Empleados</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./subcontratista.html">
                            <i class="bx bx-user-plus icon"></i>
                            <span class="text nav-text">Subcontratista</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./proveedores.html">
                            <i class="bx bx-cart-add icon"></i>
                            <span class="text nav-text">Proveedores</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->

                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./contabilidad.html">
                            <i class="bx bx-line-chart icon"></i>
                            <span class="text nav-text">Contabilidad</span>
                        </a>
                    </li> <!------------------------------- fin elemento de la lista -->
                    
                    <li class="nav-link"> <!--------------- Inicio elemento de la lista -->
                        <a href="./sistemas.html">
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

        <article id="detalleEmpleado">
            
            <form id="detalleDatosPersonales">
                <h2>Datos personales</h2>
                
                <img id="detalleFotoDni" src="" alt="detalleFotoDni">
                
                <div id="contenedorDetalleDatosPersonales">
                    <label id="labelNombres">Nombres</label>
                    <input class="habilitacion" type="text" id="detalleNombresEmpleado" disabled="true" required>
                    
                    <label id="labelApellidos">Apellidos</label>
                    <input class="habilitacion" type="text" id="detalleApellidosEmpleado" disabled="true" required>

                    <label id="labelCuil">CUIL</label>
                    <input class="habilitacion" type="number" id="detalleDigitoGlobal" disabled="true" required>
                    <label class="guion">-</label>
                    <input class="habilitacion" class="habilitacion" type="number" id="detalleCuerpoCuil" disabled="true" required>
                    <label class="guion">-</label>
                    <input class="habilitacion" type="number" id="detalleDigitoVerificador" disabled="true" required>
                </div>
                
                <input type="file" id="nuevaFotoDni" accept="image/*">

                <h2>Datos de contacto</h2>
                <div id="detalleCalle">
                    <label>Calle</label>
                    <input class="habilitacion" type="text" id="detalleCalleEmpleado" disabled="true"> 
                </div>
                
                <div id="detalleNumero">
                    <label>Numero</label>
                    <input class="habilitacion" type="number" id="detalleNumeroEmpleado" disabled="true"> 
                </div>
                
                <div id="detalleDpto">
                    <label>Piso-Dpto</label>
                    <input class="habilitacion" type="text" id="detalleDptoEmpleado" disabled="true"> 
                </div>
                
                <div id="detalleLocalidad">
                    <label>Localidad</label>
                    <input class="habilitacion" type="text" id="detalleLocalidadEmpleado" disabled="true"> 
                </div>
                
                <div id="detalleTelefonosEmpleado">
                    <label>Telefono</label>
                    <input class="habilitacion" type="text" id="detalleTelefonoEmpleado" disabled="true">
                    <label>Telefono familiar</label>
                    <input class="habilitacion" type="text" id="detalleTelefonoFamiliarEmpleado" disabled="true">
                </div>
                
                <h2>Datos Administrativos</h2>
                <div class="contenedorDetalleAdministrativo">
                    <label>Legajo</label>
                    <input class="habilitacion" type="number" id="detalleLegajoEmpleado" disabled="true" required>
                </div>
                
                <div class="contenedorDetalleAdministrativo">
                    <label>Sueldo</label>
                    <input class="habilitacion" type="number" id="detalleSueldoEmpleado" disabled="true" required>
                </div>               

                <div class="contenedorDetalleAdministrativo">
                    <label>Fecha ingreso</label>
                    <input class="habilitacion" type="date" id="detalleFechaEmpleado" disabled="true" required>
                </div>
                
                <div class="contenedorDetalleSelect">
                    <label>Contrato</label>
                    <select class="habilitacion" id="detalleContratoEmpleado" disabled="true">

                    </select>
                </div>
                
                <div class="contenedorDetalleSelect">
                    <label>Cargo</label>
                    <select class="habilitacion" id="detalleJerarquiaEmpleado" disabled="true">
                        
                    </select>
                </div>
                
                
            </form>
            <div id="contenedorBotonesModificar">
                <button id="modificarDatosEmpleado">Modificar datos</button>

                <button id="guardarNuevosDatosEmpleado">Guardar datos</button>
                <button id="cancelarNuevosDatos">Cancelar</button>
            </div>
        </article>

        <article id="botonesHistorialEmpleado">
            <button id="btnHistorialTrabajos">Ver historial de trabajo</button>
            <button id="btnHistorialART">Ver historial de ART</button>
            <button id="btnHistorialAsistencias">Ver historial de Asistencias</button>
            <button id="btnHistorialSueldos">Ver liquidaciones de sueldo</button>
            <button id="btnHistorialEPP">Ver entregas de EPP</button>
        </article>
        
        <article class="historialEmpleado" id="historialTrabajos">
            <h2>Historial de trabajo</h2>
        </article>
        
        <article class="historialEmpleado" id="historialART">
            <h2>Historial ART</h2>
        </article>
        
        <article class="historialEmpleado" id="historialAsistencias">
            <h2>Historial de asistencias</h2>
        </article>
        
        <article class="historialEmpleado" id="historialSueldos">
            <h2>Liquidaciones de sueldo</h2>
        </article>
        
        <article class="historialEmpleado" id="historialEPP">
            <h2>Entregas de EPP</h2>
        </article>
        
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>

    </body>
</html>
