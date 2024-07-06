<%-- 
    Document   : services
    Created on : Jul 3, 2024, 1:16:50 PM
    Author     : JFerreira
--%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
%>
<%@page import="logica.Services"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Servicios</title>
    <link rel="stylesheet" href="css/style.css" />
    <link href="css/sidebars.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link href="css/fontawesome.css" rel="stylesheet" />
    <link href="css/brands.css" rel="stylesheet" />
    <link href="css/solid.css" rel="stylesheet" />
    <link href="css/footers.css" rel="stylesheet" type="text/css"/>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/script.js"></script>
    <script src="js/toastify.js"></script>
    <script src="js/sidebars.js"></script>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<header>
    <%@include file="jsp/navbar.jsp" %>
</header>

<div class="d-flex">
    <!-- Sidebar -->
    <div class="sidebar d-flex flex-column align-items-left py-6 mt-auto mb-6" style="font-family: 'Roboto', sans-serif; font-size: 14px; width: 250px;">
        <br>
        <!-- Admin Functions -->
        <a href="javascript:listarServicios()" id="list-services" data-bs-toggle="tooltip" data-bs-placement="right" title="Listar Servicios" style="color: #ffffff;">
            <i class="fas fa-list fa-1.5x text-white mb-3 sidebar-icon"></i> Listar Servicios
        </a>
        <a href="javascript:agregarServicio()" id="add-service" data-bs-toggle="tooltip" data-bs-placement="right" title="Agregar Servicio">
            <i class="fas fa-plus fa-1.5x text-white mb-3 sidebar-icon"></i> Agregar Servicio
        </a>
        <a href="javascript:eliminarServicio()" id="delete-service" data-bs-toggle="tooltip" data-bs-placement="right" title="Eliminar Servicio">
            <i class="fas fa-trash fa-1.5x text-white mb-3 sidebar-icon"></i> Eliminar Servicio
        </a>
        <a href="javascript:editarServicio()" id="edit-service" data-bs-toggle="tooltip" data-bs-placement="right" title="Editar Servicio">
            <i class="fas fa-edit fa-1.5x text-white mb-3 sidebar-icon"></i> Editar Servicio
        </a>
    </div>

    <!-- Main Content -->
    <div class="container-fluid">
        <div id="main-content">
            <!-- Your main content will appear here -->
            <h1>Listado de Servicios</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Mes</th>
                    <th>Formato</th>
                    <th>Inc</th>
                    <th>SAP</th>
                    <th>Tienda</th>
                    <th>Detalle</th>
                    <th>Monto</th>
                    <th>Moneda</th>
                    <th>Proveedor</th>
                    <th>F. Autorizar</th>
                    <th>OC</th>
                    <th>F. Envío Prov</th>
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
                <%
                    List<Services> servicesList = (List<Services>) request.getAttribute("services");
                    if (servicesList != null) {
                        for (Services service : servicesList) {
                %>
                <tr>
                    <td><%= service.getId() %></td>
                    <td><%= service.getTipo() %></td>
                    <td><%= service.getMes() %></td>
                    <td><%= service.getFormato() %></td>
                    <td><%= service.getInc() %></td>
                    <td><%= service.getSap() %></td>
                    <td><%= service.getTienda() %></td>
                    <td><%= service.getDetalle() %></td>
                    <td><%= service.getMonto() %></td>
                    <td><%= service.getMoneda() %></td>
                    <td><%= service.getProveedor() %></td>
                    <td><%= service.getfAutorizar() %></td>
                    <td><%= service.getOc() %></td>
                    <td><%= service.getfEnvioProv() %></td>
                    <td><%= service.getHes() %></td>
                    <td><%= service.getSociedad() %></td>
                    <td><%= service.getOrdenEstadistica() %></td>
                    <td><%= service.getTextoBreve() %></td>
                    <td><%= service.getCotizacion() %></td>
                    <td><%= service.isActivo() %></td>
                    <td><%= service.getFechaCreacion() %></td>
                    <td><%= service.getFechaActualizacion() %></td>
                    <td><%= service.getFechaCierre() %></td>
                </tr>
                <%
            }
                } else {
            %>
            <tr>
                <td colspan="23">No data available</td>
            </tr>
            <%
                }
            %>
            </table>
        </div>
    </div>
</div>

<!-- JavaScript for changing content -->
<script>
    function listarServicios() {
        console.log("listarServicios called");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'SvServices?action=list', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('main-content').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    function agregarServicio() {
        console.log("agregarServicio called");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'SvServices?action=loadForm', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('main-content').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    function eliminarServicio() {
        console.log("eliminarServicio called");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'SvServices?action=delete-form', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('main-content').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    function editarServicio() {
        console.log("editarServicio called");
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'SvServices?action=edit-form', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('main-content').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    document.addEventListener("DOMContentLoaded", function () {
        const actions = {
            "list-services": function() {
                listarServicios();
            },
            "add-service": function() {
                agregarServicio();
            },
            "delete-service": function() {
                eliminarServicio();
            },
            "edit-service": function() {
                editarServicio();
            }
        };

        Object.keys(actions).forEach(function (id) {
            document.getElementById(id).addEventListener('click', actions[id]);
        });
    });
</script>
</body>
</html>

