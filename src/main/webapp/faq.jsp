<%-- 
    Document   : faq
    Created on : May 28, 2024, 1:03:02 AM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <title>FAQ - Manuales</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/brands.min.css" rel="stylesheet" type="text/css"/>
        <link href="fontawesome/css/all.min.css" rel="stylesheet"/>
        <script src="fontawesome/js/all.min.js"></script>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet"/>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js"></script>
    </head>
    <body>
        <header>
            <%@ include file="jsp/navbar.jsp" %>
        </header>
        <div class="container-fluid mt-3">
            <div class="row">
                
                <div class="col-12 col-md-12">
                    <%@page import="logica.Usuario"%>
                    <div class="container-box">
                        <h1 class="mt-5">Manuales y Documentación</h1>
                        <img src="images/Data-Organization-2.png" class="img-fluid" alt="Manuales">
                        <p>Aquí puedes encontrar los manuales en PDF:</p>
                        <ul class="list-group pdf-list">
                            <li class="list-group-item pdf-link">
                                <a href="pdf/SMU-Procedimiento-ResetyCargadePesosenSCO.pdf" target="_blank">
                                    <i class="fas fa-file-pdf"></i> Reset y Carga de Pesos en SCO
                                </a>
                            </li>
                            <li class="list-group-item pdf-link">
                                <a href="pdf/SMU-Procedimiento-Creacion-usuario-en-Consola-Geopos.pdf" target="_blank">
                                    <i class="fas fa-file-pdf"></i> Creacion de Usuario en Consola
                                </a>
                            </li>
                            <li class="list-group-item pdf-link">
                                <a href="#" target="_blank">
                                    <i class="fas fa-file-pdf"></i> Manual 3
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>