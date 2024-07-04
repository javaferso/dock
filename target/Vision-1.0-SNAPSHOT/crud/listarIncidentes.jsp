<%-- 
    Document   : listarIncidentes
    Created on : Jul 2, 2024, 1:11:13 AM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tipo</th>
            <th>Mes</th>
            <th>Formato</th>
            <th>IN</th>
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
            <th>Fecha Creación</th>
            <th>Fecha Actualización</th>
            <th>Fecha Cierre</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="incidente" items="${incidentes}">
            <tr>
                <td>${incidente.id}</td>
                <td>${incidente.tipo.nombre}</td>
                <td>${incidente.mes}</td>
                <td>${incidente.formato.nombre}</td>
                <td>${incidente.in}</td>
                <td>${incidente.tienda.nombre}</td>
                <td>${incidente.detalle}</td>
                <td>${incidente.monto}</td>
                <td>${incidente.moneda.codigo}</td>
                <td>${incidente.proveedor.nombre}</td>
                <td>${incidente.fAutorizar}</td>
                <td>${incidente.oc}</td>
                <td>${incidente.fEnvioProv}</td>
                <td>${incidente.hes}</td>
                <td>${incidente.sociedad.nombre}</td>
                <td>${incidente.ordenEstadistica}</td>
                <td>${incidente.textoBreve}</td>
                <td>${incidente.cotizacion}</td>
                <td>${incidente.activo}</td>
                <td>${incidente.fechaCreacion}</td>
                <td>${incidente.fechaActualizacion}</td>
                <td>${incidente.fechaCierre}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

