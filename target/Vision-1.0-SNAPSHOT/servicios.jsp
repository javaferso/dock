<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Usuario"%>

<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Panel Administración</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="favicon.ico"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="css/fontawesome.css" rel="stylesheet" />
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <link href="css/footers.css" rel="stylesheet" type="text/css"/>
        <link href="tablesorter-master/dist/css/theme.default.min.css" rel="stylesheet" type="text/css"/>
        <link href="tablesorter-master/dist/css/filter.formatter.min.css" rel="stylesheet" type="text/css"/>
        <script src="tablesorter-master/dist/js/jquery.tablesorter.min.js"></script>
        <script src="tablesorter-master/dist/js/jquery.tablesorter.widgets.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="poppers/popper.js"></script>
        <script src="js/script.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/sidebars.js"></script>
    </head>
    <body class="body-boleta">
        <aside class="sidebar">
            <!-- Sidebar -->
            <nav>
                <!-- Admin Functions -->
                <a><hr class="dropdown-divider"></a>
                <a><hr class="dropdown-divider"></a>
                <a href="javascript:listarIncidentes()" id="list-incidents" data-bs-toggle="tooltip" data-bs-placement="right" title="Listar Incidentes" style="color: #ffffff;">
                    <i class="fas fa-list text-white mb-1 sidebar-icon"></i>
                </a>
                <a href="javascript:agregarIncidente()" id="add-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Agregar Incidente">
                    <i class="fas fa-plus text-white mb-1 sidebar-icon"></i>
                </a>
                <a href="javascript:editarIncidente()" id="edit-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Editar Incidente">
                    <i class="fas fa-edit text-white mb-1 sidebar-icon"></i>
                </a>
                <a><hr class="dropdown-divider"></a>
                <a><hr class="dropdown-divider"></a>
                <a href="javascript:IncidentePendientes()" id="pending-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Incidentes Pendientes">
                    <i class="fa-regular fa-hourglass-half"></i>
                </a>
                <a href="javascript:eliminarIncidente()" id="delete-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Eliminar Incidente">
                    <i class="fas fa-trash text-white mb-1 sidebar-icon"></i>
                </a>

                <a><hr class="dropdown-divider"></a>
                <a onclick="exitMonitor()" class="dropdown-item" href="index.jsp">
                    <i class="fas fa-sign-out-alt text-white mb-3 sidebar-icon"></i>
                </a>
            </nav>
        </aside>
        <header>
            <%@include file="jsp/navbar.jsp" %>
        </header>
         
        <div class="container-fluid">
            
            <!-- Main Content -->
            <div id="main-content" class="w-100 ml-3">
                <!-- Your main content will appear here -->
            </div>
        </div>
        <!-- Botón de Volver Arriba -->
        <button 
            type="button"
            class="btn btn-danger btn-floating btn-lg"
            id="backToTop">
            <i class="fas fa-arrow-up"></i>
        </button>
        <!-- Botón para desplazarse hacia la izquierda -->
        <button id="scrollLeft" title="Scroll Left" style="display: none; position: fixed; bottom: 60px; left: 10px; z-index: 9999;"><i class="fas fa-arrow-left"></i></button>

        <!-- Botón para desplazarse hacia la derecha -->
        <button id="scrollRight" title="Scroll Right" style="display: none; position: fixed; bottom: 60px; right: 10px; z-index: 9999;"><i class="fas fa-arrow-right"></i></button>

        <!-- JavaScript for changing content -->
        <script>
            function listarIncidentes() {
                console.log("listarIncidentes called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvIncidente?action=list', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
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
                        
                        inicializarTablesorter(); // Inicializar tablesorter después de cargar el contenido
                    }
                };
                xhr.send();
            }

            function agregarIncidente() {
                console.log("agregarIncidente called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvIncidente?action=loadForm', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;

                        // Inicializa el autocompletado aquí
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
                            },
                            minLength: 2
                        });
                    }
                };
                xhr.send();

                // Mover la definición de los eventos aquí, fuera del bloque if
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
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    alert("Error: " + jqXHR.responseJSON.error);
                                    console.log("Error en la obtención de información del local", textStatus, errorThrown);
                                    $('#tienda').val('');
                                    $('#sap').val('');
                                    $('#formato').val('');
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
            }

            function eliminarIncidente() {
                console.log("eliminarIncidente called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvIncidente?action=delete-form', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function editarIncidente() {
                console.log("editarIncidente called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvIncidente?action=edit-form', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function inicializarTablesorter() {
                $("#details").tablesorter({
                    theme: 'blue',
                    widgets: ['zebra', 'filter', 'pager', 'stickyHeaders'],
                    widgetOptions: {
                        zebra: ["even", "odd"],
                        filter_reset: ".reset",
                        filter_cssFilter: 'form-control',
                        filter_columnFilters: true,
                        filter_placeholder: {search: 'Buscar...', select: 'Seleccionar filtro'},
                        pager_output: '{startRow} - {endRow} / {filteredRows} ({totalRows})',
                        pager_selectors: {
                            container: '#pager',
                            first: '.first',
                            prev: '.prev',
                            next: '.next',
                            last: '.last',
                            gotoPage: '.gotoPage',
                            pageDisplay: '.pagedisplay',
                            pageSize: '.pagesize'
                        },
                        stickyHeaders: "thead"
                    }
                });
            }

            window.onload = function () {
                listarIncidentes();
            };

            document.addEventListener("DOMContentLoaded", function () {
                const actions = {
                    "list-incidents": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el listado de incidentes.';
                    },
                    "add-incident": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el formulario para agregar incidentes.';
                    },
                    "delete-incident": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el formulario para borrar incidentes.';
                    },
                    "edit-incident": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el formulario para editar incidentes.';
                    }
                };
                Object.keys(actions).forEach(function (id) {
                    document.getElementById(id).addEventListener('click', actions[id]);
                });
            });
            // Función para desplazarse hacia la izquierda
            $('#scrollLeft').click(function (event) {
                event.preventDefault();
                $(window).scrollBy(-250, 0); // Desplazar 250px hacia la izquierda
            });

            // Función para desplazarse hacia la derecha
            $('#scrollRight').click(function (event) {
                event.preventDefault();
                $(window).scrollBy(250, 0); // Desplazar 250px hacia la derecha
            });

        </script>
    </body>
</html>
