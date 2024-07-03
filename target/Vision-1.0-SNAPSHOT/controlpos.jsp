<%-- 
    Document   : formulario
    Created on : Apr 30, 2024, 12:02:39 AM
    Author     : JFerreira
--%>

<%@page import="logica.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 

<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ControlPos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="fontawesome/css/fontawesome.css" rel="stylesheet" type="text/css"/>
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <script src="poppers/popper.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/stickyfill.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
    </head>
    <header>
        <%@ include file="jsp/navbar.jsp" %>
    </header>
    <% if (user == null) {
            // Redirigir a Login
            response.sendRedirect("login.jsp");
        } else {%>   
    <body class="body-boleta">
        <%
            System.out.println("controlpos called for user: " + user.getIdUsuario());
        %>

        <div class="container-fluid">
            <div class="shadow p-3 mb-5 bg-body-tertiary rounded">
                <div class="row">

                    <label for="tiendaInput" class="col-md-1 col-form-label">Tienda:</label>

                    <div class="col-2">
                        <input type="text" id="localId" class="form-control" value="">
                    </div>
                    <div class="col-2">

                    </div> 
                    <div class="col-2">
                        <button type="button" class="btn btn-outline-dark me-md-2" id="ip_virtual" value="" onclick="setVirtual($('#ipVirtualInput').val())">Serv. Virtual</button>
                    </div>   

                    <label for="flejeraInput" class="col-md-1 form-group row d-flex justify-content-end">Flejera:</label>     

                    <div class="col-3">
                        <input type="text" id="flejeraInput" class="form-control" value="" readonly>
                    </div>
                </div>
                <div class="row">
                    <!-- Campo oculto para la IP Virtual -->
                    <input type="hidden" id="ipVirtualInput" value="">
                    <!-- Fila para Nombre e Input -->

                    <label for="nombreInput" class="col-md-1 col-form-label">Nombre:</label>

                    <div class="col-4">
                        <input type="text" id="nombreTienda" class="form-control w-full" value="" readonly>
                    </div>
                    <div class="col-2">

                    </div>
                    <!-- Fila para Nombre e Input -->

                    <label for="serieInput" class="col-md-1 form-group row d-flex justify-content-end">Formato:</label>

                    <div class="col-2">
                        <input type="text" id="nombreInput" class="form-control" value="" readonly>
                    </div>
                </div>
                <div class="row">

                    <label for="direccionInput" class="col-md-1 col-form-label">Dirección:</label>

                    <div class="col-md-4">
                        <input type="text" id="direccionInput" class="form-control" value="" readonly>
                    </div>   
                    <div class="col-2">
                        <button type="button" class="btn btn-outline-dark me-md-2" id="ipServer" value="" onclick="setServerssh($('#iPservidorInput').val())">SSHServidor</button>
                    </div>       
                    <label for="ipInput" class="col-md-1 form-group row d-flex justify-content-end">IP Serv:</label>        
                    <div class="col-2">
                        <input type="text" id="iPservidorInput" class="form-control w-full" value="" readonly>
                    </div>         
                    <div class="col-1">
                        <button type="button" class="btn status-icon bg-success" id="status-icon-ip" onclick="" ></button>
                    </div>        
                </div>            
                <!-- Fila para Teléfono e Input -->
                <div class="row">
                    <label for="telefonoInput" class="col-md-1 col-form-label">Ciudad:</label>
                    <div class="col-md-4">
                        <input type="text" id="cityInput" class="form-control" value="" readonly>
                    </div>
                    <div class="col-2">

                    </div>
                    <label for="enlaceInput" class="col-md-1 form-group row d-flex justify-content-end">P.Enlace:</label>

                    <div class="col-2">
                        <input type="text" id="enlaceInput" class="form-control" value="" readonly>
                    </div>
                    <div class="col-1">
                        <button type="button" class="btn status-icon bg-success" id="status-icon-enlace"></button>
                    </div>
                </div> 
            </div>

            <div id="todasLasCajas" class="bg-light p-3 shadow-sm mb-3 mt-3" style="border-radius: 15px; background-color: #a3cfbb;"></div>
            <div id="loading" style="display: none;">
                <div class="spinner-border text-primary" role="status">
                    <span class="sr-only">Cargando...</span>
                </div>
            </div>
            <div>
                <div class="d-grid gap-2 d-md-flex justify-content-xl-end">
                    <input type="text" class="input-group-lg" id="ipCajaOculta" value="" placeholder="Ip Seleccionada"/>
                    <button
                        type="button"
                        id="verSSHButton"
                        class="btn btn-outline-dark me-md-2"
                        value=""
                        onclick="setSSHLink()"
                        >SSH Pos</button>
                    <input type="hidden" id="ipPosShadow" value=''/>
                    <button
                        type="button"
                        id="verVNCButton"
                        class="btn btn-outline-dark me-md-2"
                        value=""
                        onclick="setVNCLink()"
                        >VNC Pos</button>

                    <button
                        type="button"
                        id="ip_consola"
                        class="btn btn-outline-dark me-md-2"
                        value=""
                        onclick="setConsola($('#iPservidorInput').val())"
                        >Geoconsola</button>
                    <input type="hidden" id="ipPosShadow" value=''/>
                    <button
                        type="button"
                        id="ip_pricer"
                        class="btn btn-outline-dark me-md-2"
                        value=""
                        onclick="setPricer($('#iPservidorInput').val())"
                        >GeoPricer</button>
                    <button
                        type="button"
                        id="ip_conciliacion"
                        class="btn btn-outline-dark me-md-2"
                        value=""
                        onclick="setConciliacion($('#iPservidorInput').val())"
                        >GeoConciliacion</button>
                </div>
            </div>
        </div>
        <script>
            var ipOculta = [];

            $(document).ready(function () {
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
                        $('#nombreTienda').val(data.formato + " " + data.nombreTienda);
                        $('#direccionInput').val(data.direccion);
                        $('#cityInput').val(data.ciudad);
                        $('#iPservidorInput').val(data.ipAddress);
                        $('#ipVirtualInput').val(data.ipVirtual);
                        $('#enlaceInput').val(data.ipEnlace);
                        $('#nombreInput').val(data.formato);
                        $('#ipOculta').val(data.ipAddress);
                        cargarCajas(data.local);
                        updateStatusIcon('#status-icon-ip', data.estadoIp);
                        updateStatusIcon('#status-icon-enlace', data.estadoEnlace);
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
                                    $('#nombreTienda').val(concatenatedData);
                                    $('#direccionInput').val(data.direccion);
                                    $('#iPservidorInput').val(data.ipAddress);
                                    $('#ipVirtualInput').val(data.ipVirtual);
                                    $('#enlaceInput').val(data.ipEnlace);
                                    $('#nombreInput').val(data.formato);
                                    $('#cityInput').val(data.ciudad);
                                    $('#ipOculta').val(data.ipAddress);
                                    cargarCajas(data.local);
                                    updateStatusIcon('#status-icon-ip', data.estadoIp);
                                    updateStatusIcon('#status-icon-enlace', data.estadoEnlace);
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
                    icon.addClass('bg-warning');
                } else {
                    icon.addClass('bg-danger');
                }
            }

            var cajasOrdenadas = [];
            var userRole = <%= user.getIdRole()%>;

            $(document).ready(function () {
                $('#todasLasCajas').on('click', 'tr', function () {
                    $('tr').removeClass('selected');
                    $(this).addClass('selected');
                    var ipCaja = $(this).find('td:eq(1)').text();
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
                                    $('<th>').text('Caja'),
                                    $('<th>').text('IpAddress'),
                                    $('<th>').text('Tickets'),
                                    $('<th>').text('Estado'),
                                    $('<th>').text('Acción')
                                    );
                            tabla.append(cabecera);
                            // Itera sobre las cajas ordenadas y agrega las filas a la tabla
                            cajasOrdenadas.forEach(function (caja) {
                                if (resultados[caja]) {
                                    var detalles = resultados[caja];
                                    var claseEstado = detalles.estado === 'online' ? 'estado-online' : 'estado-offline';
                                    var ticketValue = parseInt(detalles.tickets, 10);
                                    var claseTicket = ticketValue === 0 ? 'ticket-cero' : 'ticket-otros';
                                    var fila = $('<tr>').append(
                                            $('<td>').text(caja),
                                            $('<td>').text(detalles.ip),
                                            $('<td>').addClass(claseTicket).text(detalles.tickets),
                                            $('<td>').addClass(claseEstado).text(detalles.estado),
                                            $('<td>').append(
                                            $('<button>').addClass('btn btn-outline-dark me-md-2').text('Ping').click(function () {
                                        hacerPing(caja, local);
                                    })
                                            )
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
                                $('<th>').text('Caja'),
                                $('<th>').text('IpAddress'),
                                $('<th>').text('Tickets'),
                                $('<th>').text('Estado'),
                                $('<th>').text('Acción')
                                );
                        tabla.append(cabecera);
                        $.each(resultadosOrdenados, function (caja, detalles) {
                            var claseEstado = detalles.estado === 'online' ? 'estado-online' : 'estado-offline';
                            var ticketValue = parseInt(detalles.tickets, 10);
                            var claseTicket = ticketValue === 0 ? 'ticket-cero' : 'ticket-otros';
                            console.log('Caja:', caja, 'Ticket Value:', ticketValue, 'Clase Ticket:', claseTicket);
                            var fila = $('<tr>').append(
                                    $('<td>').text(caja),
                                    $('<td>').text(detalles.ip),
                                    $('<td>').addClass(claseTicket).text(detalles.tickets),
                                    $('<td>').addClass(claseEstado).text(detalles.estado),
                                    $('<td>').append(
                                    $('<button>').addClass('btn btn-outline-dark me-md-2').text('Ping').click(function () {
                                hacerPing(caja, localSeleccionado);
                            })
                                    )
                                    );
                            tabla.append(fila);
                        });
                        contenedor.append(tabla);
                    });
                }
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
                filaCaja.find('td:eq(3)').text(detallesCaja.ip);
                var estadoCaja = filaCaja.find('td:eq(3)');
                estadoCaja.text(detallesCaja.estado).removeClass('estado-online estado-offline').addClass(detallesCaja.estado === 'online' ? 'estado-online' : 'estado-offline');
                estadoCaja.addClass('animate-blink');
                setTimeout(function () {
                    estadoCaja.removeClass('animate-blink');
                }, 2000); // Ajuste de tiempo milisegundos
            }

            function setSSHLink() {
                var ipCaja = $('#ipCajaOculta').val();
                if (ipCaja) {
                    location.href = 'ssh://root@' + ipCaja;
                } else {
                    alert('No se ha encontrado la ip de caja');
                }
            }

            function setVNCLink() {
                var ipCaja = $('#ipCajaOculta').val();
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
        </script>
        <style>
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
               background-color: yellow;
            }

            .bg-danger {
               background-color: red;
            } 

            .bg-warning {
               background-color: white;
            }
            #todasLasCajas {
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
            #todasLasCajas th {
               border-bottom: 2px solid #000;
               padding-bottom: 4px;
            }

            .spinner-border {
               animation: spin 1s linear infinite;
            }
        </style>
        <%@include file='jsp/footer.jsp' %>
    </body>

</html>

<% }%>
