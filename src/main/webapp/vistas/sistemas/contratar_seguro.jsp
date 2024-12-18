<%@page import="clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/contratar_seguro.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
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
                    <button id="btnModalSeguroAceptar">Aceptar</button>
                    <button id="btnModalSeguroCerrar">Cerrar</button>
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
                <label>Sociedad asegurada</label>
                <select id="selectSociedades">
                    
                </select>
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
        
        <article id="vencimientoSeguros">
            <h2>Seguros con fechas de vencimiento proximas</h2>
            
            <div class="cabeceraSeguros">
                <p>Nombre</p>
                <p>CUIT</p>
                <p>N° poliza</p>
                <p>Sociedad</p>
                <p>Vencimiento</p>
            </div>
            
            <div id="listaVencimientoSeguros">
                <h1>No hay seguros proximos a vencer</h1>
            </div>
        </article>
        
    </section>
                
    <footer class="pie">
        Footer
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
       
    </body>
</html>
