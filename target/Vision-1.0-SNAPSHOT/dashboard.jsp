<%-- 
Document : dashboard 
Created on : Jun 5, 2023, 8:07:31 PM 
Author :
JFerreira --%> 

<%@page import="logica.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>DockPOS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="fontawesome/css/fontawesome.css" rel="stylesheet" type="text/css"/>
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="poppers/popper.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/stickyfill.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
    </head>
    <header>
        <%@ include file="jsp/navbar.jsp" %>
    </header>
    <% if (user == null) {
            //Redirigir a Login
            response.sendRedirect("login.jsp");
        } else {%>   
    <div id="notification-container"></div>

    <div class="row">
        <div class="col-1 p-3 mb-2">
            <%@ include file="jsp/sidebar.jsp" %>
        </div>
                    
        
        <div class="col-sm-11">
            <body class="body-boleta">
                <p>Bienvenido <%= user.getNombre() + " " + user.getApellido()%></p>

                <div class="container-fluid mt-3 mb-5 p-2">
                    <div class="row">
                        <div class="col">
                            <div class="d-flex flex-wrap align-items-center bg-light p-3 shadow" style="border-radius: 15px; background-color: #f5f5f5;">
                                <div class="p-2 flex-fill">
                                    <label for="formato" class="form-label">Formato:</label>
                                    <select id="formato" class="form-select" onchange="cargarLocales()">
                                        <option value="">Seleccionar formato</option>
                                    </select>
                                </div>
                                <div class="p-2 flex-fill">
                                    <label for="local" class="form-label">Local:</label>
                                    <select id="local" class="form-select" onchange="cargarCajas()">
                                        <option value="">Seleccionar local</option>
                                    </select>
                                </div>
                                <div class="p-2 flex-fill">
                                    <label for="caja" class="form-label">Caja:</label>
                                    <select id="caja" class="form-select">
                                        <option value="">Seleccionar caja</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bg-light p-3 shadow mb-3" style="border-radius: 15px; background-color: #a3cfbb;">
                    <div class="row">
                        <div class="col">
                            <h4 class="mb-2">Información de la Tienda</h4>
                            <label for="nombreTienda" class="form-label">Tienda:</label>
                            <input type="text" id="nombreTienda" class="form-control" readonly="readonly" />
                        </div>
                    </div>
                </div>
                <br>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <input type="hydden" class="input-group-text" id="ipCajaOculta" value=""/>
                    <input
                        type="submit"
                        class="btn btn-outline-dark me-md-2"
                        value="Iniciar Monitoreo"
                        onclick="location.href = 'monitor.jsp'"
                        />

                    <% if (user.getIdRole() == 1) { %>
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Buscar Boleta"
                        onclick="location.href = 'buscarBoleta.jsp'"
                        />
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Instalar VNC"
                        onclick="location.href = 'instal_vnc.jsp'"
                        />
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Actualizar Peso Sco"
                        onclick="location.href = 'sco.jsp'"
                        />
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Log Ecommerce"
                        onclick="location.href = 'buscarLogEcommerce.jsp'"
                        />
                    <input
                        type="button"
                        id ="verSSHButton"
                        class="btn btn-outline-dark me-md-2"
                        value="SSH Pos"
                        onclick="setSSHLink()"
                        />
                    <% } else if (user.getIdRole() == 2) { %>
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Buscar Boleta"
                        onclick="location.href = 'buscarBoleta.jsp'"
                        />
                    <% } else if (user.getIdRole() == 3) { %>
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Instalar vnc"
                        onclick="location.href = 'instal_vnc.jsp'"
                        />
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Actualizar Peso Sco"
                        onclick="location.href = 'sco.jsp'"
                        />
                    <input
                        type="button"
                        id="verSSHButton"
                        class="btn btn-outline-dark me-md-2"
                        value="SSH Pos"
                        onclick="setSSHLink()"
                        />

                    <% } else if (user.getIdRole() == 5) { %>
                    <input
                        type="button"
                        class="btn btn-outline-dark me-md-2"
                        value="Log Ecommerce"
                        onclick="location.href = 'buscarLogEcommerce.jsp'"
                        />
                    <% }%>


                </div>


                <script>
                        var userRole = <%= user.getIdRole()%>;
                        $(document).ready(function () {
                            cargarFormatos();

                            $('#formato').change(function () {
                                cargarLocales();
                            });

                            $('#local').change(function () {
                                cargarCajas();
                            });
                        });

                        function cargarFormatos() {
                            $.get('SvDatosLocal?action=getFormatos', function (data) {
                                $('#formato').empty().append('<option value="">Seleccionar formato</option>');
                                data.forEach(function (item) {
                                    $('#formato').append('<option value="' + item + '">' + item + '</option>');
                                });
                                if (userRole == 5) {
                                    $('#formato').val('MFC');
                                    cargarLocales();
                                }
                            });
                        }

                        function cargarLocales() {
                            var formato = $('#formato').val();
                            $.get('SvDatosLocal?action=getLocales&formato=' + formato, function (data) {
                                $('#local').empty().append('<option value="">Seleccionar local</option>');
                                data.forEach(function (item) {
                                    $('#local').append('<option value="' + item + '">' + item + '</option>');
                                });
                            });
                        }

                        function cargarNombreTienda() {
                            var local = $('#local').val();
                            if (local) {
                                $.get('SvDatosLocal?action=getNombreTienda&local=' + local, function (data) {
                                    var nombreCompletoTienda = local + '  ' + data;
                                    $('#nombreTienda').val(nombreCompletoTienda);
                                    //console.log("Data Tienda: " + data);
                                });
                            }

                        }

                        function cargarCajas() {
                            var local = $('#local').val();
                            $.get('SvDatosLocal?action=getCajas&local=' + local, function (data) {
                                $('#caja').empty().append('<option value="">Seleccionar caja</option>');
                                data.forEach(function (item) {
                                    $('#caja').append('<option value="' + item + '">' + item + '</option>');
                                });

                            });
                        }
                        $('#local').change(function () {
                            cargarCajas();
                            cargarNombreTienda();
                        });
                        function setSSHLink() {
                            var ipCaja = $('#ipCajaOculta').val();
                            if (ipCaja) {
                                location.href = 'ssh://root@' + ipCaja;
                            } else {
                                alert('No se ha encontrado la ip de caja');
                            }
                        }

                        $('#caja').change(function () {
                            var cajaSeleccionada = $(this).val();
                            var localSeleccionado = $('#local').val();

                            $.ajax({
                                url: 'SvDatosLocal',
                                method: 'GET',
                                data: {
                                    action: 'getIp',
                                    caja: cajaSeleccionada,
                                    local: localSeleccionado
                                },
                                success: function (ipCaja) {
                                    $('#ipCaja').val(ipCaja);
                                    $('#ipCajaOculta').val(ipCaja);
                                    var formatoSeleccionado = $('#formato').val();
                                    var nombreTiendaseleccionado = $('#nombreTienda').val();
                                    $.ajax({
                                        url: 'SvIniciarMonitor',
                                        method: 'POST',
                                        data: {
                                            caja: cajaSeleccionada,
                                            local: localSeleccionado,
                                            formato: formatoSeleccionado,
                                            ipCaja: ipCaja,
                                            nombreTienda: nombreTiendaseleccionado
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            console.log('Error: ' + textStatus + ' ' + errorThrown);
                                        }
                                    });

                                }

                            });

                        });


                </script>
                <script>
                    $(function () {
                        $('[data-toggle="tooltip"]').tooltip();
                    });
                    var navbar = document.querySelector('.navbar-custom');
                    Stickyfill.add(navbar);


                </script>
                <br>
                <%@include file="jsp/footer.jsp" %>

            </body>
        </div>
    </div>


</html>
<% }%>
