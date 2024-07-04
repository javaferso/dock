<%-- 
    Document   : agregarIncidente
    Created on : Jul 3, 2024, 11:15:03 PM
    Author     : JFerreira
--%>
<%-- 
    Document   : agregarIncidente
    Created on : Jul 3, 2024, 1:30:00 PM
    Author     : JFerreira
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
<body>
    <h1>Agregar Incidente</h1>
    <form action="SvIncidente" method="post">
        <div class="mb-3">
            <label for="tipo" class="form-label">Tipo</label>
            <input type="text" class="form-control" id="tipo" name="tipo" required>
        </div>
        <div class="mb-3">
            <label for="mes" class="form-label">Mes</label>
            <input type="number" class="form-control" id="mes" name="mes" required>
        </div>
        <div class="mb-3">
            <label for="formato" class="form-label">Formato</label>
            <input type="text" class="form-control" id="formato" name="formato" required>
        </div>
        <div class="mb-3">
            <label for="inc" class="form-label">Inc</label>
            <input type="text" class="form-control" id="inc" name="inc">
        </div>
        <div class="mb-3">
            <label for="sap" class="form-label">SAP</label>
            <input type="number" class="form-control" id="sap" name="sap" required>
        </div>
        <div class="mb-3">
            <label for="tienda" class="form-label">Tienda</label>
            <input type="text" class="form-control" id="tienda" name="tienda" required>
        </div>
        <div class="mb-3">
            <label for="detalle" class="form-label">Detalle</label>
            <textarea class="form-control" id="detalle" name="detalle" rows="3"></textarea>
        </div>
        <div class="mb-3">
            <label for="monto" class="form-label">Monto</label>
            <input type="number" step="0.01" class="form-control" id="monto" name="monto">
        </div>
        <div class="mb-3">
            <label for="moneda" class="form-label">Moneda</label>
            <input type="text" class="form-control" id="moneda" name="moneda">
        </div>
        <div class="mb-3">
            <label for="proveedor" class="form-label">Proveedor</label>
            <input type="text" class="form-control" id="proveedor" name="proveedor">
        </div>
        <div class="mb-3">
            <label for="f_autorizar" class="form-label">Fecha Autorizar</label>
            <input type="date" class="form-control" id="f_autorizar" name="f_autorizar">
        </div>
        <div class="mb-3">
            <label for="oc" class="form-label">OC</label>
            <input type="text" class="form-control" id="oc" name="oc">
        </div>
        <div class="mb-3">
            <label for="f_envio_prov" class="form-label">Fecha Envío Prov</label>
            <input type="date" class="form-control" id="f_envio_prov" name="f_envio_prov">
        </div>
        <div class="mb-3">
            <label for="hes" class="form-label">HES</label>
            <input type="text" class="form-control" id="hes" name="hes">
        </div>
        <div class="mb-3">
            <label for="sociedad" class="form-label">Sociedad</label>
            <input type="text" class="form-control" id="sociedad" name="sociedad">
        </div>
        <div class="mb-3">
            <label for="orden_estadistica" class="form-label">Orden Estadística</label>
            <input type="number" class="form-control" id="orden_estadistica" name="orden_estadistica">
        </div>
        <div class="mb-3">
            <label for="texto_breve" class="form-label">Texto Breve</label>
            <input type="text" class="form-control" id="texto_breve" name="texto_breve">
        </div>
        <div class="mb-3">
            <label for="cotizacion" class="form-label">Cotización</label>
            <input type="text" class="form-control" id="cotizacion" name="cotizacion">
        </div>
        <div class="form-check mb-3">
            <input type="checkbox" class="form-check-input" id="activo" name="activo" checked>
            <label for="activo" class="form-check-label">Activo</label>
        </div>
        <button type="submit" class="btn btn-primary">Agregar</button>
    </form>
</body>
</html>

