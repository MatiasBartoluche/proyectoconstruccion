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
        <script src="${pageContext.request.contextPath}/js/detalle_grupo_trabajo.js"></script>
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

        <div id="mensajeModalDetalleGrupo" class="modal">
            <div class="modal-content">
                <span class="close"></span>
                <h2>Mensaje</h2>
                <div id="contenedorTextoModal">

                </div>
                <div class="botonesModal">
                    <button id="btnModalCambiarCapataz">Cambiar</button>
                    <button id="btnModalAceptar">Aceptar</button>
                    <button id="btnModalBorrar">Eliminar</button>
                    <button id="btnModalCancelar">Cancelar</button>
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

        <article id="detalleGrupo">
            <h2>Informacion del grupo</h2>
            
            <div id="contenedorCapataz">
                <div id="contenedorDni">
                    <img id="dniCapataz" src=""/>
                </div>
                <div id="datosCapataz"></div>
                
                <div id="listaCapataces">
                    
                </div>
            </div>
            
            <div id="botonesCapataz">
                <button id="btnDetalleCapataz">Ver detalles</button>
                <button id="btnCambiarCapataz">Cambiar capataz</button>
                <button id="btnAceptarCapataz">Cambiar</button>
                <button id="btnCancelarCapataz">Cancelar</button>
            </div>

            <h2>Lista de obreros</h2>
            <div class="cabeceraListaEmpleados">
                <p>Legajo</p>
                <p>Empleado</p>
                <p>CUIL</p>
                <p>Cargo</p>
                <p>Fecha de ingreso</p>
            </div>
            <div id="contenedorIntegrantes">

            </div>
            
            <div id="contenedorNuevosEmpleados">
                <h2>Agregar nuevos empleados</h2>
                <div class="cabeceraListaEmpleados">
                    <p>Legajo</p>
                    <p>Empleado</p>
                    <p>CUIL</p>
                    <p>Cargo</p>
                    <p>Fecha de ingreso</p>
                </div>
                <div id="nuevosEmpleados"></div>
            </div>
            <div id="botonesNuevosEmpleados">
                <button id="btnAgregarEmpleados">Agregar empleados</button>
                <button id="btnAceptarEmpleados">Aceptar</button>
                <button id="btnCancelarEmpleados">Cancelar</button>
            </div>
        </article>
        
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>

    </body>
</html>
