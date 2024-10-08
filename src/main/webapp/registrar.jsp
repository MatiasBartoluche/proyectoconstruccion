<%@page import="clases.Controlador"%>
<%@page import="clases.Usuario"%>
<%@page import="clases.Empleado"%>
<%@page import="clases.Rol"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="registrar.jsp" method="GET">
            <p>Buscar empleado</p>
            <p>Ingrese su numero de legajo: <input type="text" placeholder="Legajo" name="buscarLegajo"></p>
            <button type="submit">Buscar</button>
        </form>
        
        <div class="resultadoBusquedaEmpleado">

            <%
                String legajo = request.getParameter("buscarLegajo");
                
                if (legajo != null && !legajo.trim().isEmpty()) {
                    System.out.println("################## legajo"+legajo);
                    try{
                        Controlador controlador = new Controlador();
                        Empleado empleado = controlador.buscarEmpleado(Integer.parseInt(legajo));
                        
                        if(empleado != null){
                            %>
                                <p>Nombre: <%= empleado.getNombres() %></p>
                            <%
                        }
                        else{
                            %>
                                <p>No se encontro ningun empleado con ese legajo</p>
                            <%
                        }
                    }
                    catch(Exception e){
                        System.out.println("############################# error"+e);

                    }
                }
                else{
                    System.out.println("################## no se encontro ningun empleado");
            %>
                    <p>Ingrese su legajo</p>
            <%
                }
            %>
            
        </div>
        
        <form action="SvRegistrar" method="POST">
            <select id="rol" name="rol">
            <%
                List<Rol> listaRoles = (List)request.getSession().getAttribute("listaRoles");
                for(Rol r : listaRoles){
            %>
            <option value="<%=r.getIdRol() %>"> <%=r.getDescripcion() %> </option>

            <%
                }
            %>
            </select>
            
            <button type="submit">Registrar</button>
        </form>
    </body>
</html>
