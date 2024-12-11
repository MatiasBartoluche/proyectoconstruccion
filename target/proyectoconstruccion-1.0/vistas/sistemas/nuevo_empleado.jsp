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
        <script src="${pageContext.request.contextPath}/js/nuevo_empleado.js"></script>
    </head>
    <body>

        <div id="mensajeModal" class="modal">
            <div class="modal-content">
                <span class="close"></span>
                <h2>Mensaje del servidor</h2>
                <div id="contenedorTextoModal">
                    <p id="mensajeModalTexto"></p>
                </div>
                <div class="botonesModal">

                </div>
            </div>
        </div>
        
        <%@ page session="true" %>
        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario"); // recupero el nombre de usuario
            if (usuario == null) {
                // Si no hay sesión, redirige al login
                response.sendRedirect("index.jsp");
            }
        %>
        
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
                        <a href="./sociedades.jsp">
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

        <form class="formulario" id="formularioNuevoEmpleado">
            <h2>Datos personales</h2>
            <div class="datosPersonales">
                <div id="contenedorNombres">
                    <label>Nombres</label>
                    <input type="text" name="nombresEmpleado" placeholder="Nombres" id="nombresEmpleado">
                    <p></p>
                </div>

                <div id="contenedorApellidos">
                    <label>Apellidos</label>
                    <input type="text" name="apellidosEmpleado" placeholder="Apellidos" id="apellidosEmpleado">
                    <p></p>
                </div>
                
                <div id="contenedorCuil">
                    <label>CUIL</label>
                    <input type="number" name="digitoGlobal" id="digitoGlobal" placeholder="30" min="1" max="2">
                    <label id="guion1">-</label>
                    <input type="number" name="cuerpoCUIL" id="cuerpoCUIL" placeholder="12345678">
                    <label id="guion2">-</label>
                    <input type="number" name="digitoVerificador" id="digitoVerificador" placeholder="9" min="1" max="1">
                    <div></div>
                </div>
            </div>
            
            <h2>Cargar foto</h2>
            <div class="cargarDNI">
                <input type="file" id="dniEmpleado" name="dniEmpleado" accept="image/*" />
            </div>


            <h2>Datos de contacto</h2>
            <div class="datosContacto">
                <div class="contenido1">
                    <label>Calle</label>
                    <input type="text" placeholder="Calle" id="calleEmpleado" name="calleEmpleado">
    
                    <label>Numero</label>
                    <input id="numeroDomicilioEmpleado" type="number" placeholder="numero" name="numeroDomicilioEmpleado">
    
                    <label>Dpto-piso</label>
                    <input id="pisoEmpleado" type="text" placeholder="piso" name="pisoEmpleado">
                    
                    <label>Localidad</label>
                    <input id="localidadEmpleado" type="text" placeholder="Localidad" name="localidadEmpleado">
                </div>

                <label>Telefono</label>
                <input type="text" placeholder="Telefono" id="telefonoEmpleado" name="telefonoEmpleado">

                <label>Telefono de familiar</label>
                <input type="text" placeholder="Telefono" id="telefonoFamiliarEmpleado" name="telefonoFamilarEmpleado">
            </div>

            <h2>Datos Administrativos</h2>
            
            <div class="datosAdministrativos">
                <div id="contenedorJerarquia">
                <label>Cargo jerarquico</label>
                    <select id="jerarquia" name="jerarquia">
                        <!-- select vacio, los tag option se cargaran por jquery -->
                    </select>
                </div>

                <div id="contenedorContrato">
                <label>Contrato</label>
                    <select id="contrato" name="contrato">
                        <!-- select vacio, los tag option se cargaran por jquery -->
                    </select>
                </div>
                
                <div id="contenedorEstadoEmpleado">
                <label>Estado</label>
                    <select id="estadoEmpleado" name="estado">
                        <!-- select vacio, los tag option se cargaran por jquery -->
                    </select>
                </div>
                
                <div id="contenedorLegajo">
                    <label>N° legajo</label>
                    <input id="legajo" type="number" placeholder="Legajo" name="legajo">
                    <p></p>
                </div>
                
                <div id="contenedorFechaIngreso">
                    <label>Fecha de ingreso</label>
                    <input type="date" name="fechaIngreso" id="fechaIngreso">
                    <p></p>
                </div>
                
                <div id="contenedorSalario">
                    <label>Salario</label>
                    <input id="salarioEmpleado" type="number" placeholder="Salario" name="salario">
                    <p></p>
                </div>
                
            </div>

            <button type="button" id="guardarNuevoEmpleado">Guardar</button>
        </form>
        
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>

    </body>
</html>
