
<%@page import="com.smu.vision.Breadcrumb"%>
<%@page import="logica.Usuario"%>
<link href="css/navbars.css" rel="stylesheet" type="text/css"/>
<a href="../Roboto/Roboto-Bold.ttf"></a>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container-fluid">
        <img class="logo-img" src="images/checkout.png" alt=""/>
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
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <%
                    if (user != null && user.getIdRole() != 5) {
                %>
                <li class="nav-item">
                    <a class="nav-link active" href="controlpos.jsp" title="Control de Punto de Venta">ControlPos  <i class="fa-solid fa-laptop-code"></i></a>
                </li>
                <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link active" href="http://10.36.94.164/monitor/dashboardFlejes" title="Nuevo Monitor de Operaciones" target="_blank">Monitor - TI  <i class="fa-solid fa-chart-simple"></i></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="paneles.jsp">Cajas en Linea  <i class="fa-solid fa-wifi"></i></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="faq.jsp" title="Documentacion y Manuales">Docs  <i class="fa-solid fa-book"></i></a>
                </li>
            </ul>

            <!-- Profile dropdown menu -->
            <ul class="navbar-nav">
                <li class="nav-right visible-xs">
                    <a class="nav-link dropdown-toggle" href="#" id="perfilDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="perfilImagen" src="SvPerfil" alt="Imagen de perfil" width="40"></img>
                        <!-- Display user's full name -->
                        <% if (user != null) {%>
                        <%= user.getNombre()%> <%= user.getApellido()%>
                        <% } else { %>
                        <script type="text/javascript">
                            window.location.href = "login.jsp";
                        </script>
                        <% }%>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="perfilDropdown">
                        <!-- Add dropdown menu items -->
                        <li><a class="dropdown-item" href="perfil.jsp"><i class="fas fa-user fa-1.5x text-white mb-3 sidebar-icon"></i>Perfil</a></li>
                        <li><a class="dropdown-item" href="info.jsp" data-bs-toggle="tooltip" data-bs-placement="right" title="Acerca de..."><i class="fas fa-circle-info fa-1.5x text-white mb-3 sidebar-icon"></i>Info</a></li>
                        <li><a class="dropdown-item" href=href="mailto:e_jferreira@smu.cl" data-bs-toggle="tooltip" data-bs-placement="right" title="Tienes dudas, escri­benos"><i class="fas fa-envelope fa-1.5x text-white mb-3 sidebar-icon"></i>Email</a></li>
                        <li><a class="dropdown-item" href="https://api.whatsapp.com/send?phone=56949469213&text=Hola%20tengo%20una%20consulta%20con%20respecto%20a%20la%20aplicacion" target="_blank" data-bs-toggle="tooltip" data-bs-placement="right" title="HÃ¡blanos, estamos para ayudarte"><i class="fa-brands fa-whatsapp fa-1.5x text-white mb-3 sidebar-icon"></i>Whatsapp</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a onclick="exitMonitor()" class="dropdown-item" href="index.jsp"><i class="fas fa-sign-out-alt fa-1.5x text-white mb-3 sidebar-icon"></i>Salir</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
    
</nav>
                    
                   
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
    String currentUrl = request.getRequestURI();
    List<Breadcrumb> breadcrumbs = Breadcrumb.generateBreadcrumbs(currentUrl);
%>


    <ol class="arrows">
        <li><a href="dashboard.jsp">Home</a></li>
        <% for (int i = 0; i < breadcrumbs.size(); i++) { 
            Breadcrumb breadcrumb = breadcrumbs.get(i); %>
            <% if (i == breadcrumbs.size() - 1) { %>
                <li><%= breadcrumb.getTitle() %></li>
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


