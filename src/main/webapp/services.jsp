<%-- 
    Document   : services
    Created on : Jul 3, 2024, 1:16:50 PM
    Author     : JFerreira
--%>

<%@page import="logica.Services"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Servicios</title>
    <link rel="stylesheet" href="css/style.css" />
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Listado de Servicios</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Mes</th>
            <th>Formato</th>
            <th>Inc</th>
            <th>SAP</th>
            <th>Tienda</th>
            <th>Detalle</th>
            <th>Monto</th>
            <th>Moneda</th>
            <th>Proveedor</th>
            <th>F. Autorizar</th>
            <th>OC</th>
            <th>F. Envío Prov</th>
            <th>HES</th>
            <th>Sociedad</th>
            <th>Orden Estadística</th>
            <th>Texto Breve</th>
            <th>Cotización</th>
            <th>Activo</th>
            <th>Fecha Creación</th>
            <th>Fecha Actualización</th>
            <th>Fecha Cierre</th>
        </tr>
        <%
            List<Services> servicesList = (List<Services>) request.getAttribute("services");
            if (servicesList != null) {
                for (Services service : servicesList) {
        %>
        <tr>
            <td><%= service.getId() %></td>
            <td><%= service.getTipo() %></td>
            <td><%= service.getMes() %></td>
            <td><%= service.getFormato() %></td>
            <td><%= service.getInc() %></td>
            <td><%= service.getSap() %></td>
            <td><%= service.getTienda() %></td>
            <td><%= service.getDetalle() %></td>
            <td><%= service.getMonto() %></td>
            <td><%= service.getMoneda() %></td>
            <td><%= service.getProveedor() %></td>
            <td><%= service.getfAutorizar() %></td>
            <td><%= service.getOc() %></td>
            <td><%= service.getfEnvioProv() %></td>
            <td><%= service.getHes() %></td>
            <td><%= service.getSociedad() %></td>
            <td><%= service.getOrdenEstadistica() %></td>
            <td><%= service.getTextoBreve() %></td>
            <td><%= service.getCotizacion() %></td>
            <td><%= service.isActivo() %></td>
            <td><%= service.getFechaCreacion() %></td>
            <td><%= service.getFechaActualizacion() %></td>
            <td><%= service.getFechaCierre() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="23">No data available</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
