<%-- 
    Document   : listarIncidentes
    Created on : Jul 2, 2024, 1:11:13 AM
    Author     : JFerreira
--%>

<%@page import="logica.Incidentes"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Agregar Incidente</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link href="css/fontawesome.css" rel="stylesheet" />
    <link href="css/brands.css" rel="stylesheet" />
    <link href="css/solid.css" rel="stylesheet" />
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/script.js"></script>
</head>
<h1>Lista de Servicios Registrados</h1>
<table id="details" class="table table-striped table-sm tablesorter">
    <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Mes</th>
            <th>Formato</th>
            <th>IN</th>
            <th>SAP</th>
            <th>Tienda</th>
            <th>Detalle</th>
            <th>Monto</th>
            <th>Moneda</th>
            <th>Proveedor</th>
            <th>Fecha Autorizar</th>
            <th>OC</th>
            <th>Fecha Envío Prov</th>
            <th>HES</th>
            <th>Sociedad</th>
            <th>Orden Estadística</th>
            <th>Texto Breve</th>
            <th>Cotización</th>
            <th>Activo</th>
            <th>Fecha Cierre</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Incidentes> listaIncidentes = (List<Incidentes>) request.getAttribute("incidentes");
            for (Incidentes incidente : listaIncidentes) {
        %>

        <tr>
            <td><%= incidente.getId()%></td>
            <td><%= incidente.getTipo()%></td>
            <td><%= incidente.getMes()%></td>
            <td><%= incidente.getFormato()%></td>
            <td><%= incidente.getInc()%></td>
            <td><%= incidente.getSap()%></td>
            <td><%= incidente.getTienda()%></td>
            <td><%= incidente.getDetalle()%></td>
            <td><%= incidente.getMonto()%></td>
            <td><%= incidente.getMoneda()%></td>
            <td><%= incidente.getProveedor()%></td>
            <td><%= incidente.getFAutorizar()%></td>
            <td><%= incidente.getOc()%></td>
            <td><%= incidente.getFEnvioProv()%></td>
            <td><%= incidente.getHes()%></td>
            <td><%= incidente.getSociedad()%></td>
            <td><%= incidente.getOrdenEstadistica()%></td>
            <td><%= incidente.getTextoBreve()%></td>
            <td><%= incidente.getCotizacion()%></td>
            <td><%= incidente.getActivo()%></td>
            <td><%= incidente.getFechaCierre()%></td>
        </tr>
        <%}%>
    </tbody>
</table>