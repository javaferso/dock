<%-- 
    Document   : listarServidores
    Created on : Jun 11, 2024, 4:40:52 PM
    Author     : e_jferreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Servidores"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Tiendas</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>Id</td>
                    <td>Local</td>
                    <td>Nombre</td>
                    <td>Ip generica</td>
                    <td>Ip enlace</td>
                    <td>Direccion</td>
                    <td>Ciudad</td>
                    <td>Formato</td>
                    <td>Ip virtual</td>
                    <td>Estado Ip</td>
                    <td>Estado enlace</td>
                    <td>Ultima revision</td>
                </tr>
            </thead>
            
            <tbody>
                <% 
                    List<Servidores> listaServidores = (List<Servidores>) request.getAttribute("listaServidores");
                    for (Servidores servidores: listaServidores) { 
                %>
                <tr>
                    <td>
                        <%= servidores.getId() %>
                    </td>
                    <td>
                        <%= servidores.getLocal()%>
                    </td>
                    <td>
                        <%= servidores.getNombreTienda() %>
                    </td>
                    <td>
                        <%= servidores.getIpAddress() %>
                    </td>
                    <td>
                        <%= servidores.getIpEnlace() %>
                    </td>
                    <td>
                        <%= servidores.getDireccion() %>
                    </td>
                    <td>
                        <%= servidores.getCiudad() %>
                    </td>
                    <td>
                        <%= servidores.getFormato() %>
                    </td>
                    <td>
                        <%= servidores.getIpVirtual() %>
                    </td>
                    <td>
                        <%= servidores.getEstadoIp() %>
                    </td>
                    <td>
                        <%= servidores.getEstadoEnlace() %>
                    </td>
                    <td>
                        <%= servidores.getUpdatedAt() %>
                    </td>
                    
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
