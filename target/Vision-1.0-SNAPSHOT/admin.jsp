<%-- 
    Document   : admin
    Created on : Aug 11, 2023, 10:45:52 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="css/style.css" />
        <link href="css/sidebars.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="css/fontawesome.css" rel="stylesheet" />
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <link rel="stylesheet" th:href="@{/webjars/bootstrap/5.3.1/css/bootstrap.min.css}" />
        <link href="css/footers.css" rel="stylesheet" type="text/css"/>
        <script th:src="@{/webjars/jquery/3.6.3/jquery.min.js}"></script>
        <script th:src="@{/webjars/bootstrap/5.3.1/js/bootstrap.min.js}"></script>
        <script src="@{/webjars/popper.js/1.16.1/umd/popper.min.js}"></script>
        <script src="js/script.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/sidebars.js"></script>
    </head>
    <body>
        <header>
            <%@include file="jsp/navbar.jsp" %>
        </header>

        <div class="col-2">
            <!-- Sidebar -->
            <div class="sidebar d-flex flex-column align-items-left py-6 mt-auto mb-6" style="font-family: 'Roboto', sans-serif; font-size: 14px; width: 150px;">
                <br>
                <!-- Admin Functions -->
                <a href="javascript:listarUsuarios()" id="list-users" data-bs-toggle="tooltip" data-bs-placement="right" title="Listar Usuarios" style="color: #ffffff;">
                    <i class="fas fa-users fa-1.5x text-white mb-3 sidebar-icon"></i> Usuarios
                </a>
                <a href="javascript:agregarUsuarios()" id="add-user" data-bs-toggle="tooltip" data-bs-placement="right" title="Agregar Usuarios">
                    <i class="fas fa-user-plus fa-1.5x text-white mb-3 sidebar-icon"></i> Agregar
                </a>
                <a href="javascript:eliminarUsuarios()" id="delete-user" data-bs-toggle="tooltip" data-bs-placement="right" title="Eliminar Usuarios">
                    <i class="fas fa-user-times fa-1.5x text-white mb-3 sidebar-icon"></i> Eliminar
                </a>
                <a href="#" id="edit-user" data-bs-toggle="tooltip" data-bs-placement="right" title="Editar Usuarios">
                    <i class="fas fa-user-edit fa-1.5x text-white mb-3 sidebar-icon"></i> Editar
                </a>
                <a href="#" id="update-password" data-bs-toggle="tooltip" data-bs-placement="right" title="Actualizar Password">
                    <i class="fas fa-key fa-1.5x text-white mb-3 sidebar-icon"></i> Contraseña
                </a>
                <a href="javascript:listarServidores()" id="list-server" data-bs-toggle="tooltip" data-bs-placement="right" title="Listar Servidores">
                    <i class="fas fa-store fa-1.5x text-white mb-3 sidebar-icon"></i> Locales
                </a>
                <a href="javascript:agregarServidores()" id="add-server" data-bs-toggle="tooltip" data-bs-placement="right" title="Agregar Servidores">
                    <i class="fas fa-circle-plus" style="color: whitesmoke;"></i><i class="fas fa-store fa-1.5x text-white mb-3 sidebar-icon"></i> Agregar Tienda
                </a>
            </div>
            <div class="container-fluid">
                <!-- Main Content -->


                <div id="main-content">
                    <!-- Your main content will appear here -->
                </div>

            </div>


        </div>


        <!-- JavaScript for changing content -->
        <script>
            function listarUsuarios() {
                console.log("listarUsuarios called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvUsuarios?action=list', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function agregarUsuarios() {
                console.log("agregarUsuarios called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvUsuarios?action=loadForm', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function eliminarUsuarios() {
                console.log("eliminarUsuarios called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvUsuarios?action=delete-form', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function listarLocales() {
                console.log("listarLocales called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvLocales?action=list', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            function listarServidores() {
                console.log("listarServidores called");
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'SvLocales?action=listServer', true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        document.getElementById('main-content').innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }

            document.addEventListener("DOMContentLoaded", function () {
                event.preventDefault();
                const actions = {
                    "list-users": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el listado de usuarios.';
                    },
                    "add-user": function () {
                        document.getElementById('main-content').innerHTML = 'Aquí va el formulario para agregar un usuario.';
                    },
                    "delete-user": function () {
                        document.getElementById('main-content').innerHTML = 'Aca formulario eliminar';
                    },
                    "list-locales": function () {
                        document.getElementById('main-content').innerHTML = 'Acá va el listado de locales.';
                    },
                    "list-server": function () {
                        document.getElementById('main-content').innerHTML = 'Acá Lista de Servidores de Tienda';
                    },
                    "add-server": function () {
                        document.getElementById('main-content').innerHTML = 'Form para agregar Servidor de Tienda';
                    }
                };

                Object.keys(actions).forEach(function (id) {
                    document.getElementById(id).addEventListener('click', actions[id]);
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
