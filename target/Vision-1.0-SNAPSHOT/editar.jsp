<%-- 
    Document   : editar
    Created on : Jul 25, 2023, 4:30:13 PM
    Author     : JFerreira
--%>

<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Usuario usu = (Usuario) request.getSession().getAttribute("usuEditar"); %>
        <h1>Datos Usuario</h1>
        <form action="SvUsuarios" method="POST">
            <p><label>Nombre    : </label> <input type="text" name="nombre"></p>
            <p><label>Apellido  : </label> <input type="text" name="apellido"></p>
            <p><label>Usuario   : </label> <input type="text" name="usuario"></p>
            <button type="submit" >Enviar</button>
        </form>
    </body>
</html>
