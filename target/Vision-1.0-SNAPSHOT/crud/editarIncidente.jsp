<%-- 
    Document   : editarIncidente
    Created on : Jul 15, 2024, 10:59:29 AM
    Author     : JFerreira
--%>

<%@page import="logica.Sociedades"%>
<%@page import="logica.Proveedores"%>
<%@page import="logica.Moneda"%>
<%@page import="logica.Servidores"%>
<%@page import="logica.Tipos"%>
<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<%@page import="logica.Incidentes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());

    Incidentes incidente = (Incidentes) request.getAttribute("incidente");
    List<Tipos> tipos = (List<Tipos>) request.getAttribute("tipos");
    List<Moneda> monedas = (List<Moneda>) request.getAttribute("monedas");
    List<Proveedores> proveedores = (List<Proveedores>) request.getAttribute("proveedores");
    List<Sociedades> sociedades = (List<Sociedades>) request.getAttribute("sociedades");
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

    List<Servidores> servidores = (List<Servidores>) request.getAttribute("listaServidores");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Editar Incidente</title>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="css/fontawesome.css" rel="stylesheet" />
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <script src="js/bootstrap.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <h1>Editar Incidente</h1>
        <form method="POST" action="SvIncidente?action=update" class="col-6 p-3">
            <input type="hidden" name="id" value="<%= incidente.getId() %>">
            
            <!-- Input Tipo de Servicio -->
            <div class="mb-3">
                <label for="tipo" class="form-label">Tipo</label>
                <select class="form-control" id="tipo" name="tipo" required>
                    <option value="">Seleccione un tipo</option>
                    <% for (Tipos tipo : tipos) {%>
                    <option value="<%= tipo.getNombre()%>" <%= tipo.getNombre().equals(incidente.getTipo()) ? "selected" : "" %>><%= tipo.getNombre()%></option>
                    <% } %>
                </select>
            </div>

            <!-- Input Ingreso Mes de Servicio -->
            <div class="mb-3">
                <label for="mes" class="form-label">Mes</label>
                <input type="number" class="form-control" id="mes" name="mes" value="<%= incidente.getMes() %>" required>
            </div>

            <!-- Input Ingreso Formato de Tienda -->
            <div class="mb-3">
                <label for="formato" class="form-label">Formato</label>
                <input type="text" class="form-control" id="formato" name="formato" value="<%= incidente.getFormato() %>" required>
            </div>

            <!-- Input Ingreso Numero de Ticket -->
            <div class="mb-3">
                <label for="inc" class="form-label">Inc</label>
                <input type="text" class="form-control" id="inc" name="inc" value="<%= incidente.getInc() %>">
            </div>

            <!-- Input Ingreso Codigo de Local -->
            <div class="mb-3">
                <label for="sap" class="form-label">SAP</label>
                <input type="text" class="form-control" id="sap" name="sap" value="<%= incidente.getSap() %>" required>
            </div>

            <!-- Input Ingreso Nombre de Local -->
            <div class="mb-3">
                <label for="tienda" class="form-label">Tienda</label>
                <input type="text" class="form-control" id="tienda" name="tienda" value="<%= incidente.getTienda() %>" required>
            </div>

            <!-- Input Ingreso de Detalle de Servicio -->
            <div class="mb-3">
                <label for="detalle" class="form-label">Detalle</label>
                <textarea class="form-control" id="detalle" name="detalle" rows="3"><%= incidente.getDetalle() %></textarea>
            </div>

            <!-- Input Monto de Servicio -->
            <div class="mb-3">
                <label for="monto" class="form-label">Monto</label>
                <input type="number" step="0.01" class="form-control" id="monto" name="monto" value="<%= incidente.getMonto() %>">
            </div>

            <!-- Input Ingreso Codigo de Tipo de Moneda -->
            <div class="mb-3">
                <label for="moneda" class="form-label">Tipo de Moneda</label>
                <select class="form-control" id="moneda" name="moneda" required>
                    <option value="">Seleccione Moneda</option>
                    <% for (Moneda moneda : monedas) {%>
                    <option value="<%= moneda.getCodigo()%>" <%= moneda.getCodigo().equals(incidente.getMoneda()) ? "selected" : "" %>><%= moneda.getCodigo()%></option>
                    <% } %>
                </select>
            </div>

            <!-- Input Ingreso Proveedor -->
            <div class="mb-3">
                <label for="proveedor" class="form-label">Proveedor</label>
                <select class="form-control" id="proveedor" name="proveedor" required>
                    <option value="">Seleccione Proveedor</option>
                    <% for (Proveedores proveedor : proveedores) {%>
                    <option value="<%= proveedor.getNombre()%>" <%= proveedor.getNombre().equals(incidente.getProveedor()) ? "selected" : "" %>><%= proveedor.getNombre()%></option>
                    <% } %>
                </select>
            </div>

            <!-- Input Ingreso Fecha Autorizacion -->
            <div class="mb-3">
                <label for="f_autorizar" class="form-label">Fecha Autorizar</label>
                <input type="date" class="form-control" id="f_autorizar" name="f_autorizar" value="<%= incidente.getfAutorizar() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(incidente.getfAutorizar()) : "" %>">
            </div>

            <!-- Input Ingreso OC -->
            <div class="mb-3">
                <label for="oc" class="form-label">OC</label>
                <input type="text" class="form-control" id="oc" name="oc" value="<%= incidente.getOc() %>">
            </div>

            <!-- Input Fecha Envio a Proveedor -->
            <div class="mb-3">
                <label for="f_envio_prov" class="form-label">Fecha Envío Prov</label>
                <input type="date" class="form-control" id="f_envio_prov" name="f_envio_prov" value="<%= incidente.getfEnvioProv() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(incidente.getfEnvioProv()) : "" %>">
            </div>

            <!-- Input HES -->
            <div class="mb-3">
                <label for="hes" class="form-label">HES</label>
                <% if (user != null && user.getIdRole() != null && user.getIdRole().getIdRole() == 1) {%>
                <input type="text" class="form-control" id="hes" name="hes" value="<%= incidente.getHes() %>">
                <% } else { %>
                <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
                <% }%>
            </div>

            <!-- Input Sociedad -->
            <div class="mb-3">
                <label for="sociedad" class="form-label">Sociedad</label>
                <select class="form-control" id="sociedad" name="sociedad" required>
                    <option value="">Seleccione Sociedad</option>
                    <% for (Sociedades sociedad : sociedades) {%>
                    <option value="<%= sociedad.getNombre()%>" <%= sociedad.getNombre().equals(incidente.getSociedad()) ? "selected" : "" %>><%= sociedad.getNombre()%></option>
                    <% } %>
                </select>       
            </div>

            <!-- Input Orden Estadistica -->
            <div class="mb-3">
                <label for="orden_estadistica" class="form-label">Orden Estadística</label>
                <% if (user != null && user.getIdRole() != null && user.getIdRole().getIdRole() == 1) {%>
                <input type="number" class="form-control" id="orden_estadistica" name="orden_estadistica" value="<%= incidente.getOrdenEstadistica() %>">
                <% } else { %>
                <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
                <% }%>
            </div>

            <!-- Input Texto Breve -->
            <div class="mb-3">
                <label for="texto_breve" class="form-label">Texto Breve</label>
                <% if (user != null && user.getIdRole() != null && user.getIdRole().getIdRole() == 1) {%>
                <input type="text" class="form-control" id="texto_breve" name="texto_breve" value="<%= incidente.getTextoBreve() %>">
                <% } else { %>
                <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
                <% }%>
            </div>

            <!-- Input Cotización -->
            <div class="mb-3">
                <label for="cotizacion" class="form-label">Cotización</label>
                <input type="text" class="form-control" id="cotizacion" name="cotizacion" value="<%= incidente.getCotizacion() %>">
            </div>

            <!-- Input Estado -->
            <div class="form-check mb-3">
                <label for="activo" class="form-check-label">Estado</label>
                <select class="form-select" name="activo" id="activo">
                    <option value="True" <%= incidente.isActivo() ? "selected" : "" %>>Activo</option>
                    <option value="False" <%= !incidente.isActivo() ? "selected" : "" %>>Pendiente</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="usuario" class="form-label">Responsable TI</label>
                <% if (user != null && user.getIdRole() != null && user.getIdRole().getIdRole() == 1) { %>
                <select class="form-control" id="usuario" name="usuario" required>
                    <option value="">Seleccione Usuario</option>
                    <% for (Usuario us : usuarios) { %>
                    <% if (us.getIdRole() != null && us.getIdRole().getIdRole() == 3) {%>
                    <option value="<%= us.getIdUsuario()%>" <%= us.getIdUsuario().equals(incidente.getUsuarioId()) ? "selected" : "" %>><%= us.getNombre()%> <%= us.getApellido()%></option>
                    <% } %>
                    <% } %>
                </select>
                <% } else { %>
                <div class="alert alert-warning">Sin atribuciones para llenar este campo</div>
                <% }%>
            </div>
            <button type="submit" class="button" id="guardarCambios">Guardar Cambios</button>
            <input
                        type="button"
                        class="button"
                        value="Cancelar"
                        onclick="location.href = 'servicios.jsp'"
                        />
        </form>
              
    </body>
</html>
