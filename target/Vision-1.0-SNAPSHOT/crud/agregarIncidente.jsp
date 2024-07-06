<%-- 
    Document   : agregarIncidente
    Created on : Jul 3, 2024, 11:15:03 PM
    Author     : JFerreira
--%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Agregar Incidente</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link href="css/fontawesome.css" rel="stylesheet" />
    <link href="css/brands.css" rel="stylesheet" />
    <link href="css/solid.css" rel="stylesheet" />
    <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
    <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/script.js"></script>
    <style>
        .ui-autocomplete {
            max-height: 200px;
            overflow-y: auto;
            overflow-x: hidden;
            z-index: 1000 !important;
        }
        .ui-helper-hidden-accessible {
            display: none;
        }
    </style>
</head>
<body>
    <h1>Agregar Incidente</h1>
    <form method="POST" action="SvIncidente?action=add" class="col-6 p-3">
        
        <!-- Input Tipo de Servicio -->
        <div class="mb-3">
            <label for="tipo" class="form-label">Tipo</label>
            <input type="text" class="form-control" id="tipo" name="tipo" required>
        </div>
        
        <!-- Input Ingreso Mes de Servicio -->
        <div class="mb-3">
            <label for="mes" class="form-label">Mes</label>
            <input type="number" class="form-control" id="mes" name="mes" required>
        </div>
        
        <!-- Input Ingreso Formato de Tienda -->
        <div class="mb-3">
            <label for="formato" class="form-label">Formato</label>
            <input type="text" class="form-control" id="formato" name="formato" required>
        </div>
        
        <!-- Input Ingreso Numero de Ticket -->
        <div class="mb-3">
            <label for="inc" class="form-label">Inc</label>
            <input type="text" class="form-control" id="inc" name="inc">
        </div>
        
        <!-- Input Ingreso Codigo de Local -->
        <div class="mb-3">
            <label for="sap" class="form-label">SAP</label>
            <input type="text" class="form-control" id="sap" name="sap" required>
        </div>
        
        <!-- Input Ingreso Nombre de Local -->
        <div class="mb-3">
            <label for="tienda" class="form-label">Tienda</label>
            <input type="text" class="form-control" id="tienda" name="tienda" required>
        </div>
        
        <!-- Input Ingreso de Detalle de Servicio -->
        <div class="mb-3">
            <label for="detalle" class="form-label">Detalle</label>
            <textarea class="form-control" id="detalle" name="detalle" rows="3"></textarea>
        </div>
        
        <!-- Input Monto de Servicio -->
        <div class="mb-3">
            <label for="monto" class="form-label">Monto</label>
            <input type="number" step="0.01" class="form-control" id="monto" name="monto">
        </div>
        
        <!-- Input Ingreso Codigo de Tipo de Moneda -->
        <div class="mb-3">
            <label for="moneda" class="form-label">Moneda</label>
            <input type="text" class="form-control" id="moneda" name="moneda">
        </div>
        
        <!-- Input Ingreso Proveedor -->
        <div class="mb-3">
            <label for="proveedor" class="form-label">Proveedor</label>
            <input type="text" class="form-control" id="proveedor" name="proveedor">
        </div>
        
        <!-- Input Ingreso Fecha Autorizacion -->
        <div class="mb-3">
            <label for="f_autorizar" class="form-label">Fecha Autorizar</label>
            <input type="date" class="form-control" id="f_autorizar" name="f_autorizar">
        </div>
        
        <!-- Input Ingreso OC -->
        <div class="mb-3">
            <label for="oc" class="form-label">OC</label>
            <input type="text" class="form-control" id="oc" name="oc">
        </div>
        
        <!-- Input Fecha Envio a Proveedor -->
        <div class="mb-3">
            <label for="f_envio_prov" class="form-label">Fecha Envío Prov</label>
            <input type="date" class="form-control" id="f_envio_prov" name="f_envio_prov">
        </div>
        
        <!-- Input HES -->
        <div class="mb-3">
            <label for="hes" class="form-label">HES</label>
            <% if(user != null && user.getIdRole() == 1) {%>
                <input type="text" class="form-control" id="hes" name="hes">
            <% } else { %>
               <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
            <% }%>
        </div>
        
        <!-- Input Sociedad -->
        <div class="mb-3">
            <label for="sociedad" class="form-label">Sociedad</label>
            <% if(user != null && user.getIdRole() == 1) {%>
            <input type="text" class="form-control" id="sociedad" name="sociedad">
            <% } else { %>
               <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
            <% }%>
        </div>
        
        <!-- Input Orden Estadistica -->
        <div class="mb-3">
            <label for="orden_estadistica" class="form-label">Orden Estadística</label>
            <% if(user != null && user.getIdRole() == 1) {%>
            <input type="number" class="form-control" id="orden_estadistica" name="orden_estadistica">
            <% } else { %>
               <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
            <% }%>
        </div>
        
        <!-- Input Texto Breve -->
        <div class="mb-3">
            <label for="texto_breve" class="form-label">Texto Breve</label>
            <% if(user != null && user.getIdRole() == 1) {%>
            <input type="text" class="form-control" id="texto_breve" name="texto_breve">
             <% } else { %>
               <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
            <% }%>
        </div>
        
        <!-- Input Texto Breve -->
        <div class="mb-3">
            <label for="cotizacion" class="form-label">Cotización</label>
            <input type="text" class="form-control" id="cotizacion" name="cotizacion">
        </div>
        
        <!-- Input Texto Breve -->
        <div class="form-check mb-3">
            <input type="checkbox" class="form-check-input" id="activo" name="activo" checked>
            <label for="activo" class="form-check-label">Activo</label>
        </div>
        
        
        <button type="submit" class="btn btn-primary" id="agregarIncidenteForm">Agregar</button>
    </form>
    <!-- Autocomplete Script -->
    <script>
        var ipOculta = [];

        $(document).ready(function () {
            $('#sap').autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: 'SvDatosServidor',
                        dataType: "json",
                        method: 'GET',
                        data: {
                            action: 'searchLocals',
                            query: request.term
                        },
                        success: function (data) {
                            response(data.map(function (local) {
                                return {
                                    label: local.formato + " " + local.local + " - " + local.nombreTienda,
                                    value: local.local,
                                    data: local
                                };
                            }));
                        }
                    });
                },
                select: function (event, ui) {
                    var data = ui.item.data;
                    $('#tienda').val(data.formato + " " + data.nombreTienda);
                    $('#sap').val(data.local);
                    $('#formato').val(data.formato);
                    $('#ipOculta').val(data.ipAddress);
                },
                minLength: 2
            });

            $('#localId').keypress(function (e) {
                if (e.which === 13) {
                    var local = $(this).val();
                    local = parseInt(local);
                    if (!isNaN(local) && local >= 3 && local <= 3520) {
                        local = local.toString().padStart(3, '0');
                        console.log("Local ingresado: ", local);
                        $.ajax({
                            url: 'SvDatosServidor',
                            dataType: "json",
                            method: 'GET',
                            data: {
                                action: 'getLocal',
                                datosLocal: local
                            },
                            success: function (data) {
                                var concatenatedData = data.formato + " " + data.nombreTienda;
                                $('#tienda').val(concatenatedData);
                                $('#formato').val(data.formato);
                                $('#sap').val(data.local);
                                $('#ipOculta').val(data.ipAddress);
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                alert("Error: " + jqXHR.responseJSON.error);
                                console.log("Error en la obtención de información del local", textStatus, errorThrown);
                                $('#tienda').val('');
                                $('#sap').val('');
                                $('#iPservidorInput').val('');
                                $('#formato').val('');
                                $('#ipOculta').val('');
                            }
                        });
                    } else {
                        alert('Número de local inválido. Por favor, ingrese un número entre 000 y 3199.');
                        $(this).val('');
                    }
                }
            });

            $('#localId').keydown(function (e) {
                if (e.key === "F1") {
                    e.preventDefault();
                    $(this).autocomplete("search", $(this).val());
                }
            });
        });
    </script>
  </body>
</html>

