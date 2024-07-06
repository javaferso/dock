<%-- 
    Document   : servicios
    Created on : Jul 1, 2024, 11:09:41 PM
    Author     : JFerreira
--%>
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
    <aside class="sidebar">

        <!-- Sidebar -->

        <nav>
            <!-- Admin Functions -->
            <a href="javascript:listarIncidentes()" id="list-incidents" data-bs-toggle="tooltip" data-bs-placement="right" title="Listar Incidentes" style="color: #ffffff;">
                <i class="fas fa-list fa-1.5x text-white mb-1 sidebar-icon"></i>
            </a>
            <a href="javascript:agregarIncidente()" id="add-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Agregar Incidente">
                <i class="fas fa-plus fa-1.5x text-white mb-1 sidebar-icon"></i>
            </a>
            <a href="javascript:eliminarIncidente()" id="delete-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Eliminar Incidente">
                <i class="fas fa-trash fa-1.5x text-white mb-1 sidebar-icon"></i>
            </a>
            <a href="javascript:editarIncidente()" id="edit-incident" data-bs-toggle="tooltip" data-bs-placement="right" title="Editar Incidente">
                <i class="fas fa-edit fa-1.5x text-white mb-1 sidebar-icon"></i>
            </a>
            <a><hr class="dropdown-divider"></a>
            <a><hr class="dropdown-divider"></a>
            <a><hr class="dropdown-divider"></a>
            <a><hr class="dropdown-divider"></a>
            <a onclick="exitMonitor()" class="dropdown-item" href="index.jsp">
                <i class="fas fa-sign-out-alt fa-1.5x text-white mb-3 sidebar-icon"></i>
            </a>
        </nav>


    </aside>
    <header>
        <%@include file="jsp/navbar.jsp" %>
    </header>
    <body class="body-boleta">



        <div class="container-fluid">
            <!-- Main Content -->
            <div id="main-content" class="w-100 ml-3">
                <!-- Your main content will appear here -->
            </div>
        </div>

        <!-- JavaScript for changing content -->
        <script>
            function listarIncidentes() {
                console.log("listarIncidentes called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvIncidente?action=list', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
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
                    }
                };
                xhr.send();
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
                        theme: 'default',
                        widgets: ['zebra', 'filter'],
                        widgetOptions: {
                            zebra: ["even", "odd"],
                            filter_reset: ".reset"
                        }
                    });
            }
            window.onload = function () {
                listarIncidentes();
            };

            document.addEventListener("DOMContentLoaded", function () {
                event.preventDefault();
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

        </script>
    </body>
</html>
