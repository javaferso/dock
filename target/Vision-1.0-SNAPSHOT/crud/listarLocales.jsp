<%-- 
    Document   : listarLocales.jsp
    Created on : Jun 11, 2024, 2:40:29 PM
    Author     : e_jferreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Locales"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>

<table class="table">
    <thead class="table-dark">
        <tr>
            <th>Id</th>
            <th>local</th>
            <th>Formato</th>
            <th>Nombre</th>
            <th>IP</th>

        </tr>
    </thead>
    <tbody>
        <%
            List<Locales> listaLocales = (List<Locales>) request.getAttribute("listaLocales");
            for (Locales locales : listaLocales) {
        %>
        <tr>
            <td>
                <%= locales.getId()%>
            </td>
            <td>
                <%= locales.getLocal()%>
            </td>
            <td>
                <%= locales.getFormato()%>
            </td>
            <td>
                <%= locales.getNombreTienda()%>
            </td>
            <td>
                <%= locales.getIp()%>
            </td>
        </tr>
        <% }%>
    </tbody>
</table>

