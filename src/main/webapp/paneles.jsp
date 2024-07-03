<%-- 
    Document   : paneles
    Created on : May 10, 2024, 10:22:11 PM
    Author     : JFerreira
--%>


<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <title>Panel Online Cajas</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/kpi.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/navbars.css" rel="stylesheet" type="text/css"/>
        <link href="fontawesome/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="tablesorter-master/dist/css/theme.default.min.css" rel="stylesheet" type="text/css"/>
        <link href="tablesorter-master/dist/css/filter.formatter.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="fontawesome/js/all.min.js"></script>
        <script src="tablesorter-master/dist/js/jquery.tablesorter.min.js"></script>
        <script src="tablesorter-master/dist/js/jquery.tablesorter.widgets.min.js"></script>
    </head>
    <%
        Usuario user = (Usuario) session.getAttribute("user");
        System.out.println("Session ID: " + session.getId());
    %>

    <body class="body-maleta">

        <header>
            <%@ include file="jsp/navbar.jsp" %>
        </header>

        <div class="container-fluid mt-3">
            <div id="updateInfo" class="row">
                <div id="postit">
                    <div id="message"></div>
                </div>
            </div>
            <div id="kpis" class="row">
                <h1>Cajas:</h1>
                <div class="info-message">
                    <i class="fa-solid fa-info-circle"></i> Porcentaje de cajas online por formato, para detalle de cajas offline dar
                    click en la card correspondiente
                </div>

            </div>
            <div id="kpiServer" class="row">
                <h1>Servidores:</h1>
            </div>

            <hr>
            <h2 id="title-detail"></h2>
            <table id="details" class="table table-striped tablesorter">
                <thead>
                    <tr>
                        <th>Local</th>
                        <th>Formato</th>
                        <th>Tienda</th>
                        <th>Caja</th>
                        <th>IP</th>
                        <th>Estado</th>
                        <th>Duracion</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <!-- Botón de Volver Arriba -->
        <button 
            type="button"
            class="btn btn-danger btn-floating btn-lg"
            id="backToTop">
            <i class="fas fa-arrow-up"></i>
        </button>
        <script>
            //Funciones para mostrar KPI de Servidores
            $(document).ready(function () {
                // Inicialización para SvOfflineServer
                $.ajax({
                    url: 'SvOfflineServer',
                    method: 'GET',
                    success: function (data) {
                        data.forEach(function (item) {
                            var total = item.online + item.offline;
                            var percentOnline = (item.online / total * 100).toFixed(2);
                            var cardHtml = '<div class="col-lg-3 col-md-6 col-sm-12 mb-3">' +
                                    '<div class="card ' + getCardColor(percentOnline) + '" data-formato-server="' + item.formato + '">' +
                                    '<div class="card-body">' +
                                    '<h5 class="card-title">' + item.formato + '</h5>' +
                                    '<p class="card-text">Online: ' + item.online + ' / ' + total + ' (' + percentOnline + '%)</p>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>';
                            $('#kpiServer').append(cardHtml);
                        });
                        $('.card').click(function () {
                            var formato2 = $(this).data('formato-server');
                            $('#title-detail').text('Detalle de Servidores Offline - ' + formato2);
                            fetchOfflineServerDetails(formato2);
                            $('html, body').animate({
                                scrollTop: $("#details").offset().top
                            }, 1000);
                        });
                    }
                });

                // Inicialización para SvKpi Cajas
                $.ajax({
                    url: 'SvKpi',
                    method: 'GET',
                    success: function (data) {
                        var lastUpdate = '';
                        data.forEach(function (item) {
                            var total = item.online + item.offline;
                            var percentOnline = (item.online / total * 100).toFixed(2);
                            var cardHtml = '<div class="col-lg-3 col-md-6 col-sm-12 mb-3">' +
                                    '<div class="card ' + getCardColor(percentOnline) + '" data-formato="' + item.formato + '">' +
                                    '<div class="card-body">' +
                                    '<h5 class="card-title">' + item.formato + '</h5>' +
                                    '<p class="card-text">Online: ' + item.online + ' / ' + total + ' (' + percentOnline + '%)</p>' +
                                    '</div>' +
                                    '</div>' +
                                    '</div>';
                            $('#kpis').append(cardHtml);
                            lastUpdate = "Actualizado: " + item.updatedAt;
                        });
                        $('#message').text(lastUpdate);
                        $('.card').click(function () {
                            var formato = $(this).data('formato');
                            $('#title-detail').text('Detalle de Cajas Offline - ' + formato);
                            fetchOfflineDetails(formato);
                            $('html, body').animate({
                                scrollTop: $("#details").offset().top
                            }, 1000);
                        });
                    }
                });

                $('#backToTop').click(function () {
                    $('html, body').animate({scrollTop: 0}, 1000);
                    return false;
                });

                $(window).scroll(function () {
                    if ($(window).scrollTop() > 100) {
                        $('#backToTop').fadeIn();
                    } else {
                        $('#backToTop').fadeOut();
                    }
                });

                // Inicialización de Tablesorter
                $("#details").tablesorter({
                    theme: 'default',
                    widgets: ['zebra', 'filter'],
                    widgetOptions: {
                        zebra: ["even", "odd"],
                        filter_reset: ".reset"
                    }
                });
            });

            function fetchOfflineDetails(formato) {
                $.ajax({
                    url: 'SvOffline',
                    method: 'GET',
                    data: {formato: formato},
                    success: function (data) {
                        var rows = '';
                        data.forEach(function (item) {
                            rows += '<tr>' +
                                    '<td>' + item.local + '</td>' +
                                    '<td>' + item.formato + '</td>' +
                                    '<td>' + item.nombreTienda + '</td>' +
                                    '<td>' + item.caja + '</td>' +
                                    '<td>' + item.ip + '</td>' +
                                    '<td>' + (item.estadoIp ? 'Online' : 'Offline') + '</td>' +
                                    '<td>' + item.duracion + '</td>' +
                                    '</tr>';
                        });
                        $('#details tbody').html(rows);
                        $("#details").trigger("update"); // Actualizar la tabla después de agregar filas
                    }
                });
            }
            function fetchOfflineServerDetails(formato) {
                    $.ajax({
                        url: 'SvDetailServerOffline',
                        method: 'GET',
                        data: {formato: formato},
                        success: function (data) {
                            var rows = '';
                            data.forEach(function (item) {
                                rows += '<tr>' +
                                        '<td>' + item.local + '</td>' +
                                        '<td>' + item.formato + '</td>' +
                                        '<td>' + item.nombreTienda + '</td>' + 
                                        '<td>' + item.caja + '</td>' +
                                        '<td>' + item.ipAddress + '</td>' +
                                        '<td>' + item.estadoIp + '</td>' +
                                        '<td>' + item.duracion + '</td>' +
                                        '</tr>';
                            });
                            $('#details tbody').html(rows);
                            $("#details").trigger("update");
                        }
                    });
                }

            function getCardColor(percent) {
                if (percent === 100) {
                    return 'card-online'; // Verde
                } else if (percent > 97 && percent <= 100) {
                    return 'card-highhigh'; // Amarillo
                } else if (percent > 95 && percent <= 97) {
                    return 'card-high';
                } else if (percent > 93 && percent <= 95) {
                    return 'card-highmedium';
                } else if (percent >= 69 && percent <= 93) {
                    return 'card-medium'; // Naranja
                } else {
                    return 'card-low'; // Rojo
                }
            }
        </script>
    </body>
</html>


