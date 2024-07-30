<%-- 
    Document   : servicios
    Created on : Jul 1, 2024, 11:09:41 PM
    Author     : JFerreira
--%>
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>
<%
        Usuario user = (Usuario) session.getAttribute("user");
        System.out.println("Session ID: " + session.getId());
        String ipCaja = (String) session.getAttribute("ipCaja");
        
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/script.js" type="text/javascript"></script>
        <script src="../js/code.jquery.com_jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="../js/jquery-3.7.0.min.js" type="text/javascript"></script>
        <script src="../js/fontawesome.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios</title>
    </head>
    <%@include file='navbar.jsp' %>
    <body>
        <form action="SvIncidente" method="post">
            <select name="tipo_id">
                <!-- Opciones de tipos -->
            </select>
            <input type="number" name="mes" placeholder="Mes">
            <select name="formato_id">
                <!-- Opciones de formatos -->
            </select>
            <input type="text" name="in" placeholder="IN">
            <select name="tienda_id">
                <!-- Opciones de tiendas -->
            </select>
            <textarea name="detalle" placeholder="Detalle"></textarea>
            <input type="number" name="monto" placeholder="Monto">
            <select name="moneda_id">
                <!-- Opciones de monedas -->
            </select>
            <select name="proveedor_id">
                <!-- Opciones de proveedores -->
            </select>
            <input type="date" name="f_autorizar" placeholder="F. Autorizar">
            <input type="text" name="oc" placeholder="OC">
            <input type="date" name="f_envio_prov" placeholder="F. Envío Prov">
            <input type="text" name="hes" placeholder="HES">
            <select name="sociedad_id">
                <!-- Opciones de sociedades -->
            </select>
            <input type="text" name="orden_estadistica" placeholder="Orden Estadística">
            <textarea name="texto_breve" placeholder="Texto Breve"></textarea>
            <input type="text" name="cotizacion" placeholder="Cotización">
            <label for="activo">Activo:</label>
            <input type="checkbox" name="activo" id="activo" checked>
            <button type="submit">Guardar</button>
        </form>


    </body>
</html>
