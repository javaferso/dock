<%-- 
    Document   : controlpos
    Created on : Jul 18, 2024, 1:31:32 PM
    Author     : JFerreira
--%>
<%@page import="logica.Servidores"%>
<%@page import="logica.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 

<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
    Servidores servidores = new Servidores();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ControlPos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="icons/feather-main/src/icons.js"></script>
        <script src="icons/feather-main/src/icon.js" type="text/javascript"></script>
        <link href="fontawesome/css/fontawesome.css" rel="stylesheet" type="text/css"/>
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <script src="poppers/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/stickyfill.min.js"></script>
        <script src="js/script.js"></script>
        <script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
    </head>
    <header>
        <%@ include file="jsp/navbar.jsp" %>
    </header>
    <% if (user == null) {
            // Redirigir a Login
            response.sendRedirect("login.jsp");
        } else {%> 
    <body class="body-maleta">
        <div class="container-fluid">
            <div class="shadow p-2 mb-3 bg-body-tertiary rounded">
                <div class="form-container">
                    <div class="input-section">
                        <div class="input-group mb-1">
                            <label for="tiendaInput" class="form-label">
                                <img src="icons/search_30dp_9B5278_FILL0_wght400_GRAD0_opsz24.svg" alt="Tienda" class="icon-svg mt-2"/>
                            </label>
                            <input type="text" id="localId" class="form-control p-2" value="" placeholder="Ingrese sap o nombre de tienda">
                            <label for="nombreInput" class="form-label">
                                <img src="icons/info_30dp_9B5278_FILL0_wght400_GRAD0_opsz24.svg" alt="Nombre" class="icon-svg mt-2"/>
                            </label>
                            <input type="text" id="nombreTienda" class="form-control" value="" readonly placeholder="Hostname Tienda">
                        </div>
                        <div class="input-group mb-1">
                            <label for="direccionInput" class="form-label">
                                <img src="icons/near_me_30dp_9B5278_FILL0_wght400_GRAD0_opsz24.svg" alt="Dirección" class="icon-svg"/>
                            </label>
                            <input type="text" id="direccionInput" class="form-control" value="" readonly placeholder="Direccion Tienda">
                        </div>
                        <div class="input-group mb-1">
                            <span class="input-group-text">
                                <img src="icons/dns_30dp_9B5278_FILL0_wght400_GRAD0_opsz24.svg" alt="IP Serv" class="icon-svg"/>
                            </span>
                            <input type="text" id="iPservidorInput" class="form-control" value="" readonly placeholder="Ip Servidor">
                            <span class="input-group-text">
                                <button type="button" class="btn status-icon bg-success" id="status-icon-ip"></button>

                            </span>
                            <span class="input-group-text">
                                <button type="button" class="btn btn-light" id="ping-icon" onclick="solicitarPing()">
                                    <i class="fas fa-sync-alt fa-xs"></i>
                                </button>
                            </span>
                            <span class="input-group-text">
                                <img src="icons/lan_30dp_9B5278_FILL0_wght400_GRAD0_opsz24.svg" alt="P.Enlace" class="icon-svg"/>
                            </span>
                            <input type="text" id="enlaceInput" class="form-control" value="" readonly placeholder="Ip Enlace">
                            <span class="input-group-text">
                                <button type="button" class="btn status-icon bg-success" id="status-icon-enlace"></button>
                            </span>
                            <span class="input-group-text">
                                <button type="button" class="btn btn-light" id="ping-icon" onclick="solicitarPingEnlace()">
                                    <i class="fas fa-sync-alt fa-xs"></i>
                                </button>
                            </span>
                        </div>
                    </div>


                    <div class="button-section text-center">
                        <!-- El input está como campo oculto -->
                        <input type="hidden" id="ipVirtualInput" value="">
                        <div class="mb-1">
                            <button type="button" class="btn btn-outline-dark me-md-2" id="ip_virtual" value="" onclick="setVirtual($('#ipVirtualInput').val())">Serv. Virtual</button>
                            <button type="button" class="btn btn-outline-dark mb-1" id="ipServer" value="" onclick="setServerssh($('#iPservidorInput').val())">SSHServidor</button>
                            <button type="button" class="btn btn-outline-dark mb-1" id="ip_consola" value="" onclick="setConsola($('#iPservidorInput').val())">Geoconsola</button>
                            <button type="button" class="btn btn-outline-dark mb-1" id="ip_pricer" value="" onclick="setPricer($('#iPservidorInput').val())">GeoPricer</button>
                            <button type="button" class="btn btn-outline-dark mb-1" id="ip_conciliacion" value="" onclick="setConciliacion($('#iPservidorInput').val())">GeoConciliacion</button>
                            <button type="button" class="btn btn-outline-dark mb-1" id="ipFlejeElectronico" value="" onclick="setFlejeElectronico($('#ipFlejeElectronico').val())">Fleje Electronico</button>
                        </div>
                         <div class="input-group mb-1">
                        
                            <span class="input-group-text">
                                <img src="icons/battery-charging.svg" alt="P.Enlace" class="icon-svg"/>
                            </span>
                            <input type="text" id="upsInput" class="form-control" value="" readonly placeholder="Ip Ups">
                            <span class="input-group-text">
                                <button type="button" class="btn status-icon bg-success" id="status-icon-ups"></button>
                            </span>
                            <span class="input-group-text">
                                <button type="button" class="btn btn-light" id="ping-icon" onclick="solicitarPingUps()">
                                    <i class="fas fa-sync-alt fa-xs"></i>
                                </button>
                            </span>
                    </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="worko-tabs">
                <input class="state" type="radio" title="tab-one" name="tabs-state" id="tab-one" checked />
                <input class="state" type="radio" title="tab-two" name="tabs-state" id="tab-two" onclick="cargarBalanzas($('#local').val())" />
                <input class="state" type="radio" title="tab-three" name="tabs-state" id="tab-three" onclick="cargarConsultaPrecios($('#local').val())" />
                <input class="state" type="radio" title="tab-four" name="tabs-state" id="tab-four" />

                <div class="tabs flex-tabs">
                    <label for="tab-one" id="tab-one-label" class="tab">Pos</label>
                    <label for="tab-two" id="tab-two-label" class="tab">Balanzas</label>
                    <label for="tab-three" id="tab-three-label" class="tab">Consulta Precios</label>
                    <label for="tab-four" id="tab-four-label" class="tab">Otros</label>

                    <div id="tab-one-panel" class="panel active">
                        <!-- Contenido para la pestaña POS -->
                        <div id="todasLasCajas" class="bg-light p-3 shadow-sm mb-3 mt-3" style="border-radius: 15px; background-color: #a3cfbb">
                            <div id="loading" style="display: none;">
                                <div class="spinner-border text-primary" role="status">
                                    <span class="sr-only">Cargando...</span>
                                </div>
                            </div>
                            <h1>InfoPos</h1>
                        </div>
                    </div>
                    <div id="tab-two-panel" class="panel">
                        <!-- Contenido para la pestaña BALANZAS -->
                        <div id="todasLasBalanzas" class="bg-light p-3 shadow-sm mb-3 mt-3" style="border-radius: 15px; background-color: #a3cfbb">
                            <div id="loading" style="display: none;">
                                <div class="spinner-border text-primary" role="status">
                                    <span class="sr-only">Cargando...</span>
                                </div>
                            </div>
                            <h1>InfoBalanzas</h1>
                        </div>
                    </div>
                    <div id="tab-three-panel" class="panel">
                        <!-- Contenido para la pestaña CONSULTA PRECIOS -->
                        <div id="todasLasConsultas" class="bg-light p-3 shadow-sm mb-3 mt-3" style="border-radius: 15px; background-color: #a3cfbb">
                            <div id="loading" style="display: none;">
                                <div class="spinner-border text-primary" role="status">
                                    <span class="sr-only">Cargando...</span>
                                </div>
                            </div>
                            <h1>InfoConsultaPrecios</h1>
                        </div>
                    </div>
                    <div id="tab-four-panel" class="panel">
                        <h1>InfoTiendaUps</h1>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="d-grid gap-1 d-md-flex justify-content-xl-start">
                <input type="text" class="input-group-lg" id="ipCajaOculta" value="" placeholder="Ip Seleccionada"/>
                <button
                    type="button"
                    id="local"
                    class="btn btn-outline-dark me-md-2"
                    value=""
                    onclick="setTickets($('#local').val())"
                    >Obtener Tickets</button>

                <button 
                    type="button" 
                    id="obtenerReversas" 
                    class="btn btn-outline-dark me-md-2" 
                    onclick="obtenerReversas()"
                    >Obtener Reversas</button>

                <button
                    type="button"
                    id="verSSHButton"
                    class="btn btn-outline-dark me-md-2"
                    value=""
                    onclick="setSSHLink($('#ipCajaOculta').val())"
                    >SSH Pos</button>
                <input type="hidden" id="ipPosShadow" value=''/>
                <button
                    type="button"
                    id="verVNCButton"
                    class="btn btn-outline-dark me-md-2"
                    value=""
                    onclick="setVNCLink($('#ipCajaOculta').val())"
                    >VNC Pos</button>
            </div>
            <!-- Modal -->
            <div id="myModal" class="modal">
                <!-- Contenido del Modal -->
                <div class="modal-content">
                    <span class="close" onclick="cerrarModal()">&times;</span>
                    <div id="tinymceEditor"></div>
                </div>
            </div>
        </div>
        <script>
            function getValueById(id) {
                return $('#' + id).val();
            }
            var ipOculta = [];
            var enterPressed = false;
            $(document).ready(function () {
                // Código para cargar datos iniciales
                $('#localId').click(function () {
                    $('#localId').val('');
                    $('#nombreTienda').val('');
                    $('#direccionInput').val('');
                    $('#cityInput').val('');
                    $('#iPservidorInput').val('');
                    $('#ipVirtualInput').val('');
                    $('#enlaceInput').val('');
                    $('#upsInput').val('');
                    $('#nombreInput').val('');
                    $('#ipFlejeElectronico').val('');
                    $('#ipConsultaPrecios').val('');
                    $('#ipConsultaPrecios2').val('');
                    $('#todasLasCajas').empty();
                    $('#status-icon-enlace').empty();
                    $('#status-icon-ip').empty();
                    $('#ipCajaOculta').empty();
                });
                $('#localId').autocomplete({
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
                                response(data.map(function (item) {
                                    var local = item.local; // Extraer el objeto local
                                    $('#loading').show();
                                    return {
                                        label: local.formato + " " + local.local + " - " + local.nombreTienda,
                                        value: local.local,
                                        data: item
                                    };
                                }));
                            }
                        });
                    },
                    select: function (event, ui) {
                        if (enterPressed) {
                            enterPressed = false;
                            $('#loading').hide();
                            return false;
                        }
                        var data = ui.item.data.local;
                        $('#nombreTienda').val(data.formatoCodigo + " " + data.localTxt + " " + data.nombreTienda);
                        var concatenatedLocation = data.direccion + ", " + data.ciudad;
                        $('#direccionInput').val(concatenatedLocation);
                        $('#cityInput').val(data.ciudad);
                        $('#iPservidorInput').val(data.ipAddress);
                        $('#ipVirtualInput').val(data.ipVirtual);
                        $('#enlaceInput').val(data.ipEnlace);
                        $('#upsInput').val(data.ipUps);
                        $('#nombreInput').val(data.formato);
                        $('#ipOculta').val(data.ipAddress);
                        $('#local').val(data.local);
                        cargarCajas(data.local);
                        cargarBalanzas(data.local);
                        cargarConsultaPrecios(data.local);
                        updateStatusIcon('#status-icon-ip', data.estadoIp);
                        updateStatusIcon('#status-icon-enlace', data.estadoEnlace);
                        updateStatusIcon('#status-icon-ups', data.EstadoUps);

                        if (ui.item.data.flejeData) {
                            // Manejar los datos de flejeData si existen
                            $('#ipFlejeElectronico').val(ui.item.data.flejeData.IpFlejeElectronico);
                            $('#ipConsultaPrecios').val(ui.item.data.flejeData.IpConsultaPrecios);
                            $('#ipConsultaPrecios2').val(ui.item.data.flejeData.IpConsultaPrecios2);
                        }
                    },
                    minLength: 2
                });

                $('#localId').keypress(function (e) {
                    if (e.which === 13) {

                        enterPressed = true;
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
                                success: function (response) {
                                    var data = response.tienda;
                                    var flejeData = response.flejeData;

                                    if (data) {
                                        var concatenatedData = data.formatoCodigo + " " + data.localTxt + " " + data.nombreTienda;
                                        $('#nombreTienda').val(concatenatedData);
                                        var concatenatedLocation = data.direccion + ", " + data.ciudad;
                                        $('#direccionInput').val(concatenatedLocation);
                                        $('#iPservidorInput').val(data.ipAddress);
                                        $('#ipVirtualInput').val(data.ipVirtual);
                                        console.log(data.ipVirtual);
                                        $('#enlaceInput').val(data.ipEnlace);
                                        $('#upsInput').val(data.ipUps);
                                        $('#nombreInput').val(data.formato);
                                        $('#cityInput').val(data.ciudad);
                                        $('#ipOculta').val(data.ipAddress);
                                        $('#local').val(data.local);
                                        cargarCajas(data.local);
                                        cargarBalanzas(data.local);
                                        cargarConsultaPrecios(data.local);
                                        updateStatusIcon('#status-icon-ip', data.estadoIp);
                                        updateStatusIcon('#status-icon-enlace', data.estadoEnlace);
                                        updateStatusIcon('#status-icon-ups', data.EstadoUps);
                                    }

                                    if (flejeData) {
                                        console.log("Datos de flejeData: ", flejeData);
                                        $('#ipFlejeElectronico').val(flejeData.IpFlejeElectronico);
                                        $('#ipConsultaPrecios').val(flejeData.IpConsultaPrecios);
                                        $('#ipConsultaPrecios2').val(flejeData.IpConsultaPrecios2);
                                    }
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    alert("Error: " + jqXHR.responseJSON.error);
                                    console.log("Error en la obtención de información del local", textStatus, errorThrown);
                                    $('#nombreTienda').val('');
                                    $('#direccionInput').val('');
                                    $('#iPservidorInput').val('');
                                    $('#enlaceInput').val('');
                                    $('#nombreInput').val('');
                                    $('#cityInput').val('');
                                    $('#ipOculta').val('');
                                    $('#ipFlejeElectronico').val('');
                                    $('#ipConsultaPrecios').val('');
                                    $('#ipConsultaPrecios2').val('');
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

            function updateStatusIcon(selector, status) {
                var icon = $(selector);
                icon.removeClass('bg-success bg-warning bg-danger');
                if (status === 'online') {
                    icon.addClass('bg-success');
                } else if (status === 'offline') {
                    icon.addClass('bg-danger');
                } else {
                    icon.addClass('bg-warning');
                }
            }

            function solicitarPing() {
                var ipServidor = $('#iPservidorInput').val();
                var local = $('#localId').val();

                // Inicializar los iconos sin color
                updateStatusIcon('#status-icon-ip', '');

                $.get('SvDatosServidor', {action: 'ping', ipServidor: ipServidor, local: local}, function (data) {
                    updateStatusIcon('#status-icon-ip', data.estadoIp);
                }).fail(function () {
                    alert('Error al realizar el ping.');
                });
            }
            function solicitarPingEnlace() {
                var ipEnlace = $('#enlaceInput').val();
                var local = $('#localId').val();

                // Inicializar los iconos sin color
                updateStatusIcon('#status-icon-enlace', '');

                $.get('SvDatosServidor', {action: 'pingEnlace', ipEnlace: ipEnlace, local: local}, function (data) {

                    updateStatusIcon('#status-icon-enlace', data.estadoEnlace);
                }).fail(function () {
                    alert('Error al realizar el ping.');
                });
            }
            function solicitarPingUps() {
                var ipUps = $('#upsInput').val();
                var local = $('#localId').val();

                // Inicializar los iconos sin color
                updateStatusIcon('#status-icon-ups', '');

                $.get('SvDatosServidor', {action: 'pingUps', ipUps: ipUps, local: local}, function (data) {

                    updateStatusIcon('#status-icon-ups', data.EstadoUps);
                }).fail(function () {
                    alert('Error al realizar el ping.');
                });
            }
            var cajasOrdenadas = [];
            $(document).ready(function () {
                $('#todasLasCajas').on('click', 'tr', function () {
                    $('tr').removeClass('selected');
                    $(this).addClass('selected');
                    // Selecciona la Ip de caja para con.SSH VNC
                    var ipCaja = $(this).find('td:eq(2)').text();
                    $('#ipCajaOculta').val(ipCaja);
                });
            });

            function cargarCajas(local) {
                if (local) {
                    // Vacía los contenidos actuales
                    $('#todasLasCajas').empty();
                    // Muestra el indicador de carga
                    $('#loading').show();
                    // Realiza la solicitud al servidor
                    $.get('SvPing?local=' + local, function (resultados) {
                        // Oculta el indicador de carga
                        $('#loading').hide();
                        // Comprueba si hay resultados
                        if (!$.isEmptyObject(resultados)) {
                            // ordenarCajas
                            cajasOrdenadas = ordenarCajas(Object.keys(resultados));
                            var contenedor = $('#todasLasCajas');
                            var tabla = $('<table>').addClass('table-light');
                            var cabecera = $('<tr>').append(
                                    $('<th>').text('Tienda'),
                                    $('<th>').text('Caja'),
                                    $('<th>').text('IpAddress'),
                                    $('<th>').text('Tickets'),
                                    $('<th>').text('Estado'),
                                    $('<th>').text('Acción'),
                                    $('<th>').text('Otros')
                                    );
                            tabla.append(cabecera);
                            // Itera sobre las cajas ordenadas y agrega las filas a la tabla
                            cajasOrdenadas.forEach(function (caja) {
                                if (resultados[caja]) {
                                    var detalles = resultados[caja];
                                    var ticketValue = parseInt(detalles.tickets, 10);
                                    var claseTicket = ticketValue === 0 ? 'ticket-cero' : 'ticket-otros';

                                    var estadoIcono = $('<td>');
                                    var icon = $('<img>');
                                    if (detalles.estado === "online") {
                                        icon.attr("src", "icons/wifi_30dp_434343.svg"); // Icono verde
                                    } else if (detalles.estado === "offline") {
                                        icon.attr("src", "icons/wifi_off_30dp_BB271A.svg"); // Icono rojo
                                    } else {
                                        icon.attr("src", "icons/alert-circle.svg").addClass("icon-warning"); // Icono de advertencia
                                    }
                                    estadoIcono.append(icon);

                                    var fila = $('<tr>').append(
                                            $('<td>').text(local),
                                            $('<td>').text(caja),
                                            $('<td>').text(detalles.ip),
                                            $('<td>').addClass(claseTicket).text(detalles.tickets),
                                            estadoIcono,
                                            $('<td>').append(
                                            $('<button>').addClass('btn btn-outline-dark me-md-2').text('Ping').click(function () {
                                        hacerPing(caja, local);
                                    }),
                                            $('<button>').addClass('btn btn-outline-dark me-md-2').text('SSH').click(function () {
                                        setSSHLink(detalles.ip);
                                    }),
                                            $('<button>').addClass('btn btn-outline-dark me-md-2').text('VNC').click(function () {
                                        setVNCLink(detalles.ip);
                                    })

                                            ),
                                            $('<td>').text(detalles.reverse)
                                            );
                                    tabla.append(fila);
                                }
                            });
                            contenedor.append(tabla);
                        } else {
                            // Si no hay resultados, muestra un mensaje
                            $('#todasLasCajas').html('<p>No hay datos disponibles.</p>');
                        }
                    });
                }
            }

            function mostrarEstadoCajas() {
                var localSeleccionado = $('#local').val();
                if (localSeleccionado) {
                    $.get('SvPing?local=' + localSeleccionado, function (resultados) {
                        var resultadosOrdenados = {};
                        cajasOrdenadas.forEach(function (caja) {
                            if (resultados[caja]) {
                                resultadosOrdenados[caja] = resultados[caja];
                            }
                        });
                        var contenedor = $('#todasLasCajas');
                        contenedor.empty();
                        var tabla = $('<table>').addClass('table');
                        var cabecera = $('<tr>').append(
                                $('<th>').text('Tienda'),
                                $('<th>').text('Caja'),
                                $('<th>').text('IpAddress'),
                                $('<th>').text('Tickets'),
                                $('<th>').text('Estado'),
                                $('<th>').text('Acción'),
                                $('<th>').text('Otros')
                                );
                        tabla.append(cabecera);
                        $.each(resultadosOrdenados, function (caja, detalles) {
                            var estadoIcono = $('<td>');
                            var icon = $('<img>');
                            if (detalles.estado === "online") {
                                icon.attr("src", "icons/wifi_30dp_434343.svg"); // Icono verde
                            } else if (detalles.estado === "offline") {
                                icon.attr("src", "icons/wifi_off_30dp_BB271A.svg"); // Icono rojo
                            } else {
                                icon.attr("src", "icons/alert-circle.svg").addClass("icon-warning"); // Icono de advertencia
                            }
                            estadoIcono.append(icon);

                            var ticketValue = parseInt(detalles.tickets, 10);
                            var claseTicket = isNaN(ticketValue) || ticketValue === 0 ? 'ticket-cero' : 'ticket-otros';
                            console.log('Caja:', caja, 'Ticket Value:', ticketValue, 'Clase Ticket:', claseTicket);
                            var fila = $('<tr>').append(
                                    $('<td>').text(localSeleccionado),
                                    $('<td>').text(caja),
                                    $('<td>').text(detalles.ip),
                                    $('<td>').addClass(claseTicket).text(detalles.tickets),
                                    estadoIcono,
                                    $('<td>').append(
                                    $('<button>').addClass('btn btn-outline-dark me-md-2').text('Ping').click(function () {
                                hacerPing(caja, localSeleccionado);
                            }),
                                    $('<button>').addClass('btn btn-outline-dark me-md-2').text('SSH').click(function () {
                                setSSHLink(detalles.ip);
                            }),
                                    $('<button>').addClass('btn btn-outline-dark me-md-2').text('VNC').click(function () {
                                setVNCLink(detalles.ip);
                            })
                                    ),
                                    $('<td>').text(detalles.reverse)
                                    );
                            tabla.append(fila);
                        });
                        contenedor.append(tabla);
                    });
                }
            }

            function setSSHLink(ipCaja) {
                if (ipCaja) {
                    location.href = 'ssh://root@' + ipCaja;
                } else {
                    alert('No se ha encontrado la ip de caja');
                }
            }

            function ordenarCajas(cajas) {
                return cajas.sort((a, b) => {
                    // Extrae el número de la caja o SCO
                    const numeroA = parseInt(a.replace(/[^0-9]/g, ''), 10);
                    const numeroB = parseInt(b.replace(/[^0-9]/g, ''), 10);
                    // Comprueba si el elemento es una "CAJA" o una "SCO"
                    const esCajaA = a.startsWith('Caja');
                    const esCajaB = b.startsWith('Caja');
                    // Si ambos son "CAJA", los ordena por número
                    if (esCajaA && esCajaB) {
                        return numeroA - numeroB;
                    }
                    // Si ambos son "SCO", los ordena por número
                    if (esCajaA && esCajaB) {
                        return numeroA - numeroB;
                    }
                    // Si ambos son "SCO", los ordena por número
                    else if (!esCajaA && !esCajaB) {
                        return numeroA - numeroB;
                    }
                    // Si uno es "CAJA" y el otro es "SCO", los "CAJA" van primero
                    else {
                        return esCajaA ? -1 : 1;
                    }
                });
            }

            function cargarSVG(url, callback) {
                $.get(url, function (data) {
                    var svg = $(data).find('svg');
                    callback(svg);
                });
            }

            $(document).ready(function () {
                $('#todasLasBalanzas').on('click', 'tr', function () {
                    $('tr').removeClass('selected');
                    $(this).addClass('selected');
                    //Selecciona la Ip de caja para con.SSH VNC
                    var ipBal = $(this).find('td:eq(5)').text();
                    $('#ipCajaOculta').val(ipBal);
                });
            });

            function cargarBalanzas(local) {
                if (local) {
                    $('#todasLasBalanzas').empty();
                    $('#loading').show();
                    $.get('SvInfoBalanzas?local=' + local, function (resultados) {
                        $('#loading').hide();
                        if (!$.isEmptyObject(resultados)) {
                            var tabla = $('<table>').addClass('table');
                            var cabecera = $('<tr>').append(
                                    $('<th>').text('Tienda'),
                                    $('<th>').text('ID Bal'),
                                    $('<th>').text('Marca'),
                                    $('<th>').text('Modelo'),
                                    $('<th>').text('Descripcion'),
                                    $('<th>').text('IP'),
                                    $('<th>').text('Estado'),
                                    $('<th>').text('Fecha Actualizacion')
                                    );
                            tabla.append(cabecera);

                            $.each(resultados, function (index, balanza) {
                                var estadoIcono = $('<td>');
                                var icon = $('<img>');
                                if (balanza.estadoPing === "Online") {
                                    icon.attr("src", "icons/wifi_30dp_434343.svg"); // Icono verde
                                } else if (balanza.estadoPing === "Offline") {
                                    icon.attr("src", "icons/wifi_off_30dp_BB271A.svg"); // Icono rojo
                                } else {
                                    icon.attr("src", "icons/alert-circle.svg").addClass("icon-warning"); // Icono de advertencia
                                }
                                estadoIcono.append(icon);

                                var fila = $('<tr>').append(
                                        $('<td>').text(balanza.tiendaNumero),
                                        $('<td>').text(balanza.id),
                                        $('<td>').text(balanza.marca),
                                        $('<td>').text(balanza.modelo),
                                        $('<td>').text(balanza.nombre),
                                        $('<td>').text(balanza.direccionIp),
                                        estadoIcono,
                                        $('<td>').text(balanza.fechaActualizacionNagios)
                                        );
                                tabla.append(fila);
                            });

                            $('#todasLasBalanzas').append(tabla);
                        } else {
                            $('#todasLasBalanzas').html('<p>No hay datos disponibles.</p>');
                        }
                    });
                }
            }



            function cargarConsultaPrecios(local) {
                if (local) {
                    $('#todasLasConsultas').empty();
                    $('#loading').show();
                    $.get('SvConsultaPrecios?local=' + local, function (resultados) {
                        $('#loading').hide();
                        if (!$.isEmptyObject(resultados)) {
                            var tabla = $('<table>').addClass('table');
                            var cabecera = $('<tr>').append(
                                    $('<th>').text('Tienda'),
                                    $('<th>').text('Hostname'),
                                    $('<th>').text('sap_nombre'),
                                    $('<th>').text('Ip'),
                                    $('<th>').text('cp_number')
                                    );
                            tabla.append(cabecera);

                            // Si `resultados` es un objeto, necesitas recorrer sus propiedades
                            for (var key in resultados) {
                                if (resultados.hasOwnProperty(key)) {
                                    var consultaprecios = resultados[key];
                                    var fila = $('<tr>').append(
                                            $('<td>').text(consultaprecios.number),
                                            $('<td>').text(consultaprecios.hostname),
                                            $('<td>').text(consultaprecios.sapName),
                                            $('<td>').text(consultaprecios.ip),
                                            $('<td>').text(consultaprecios.cpNumber)
                                            );
                                    tabla.append(fila);
                                }
                            }

                            $('#todasLasConsultas').append(tabla);
                        } else {
                            $('#todasLasConsultas').html('<p>No hay datos disponibles.</p>');
                        }
                    });
                }
            }

            function setTickets(local) {
                console.log("Local: ", local); // Verifica que el valor sea correcto
                $('#loading').show(); // Muestra el indicador de carga
                $.get('SvTickets?local=' + local, function (resultados) {
                    $('#loading').hide(); // Oculta el indicador de carga
                    actualizarTablaTickets(resultados); // Nueva función para actualizar la tabla de tickets
                }).fail(function () {
                    $('#loading').hide();
                    alert('Error al solicitar tickets');
                });
            }

            function actualizarTablaTickets(resultados) {
                // Iterar sobre los resultados y actualizar las filas de la tabla
                $.each(resultados, function (caja, detalles) {
                    var filaCaja = $('tr:contains("' + caja + '")');
                    filaCaja.find('td:eq(3)').text(detalles.tickets);// Actualiza la columna de Tickets (ajusta el índice según sea necesario)
                    var celdaTickets = filaCaja.find('td:eq(3)');
                    celdaTickets.text(detalles.tickets);
                    var ticketValue = parseInt(detalles.tickets, 10);
                    if (isNaN(ticketValue) || ticketValue === 0) {
                        celdaTickets.removeClass('ticket-otros').addClass('ticket-cero');
                    } else {
                        celdaTickets.removeClass('ticket-cero').addClass('ticket-otros');
                    }
                });
            }

            function hacerPing(caja, local) {
                $('#loading').show(); // Muestra el indicador de carga
                $.get('SvPing?local=' + local + '&caja=' + caja, function (resultados) {
                    $('#loading').hide(); // Oculta el indicador de carga
                    actualizarDatosCaja(resultados, caja);
                }).fail(function () {
                    $('#loading').hide();
                    alert('Error al realizar el ping.');
                });
            }

            function actualizarDatosCaja(resultados, caja) {
                var detallesCaja = resultados[caja];
                var filaCaja = $('tr:contains("' + caja + '")');
                var estadoIcono = filaCaja.find('td:eq(4) img');

                if (detallesCaja.estado === "online") {
                    estadoIcono.attr("src", "icons/wifi_30dp_434343.svg");
                } else if (detallesCaja.estado === "offline") {
                    estadoIcono.attr("src", "icons/wifi_off_30dp_BB271A.svg");
                } else {
                    estadoIcono.attr("src", "icons/alert-circle.svg").removeClass("icon-success icon-danger").addClass("icon-warning");
                }
                estadoIcono.addClass('animate-blink');
                setTimeout(function () {
                    estadoIcono.removeClass('animate-blink');
                }, 2000); // Ajuste de tiempo milisegundos

                filaCaja.find('td:eq(3)').text(detallesCaja.tickets);
                var celdaTickets = filaCaja.find('td:eq(3)');
                celdaTickets.text(detallesCaja.tickets);
                var ticketValue = parseInt(detallesCaja.tickets, 10);
                if (isNaN(ticketValue) || ticketValue === 0) {
                    celdaTickets.removeClass('ticket-otros').addClass('ticket-cero');
                } else {
                    celdaTickets.removeClass('ticket-cero').addClass('ticket-otros');
                }
            }

            function setSSHLink(ipCaja) {
                if (ipCaja) {
                    location.href = 'ssh://root@' + ipCaja;
                } else {
                    alert('No se ha encontrado la ip de caja');
                }
            }

            function setVNCLink(ipCaja) {
                if (ipCaja) {
                    location.href = 'vnc://' + ipCaja + ':5900';
                } else {
                    alert('Not VNC connected');
                }
            }

            function setConsola(ipConsola) {
                if (ipConsola) {
                    window.open('https://' + ipConsola + ':8444/console');
                } else {
                    alert('No se ha encontrado la ip para Geoconsola');
                }
            }

            function setPricer(ipPricer) {
                if (ipPricer) {
                    window.open('http://' + ipPricer + ':9090/GEOPricer/view/start/login.jsf');
                } else {
                    alert('Not GeoPricer connected');
                }
            }

            function setConciliacion(ipConciliacion) {
                if (ipConciliacion) {
                    window.open('https://' + ipConciliacion + ':8443/GeoConciliacion-web');
                } else {
                    alert('Not GeoConciliacion connected');
                }
            }

            function setVirtual(ipVirtual) {
                console.log("IP Virtual ingresada: ", ipVirtual);
                if (ipVirtual) {
                    window.open('http://' + ipVirtual, '_blank');
                } else {
                    alert('No se ha accedido a una IP virtual valida');
                }
            }

            function setServerssh(ipServer) {
                var user = '<%= user.getIdUsuario()%>';
                if (ipServer) {
                    location.href = 'ssh://' + user + '@root@' + ipServer + '@10.42.31.59';
                } else {
                    alert('No se ha encontrado la ip de caja');
                }
            }

            function setFlejeElectronico(ipFlejes) {
                console.log("Ip FLeje Electronico: ", ipFlejes);
                if (ipFlejes) {
                    window.open('https://' + ipFlejes + '/ns/authenticate?');
                } else {
                    alert('No corresponde a un servidor para fleje electronico');
                }
            }
            function obtenerReversas() {
                var local = $('#local').val(); // Obtener el valor del local actual
                // Realiza la solicitud al servidor para obtener las reversas
                $.get('SvTickets?local=' + local, function (resultados) {
                    console.log(resultados);
                    var contenidoReverse = "<div style='font-size:24px; font-weight:bold; margin-bottom:10px; text-align:center; color:#333;'>Cajas con reversa</div>";
                    contenidoReverse += "<table style='width:100%; border-collapse:collapse; border:1px solid #ccc;'><thead><tr style='background-color:#009879; color:#ffffff;'><th style='padding:12px 15px;'>Caja</th><th style='padding:12px 15px;'>Reversa</th></tr></thead><tbody>";

                    $.each(resultados, function (index, detalles) {
                        var reverse = detalles.reverse || 'N/A'; // Usa 'N/A' si no hay datos de reverse
                        contenidoReverse += "<tr style='border-bottom:1px solid #ccc;'><td style='padding:12px 15px;'>" + index + "</td><td style='padding:12px 15px;'>" + reverse + "</td></tr>";
                    });

                    contenidoReverse += "</tbody></table>";

                    // Configurar TinyMCE para cargar el contenido en el div del modal
                    tinymce.init({
                        selector: '#tinymceEditor',
                        plugins: [],
                        toolbar: false,
                        menubar: false,
                        statusbar: false,
                        branding: false,
                        elementspath: false,
                        init_instance_callback: function (editor) {
                            editor.setContent(contenidoReverse); // Cargamos el contenido dinámico
                            document.getElementById("myModal").style.display = "block"; // Mostrar el modal después de inicializar TinyMCE
                        }
                    });
                });
            }

            // Cerrar el modal si se hace clic fuera de la ventana modal
            window.onclick = function (event) {
                var modal = document.getElementById("myModal");
                if (event.target === modal) {
                    cerrarModal();
                }
            }

            function cerrarModal() {
                document.getElementById("myModal").style.display = "none";
                tinymce.remove('#tinymceEditor'); // Eliminar la instancia de TinyMCE al cerrar el modal
            }
        </script>
        <style>
            .form-control, .input-group-text, .status-icon {
                height: calc(1.5em + .75rem + 2px); /* Ajustar según sea necesario */
                line-height: calc(1.5em + .75rem + 2px); /* centra verticalmente el contenido */
            }
            @media (min-width: 992px) {
                .form-container {
                    display: flex;
                    justify-content: space-between;
                    flex-wrap: wrap;
                }
                .form-container .input-section,
                .form-container .button-section {
                    width: 48%;
                }
                .form-container .full-width {
                    width: 100%;
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                }
                .form-container .full-width .input-group {
                    width: calc(50% - 10px);
                }
            }
            .btn-outline-dark {
                width: 100%;
                max-width: 200px;
                margin: 0 auto;
            }
            .table-btn {
                width: auto;
                max-width: none;
                margin: 0;
            }
            .input-group-text {
                border-radius: 0.25rem;
            }
            .icon-svg {
                width: 1.5rem;
                height: 1.5rem;
                margin-right: 0.5rem;
            }
            .status-icon {
                height: 20px;
                width: 20px;
                border-radius: 50%;
                display: inline-block;
                margin: 0 auto;
                position: relative;
                padding: 0;
            }
            .bg-success {
                background-color: #434343;
            }

            .bg-danger {
                background-color: #BB271A;
            }

            .bg-warning {
                background-color: white;
            }

            #todasLasCajas,
            #todasLasBalanzas {
                width: 100% !important;
                min-height: 100px;
                background-color: #a3cfbb;
                border-radius: 15px;
                box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            }
            #todasLasCajas table {
                width: 100% !important;
                border-collapse: collapse;
            }
            #todasLasCajas th,
            #todasLasBalanzas th,
            #todasLasConsultas th {
                border-bottom: 2px solid #000;
                padding-bottom: 4px;
            }

            #todasLasCajas button {
                width: 80px;
                height: 30px;
                font-family: 'Roboto', sans-serif;
                font-size: 11px;
                text-transform: uppercase;
                letter-spacing: 2.5px;
                font-weight: 500;
                color: #000;
                background-color: #fff;
                border: none;
                border-radius: 45px;
                box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
                transition: all 0.3s ease 0s;
                cursor: pointer;
                outline: none;
                margin-left: 10px;
            }

            #todasLasCajas button:hover {
                background-color: #C0C0C0;
                box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
                color: #fff;
                transform: translateY(-7px);
            }

            .spinner-border {
                animation: spin 1s linear infinite;
            }
            .worko-tabs {
                margin: 20px auto;
                width: 80%;
                position: relative;
                top: 50%;
            }
            .worko-tabs .state {
                position: absolute;
                left: -10000px;
            }
            .worko-tabs .flex-tabs {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }
            .worko-tabs .tab {
                flex-grow: 1;
                max-height: 40px;
            }
            .worko-tabs .panel {
                background-color: #fff;
                padding: 20px;
                min-height: 300px;
                display: none;
                width: 100%;
                flex-basis: auto;
            }
            .worko-tabs .tab {
                display: inline-block;
                padding: 10px;
                vertical-align: top;
                background-color: #eee;
                cursor: hand;
                cursor: pointer;
                border-left: 10px solid #ccc;
            }
            .worko-tabs .tab:hover {
                background-color: #fff;
            }
            .worko-tabs #tab-one:checked ~ .tabs #tab-one-label,
            .worko-tabs #tab-two:checked ~ .tabs #tab-two-label,
            .worko-tabs #tab-three:checked ~ .tabs #tab-three-label,
            .worko-tabs #tab-four:checked ~ .tabs #tab-four-label {
                background-color: #fff;
                cursor: default;
                border-left-color: #69be28;
            }
            .worko-tabs #tab-one:checked ~ .tabs #tab-one-panel,
            .worko-tabs #tab-two:checked ~ .tabs #tab-two-panel,
            .worko-tabs #tab-three:checked ~ .tabs #tab-three-panel,
            .worko-tabs #tab-four:checked ~ .tabs #tab-four-panel {
                display: block;
            }
            @media (max-width: 600px) {
                .worko-tabs .flex-tabs {
                    flex-direction: column;
                }
                .worko-tabs .tab {
                    background: #fff;
                    border-bottom: 1px solid #ccc;
                }
                .worko-tabs .tab:last-of-type {
                    border-bottom: none;
                }
                .worko-tabs #tab-one-label {
                    order: 1;
                }
                .worko-tabs #tab-two-label {
                    order: 3;
                }
                .worko-tabs #tab-three-label {
                    order: 5;
                }
                .worko-tabs #tab-four-label {
                    order: 7;
                }
                .worko-tabs #tab-one-panel {
                    order: 2;
                }
                .worko-tabs #tab-two-panel {
                    order: 4;
                }
                .worko-tabs #tab-three-panel {
                    order: 6;
                }
                .worko-tabs #tab-four-panel {
                    order: 8;
                }
                .worko-tabs #tab-one:checked ~ .tabs #tab-one-label,
                .worko-tabs #tab-two:checked ~ .tabs #tab-two-label,
                .worko-tabs #tab-three:checked ~ .tabs #tab-three-label,
                .worko-tabs #tab-four:checked ~ .tabs #tab-four-label {
                    border-bottom: none;
                }
                .worko-tabs #tab-one:checked ~ .tabs #tab-one-panel,
                .worko-tabs #tab-two:checked ~ .tabs #tab-two-panel,
                .worko-tabs #tab-three:checked ~ .tabs #tab-three-panel,
                .worko-tabs #tab-four:checked ~ .tabs #tab-four-panel {
                    border-bottom: 1px solid #ccc;
                }

            }

            /* El Modal (oculto por defecto) */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
            }

            /* Contenido del Modal */
            .modal-content {
                background-color: #fefefe;
                margin: 10% auto;
                padding: 20px;
                border-radius: 8px;
                border: 1px solid #888;
                width: 50%; /* Reducir ancho del modal */
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            }

            /* La X (cierre) */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            /* Estilos para la tabla */
            .styled-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 18px;
                text-align: left;
                overflow: hidden;
                border-radius: 8px;
            }

            .styled-table thead tr {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
            }

            .styled-table th,
            .styled-table td {
                padding: 12px 15px;
                border-bottom: 1px solid #dddddd;
            }

            .styled-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }

            .styled-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            .styled-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }

            .styled-table tbody tr:hover {
                background-color: #f1f1f1;
            }

            .table-title {
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 10px;
                text-align: center;
                color: #333;
            }
        </style>

        <%@include file='jsp/footer.jsp' %>
    </body>

</html>

<% }%>
