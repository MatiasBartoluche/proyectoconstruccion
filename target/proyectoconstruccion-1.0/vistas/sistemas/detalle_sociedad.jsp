<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/detalle_sociedad.js"></script>
        <title>Home</title>
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

        <div id="mensajeModalDetalleSociedad" class="modal">
            <div class="modal-content">
                <span class="close"></span>
                <h2>Mensaje</h2>
                <div id="contenedorTextoModal">

                </div>
                
                <div id="mensajesAdvertencia"></div>
                
                <div class="botonesModal">
                    <button id="btnModalDetalleAceptar">Aceptar</button>
                    <button id="btnModalDetalleCerrar">Cerrar</button>
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

        <article id="detalleSociedad">
            <h2>Nueva sociedad</h2>
            <label id="labelNombre">Nombre de la sociedad</label>
            <input class="habilitacion" type="text" id="nombreDetalleSociedad" placeholder="Nombre de la sociedad" disabled>

            <div class="cuitDetalleSociedad">
                <label>CUIT</label>
                <input class="habilitacion" type="number" id="digitoGlobalDetalleSociedad" placeholder="30" disabled>
                <label id="guionCuit">-</label>
                <input class="habilitacion" type="number" id="cuerpoCuitDetalleSociedad" placeholder="12345678" disabled>
                <label id="guionCuit">-</label>
                <input class="habilitacion" type="number" id="digitoVerificadorDetalleSociedad" placeholder="1" disabled>
            </div>
            
            <label id="labelRazonSocial">Razon Social</label>
            <textarea class="habilitacion" id="razonSocialDetalleSociedad" placeholder="Razon social" disabled></textarea>
            
            <h2>Datos de contacto</h2>
            
            <div id="contenedorDireccionDetalleSociedad">
                <label>Calle</label>
                <input class="habilitacion" type="text" id="calleDetalleSociedad" placeholder="Calle" disabled>
                <label>Altura</label>
                <input class="habilitacion" type="number" id="alturaDetalleSociedad" placeholder="Numero" disabled>
                <label>Piso-Dpto</label>
                <input class="habilitacion" type="text" id="pisoDetalleSociedad" placeholder="Piso-Dpto" disabled>
                <label>Localidad</label>
                <input class="habilitacion" type="text" id="localidadDetalleSociedad" placeholder="Localidad" disabled>
                <label>Telefono</label>
                <input class="habilitacion" type="text" id="telefonoDetalleSociedad" placeholder="1123456789" disabled>
                <label>Correo electronico</label>
                <input class="habilitacion" type="email" id="mailDetalleSociedad" placeholder="correo@email.com" disabled>
            </div>
            
            <div id="botonesDetalleSociedad">
                <button id="modificarSociedad">Modificar Datos</button>
                <button id="agregarSeguro">Agregar seguro</button>
                <button id="cancelarSeguro">Cancelar seguro</button>
                <button id="guardarNuevosDatosSociedad">Guardar</button>
                <button id="cancelarDatosSociedad">Cancelar</button>
            </div>
        </article>
        
        <article id="nuevoSeguro">
            <h2>Nuevo seguro</h2>
            
            <label id="labelNombre">Nombre del seguro</label>
            <input type="text" id="nombreNuevoSeguro" placeholder="Nombre del seguro">
            
            <div class="cuitDetalleSociedad">
                <label>CUIT</label>
                <input type="number" id="digitoGlobalNuevoSeguro" placeholder="30">
                <label id="guionCuit">-</label>
                <input type="number" id="cuerpoCuitNuevoSeguro" placeholder="12345678">
                <label id="guionCuit">-</label>
                <input type="number" id="digitoVerificadorNuevoSeguro" placeholder="1">
            </div>
            
            <div id="contenedorPoliza">
                <label>Numero de poliza</label>
                <input type="text" id="numeroPoliza" placeholder="Poliza">
            </div>
            
            <div id="fechasSeguro">
                <label>Fecha de contratacion</label>
                <input type="date" id="fechaContratacion">
                <label>Fecha de vencimiento</label>
                <input type="date" id="fechaVencimiento">
            </div>
            
            <div id="contenedorBotonesSeguro">
                <button id="btnGuardarSeguro">Guardar</button>
            </div>
        </article>
        
        <article id="contenedorBotones">
            <button id="btnHistorialSeguros">Mostrar historial de seguros</button>
            <button id="btnHistorialObras">Mostrar historial de obras</button>
        </article>
        
        <article id="historialSeguros">
            <h2>Historial de seguros</h2>
            <div id="listaSeguros"></div>
        </article>
        
        <article id="historialObras">
            <h2>Historial de obras</h2>
            <div id="listaObras"></div>
        </article>     
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
       
    </body>
</html>
