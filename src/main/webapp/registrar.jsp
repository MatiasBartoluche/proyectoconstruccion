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
        <h1>Lista de roles</h1>
        
        <%
            List<Rol> listaRoles = (List)request.getSession().getAttribute("listaRoles");
            for(Rol r : listaRoles){
        %>
                <p>---------------------------------------------------------</p>
                <p>Id Rol: <%=r.getIdRol() %> </p>
                <p>Descripcion: <%=r.getDescripcion() %> </p>
                <p>---------------------------------------------------------</p>
        <%
            }
        %>
        
        <h1>Lista de empleados</h1>
        
        <%
            List<Empleado> listaEmpleados = (List)request.getSession().getAttribute("listaEmpleados");
            for(Empleado e : listaEmpleados){
        %>
                <p>---------------------------------------------------------</p>
                <p>Legajo: <%=e.getLegajo() %> </p>
                <p>Nombres: <%=e.getNombres() %> </p>
                <p>Apellidos: <%=e.getApellidos() %> </p>
                <p>---------------------------------------------------------</p>
        <%
            }
        %>
        
        <h1>Lista de usuarios</h1>
        
        <%
            List<Usuario> listaUsuarios = (List)request.getSession().getAttribute("listaUsuarios");
            for(Usuario u : listaUsuarios){
        %>
                <p>---------------------------------------------------------</p>
                <p>Id Usuario: <%=u.getIdUsuario() %> </p>
                <p>Legajo: <%=u.getEmpleado().getLegajo() %> </p>
                <p>Nombre de usuario: <%=u.getUsuario() %> </p>
                <p>Clave: <%=u.getClave() %> </p>
                <p>Rol: <%=u.getRol().getDescripcion() %> </p>
                <p>Aprobacion: <%=u.isAprobado() %> </p>
                <p>---------------------------------------------------------</p>
        <%
            }
        %>
        
        
        <form action="SvRegistrar" method="GET">
            <button type="submit">Registrar</button>
        </form>
    </body>
</html>
