<%-- 
    Document   : listarUsuarios
    Created on : Aug 30, 2023, 12:05:16 AM
    Author     : JFerreira
--%>

<%@page import="logica.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Id</th>
            <th>Apellido</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
            for (Usuario usuario : listaUsuarios) {
        %>
        <tr>
            <td><%= usuario.getNombre()%></td>
            <td><%= usuario.getIdUsuario()%></td>
            <td><%= usuario.getApellido()%></td>
        </tr>
        <% }%>
    </tbody>
</table>

