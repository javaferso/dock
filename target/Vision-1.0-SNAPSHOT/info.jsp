<%-- 
    Document   : info
    Created on : Aug 11, 2023, 1:40:02 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.Usuario"%>
<% 
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link href="css/fontawesome.css" rel="stylesheet" />
    <link href="css/brands.css" rel="stylesheet" />
    <link href="css/solid.css" rel="stylesheet" />
    <script src="js/bootstrap.min.js"></script>
    <script src="js/script.js"></script>
    <script src="js/solid.js"></script>
    <script src="js/jquery-3.7.0.min.js"></script>
    <script src="js/toastify.js"></script>
    <title>Información de la Aplicación</title>
</head>
<header>
    <%@ include file="jsp/navbar.jsp" %>
</header>

<div class="container-fluid">
    <div class="row justify-content-start">
        <div class="col-2">
            <%@ include file="jsp/sidebar.jsp" %>
        </div>
        <div class="col-md-8">
            <body>
                <div class="content">
                    <div class="card bg-danger text-white">
                        <div class="card-body">
                            <h1 class="card-title">Información de la Aplicación</h1>
                            <p class="card-text text-left mt-4">
                                Bienvenido a nuestra aplicación de gestión. Nuestra herramienta te permite monitorear y administrar
                                diversos aspectos de los puntos de venta. Puedes acceder a información detallada sobre las
                                ventas al momento en pos y selfCheckout. Además, puedes realizar labores de soporte para instalacion
                                de actualizaciones, scripts, imagenes y datos en la base de datos de cada Pos. Todo para 
                                garantizar un funcionamiento fluido del sistema de cajas. Navega a través de nuestras diferentes secciones y aprovecha
                                las funciones intuitivas y poderosas para mejorar la eficiencia de nuestro negocio.
                            </p>
                            <a href="dashboard.jsp" class="btn btn-light">Volver al Dashboard</a>
                        </div>
                    </div>
                </div>
                         <footer>
                            <%@include file="jsp/footer.jsp" %>
                        </footer> 
            </body>
        </div>
    </div>
</div>

</html>



