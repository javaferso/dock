<%@page import="com.smu.vision.Breadcrumb"%>
<%@page import="logica.Usuario"%>
<link href="css/navbars.css" rel="stylesheet" type="text/css"/>

<!-- Navbar -->
<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="dashboard.jsp">
            <img class="logo-img" src="images/logo_smu_white.png" alt="alt"/>
        </a>
        <!-- Brand -->
        <a class="navbar-brand" href="dashboard.jsp">
            <svg height="40" viewBox="0 0 200 50" xmlns="http://www.w3.org/2000/svg">
                <text x="0" y="35" font-family="Roboto" font-size="25" font-weight="600" fill="white" letter-spacing="1">Dock</text>
                <text x="60" y="35" font-family="Roboto" font-size="25" font-weight="900" fill="orange" letter-spacing="1">POS</text>
            </svg>
        </a>

        <!-- Toggler button -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar content -->
        <div class="collapse navbar-collapse" id="navbarContent">
            <!-- Menu items -->
            <ul class="navbar-list">
                
                <li class="navbar-item">
                    <a class="nav-link active" href="controlpos.jsp" title="Control de Punto de Venta"><i class="fa-solid fa-laptop-code"></i> ControlPos</a>
                </li>
                <li class="navbar-item">
                    <a class="nav-link active" href="servicios.jsp" title="Control de Servicios"><i class="fa-solid fa-list-check"></i> Gestion de Servicios</a>
                </li>
               
                <li class="navbar-item">
                    <a href="sco.jsp"><i class="fas fa-weight-hanging"></i> Carga de Peso SCO</a>
                </li>
                <li class="navbar-item">
                    <a href="http://10.36.94.164/monitor/" title="Monitor de Operaciones TI" target="_blank"><i class="fa-solid fa-chart-pie"></i> Monitor - TI</a>
                </li>
                <li class="navbar-item">
                    <a href="paneles.jsp"><i class="fa-solid fa-wifi"></i> Cajas en Linea</a>
                </li>
                <li class="navbar-item">
                    <a href="faq.jsp" title="Documentacion y Manuales"><i class="fa-solid fa-book"></i> Docs</a>
                </li>
            </ul>

            <!-- Profile dropdown menu -->
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="perfilDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="perfilImagen" src="SvPerfil" alt="Imagen de perfil" width="40">
                            <!-- Display user's full name -->
                            <% if (user != null) { %>
                            <%= user.getNombre()%> <%= user.getApellido()%>
                            <% } else { %>
                            <script type="text/javascript">
                                window.location.href = "login.jsp";
                            </script>
                            <% } %>
                    </a>
                    <ul class="dropdown-menu-end" aria-labelledby="perfilDropdown">
                        <!-- Add dropdown menu items -->
                        <li><a class="dropdown-item" href="perfil.jsp">
                                <i class="fas fa-user fa-1.5x mb-3"></i> Perfil</a>
                        </li>
                        <li><a class="dropdown-item" href="info.jsp" data-bs-toggle="tooltip" data-bs-placement="right" title="Acerca de...">
                                <i class="fas fa-circle-info fa-1.5x mb-3"></i> Info</a>
                        </li>
                        <li><a class="dropdown-item" href="mailto:e_jferreira@smu.cl" data-bs-toggle="tooltip" data-bs-placement="right" title="Tienes dudas, escríbenos">
                                <i class="fas fa-envelope fa-1.5x mb-3"></i> Email</a>
                        </li>
                        <li><a class="dropdown-item" href="https://api.whatsapp.com/send?phone=56949469213&text=Hola%20tengo%20una%20consulta%20con%20respecto%20a%20la%20aplicacion" target="_blank" data-bs-toggle="tooltip" data-bs-placement="right" title="Háblanos, estamos para ayudarte">
                                <i class="fa-brands fa-whatsapp fa-1.5x mb-3"></i> Whatsapp</a>
                        </li>
                        <li><a class="dropdown-item" onclick="exitMonitor()" href="index.jsp">
                                <i class="fas fa-sign-out-alt fa-1.5x mb-3"></i> Salir</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page import="com.smu.vision.Breadcrumb"%>
<%@page import="logica.Usuario"%>

<%
    String currentUrl = request.getRequestURI();
    List<Breadcrumb> breadcrumbs = Breadcrumb.generateBreadcrumbs(currentUrl);
%>

<ol class="breadcrumb">
    <li><a href="dashboard.jsp">Home</a></li>
    <% for (int i = 0; i < breadcrumbs.size(); i++) {
            Breadcrumb breadcrumb = breadcrumbs.get(i); %>
        <% if (i == breadcrumbs.size() - 1) { %>
            <li class="active"><%= breadcrumb.getTitle() %></li>
        <% } else { %>
            <li><a href="<%= breadcrumb.getUrl() %>"><%= breadcrumb.getTitle() %></a></li>
        <% } %>
    <% } %>
</ol>


<script>
    $(document).ready(function () {
        $(document).on('click', '.navbar-collapse', function (e) {
            if ($(e.target).is('a:not(".dropdown-toggle")') && $(this).hasClass('show')) {
                $(this).collapse('hide');
            }
        });

        $("#perfilDropdown").on("click", function (e) {
            e.preventDefault();
            $(this).next('.dropdown-menu').toggleClass("show");
            e.stopPropagation(); // Evitar que el evento se propague al documento
        });

        // Cerrar el menú al hacer clic fuera
        $(document).on('click', function (e) {
            if (!$(e.target).closest('.dropdown-menu').length && !$(e.target).is('#perfilDropdown')) {
                $('.dropdown-menu').removeClass("show");
            }
        });

        $(document).keyup(function (e) {
            if (e.keyCode === 27) { // ESC key
                $('.dropdown-menu').removeClass("show");
            }
        });
    });
</script>
