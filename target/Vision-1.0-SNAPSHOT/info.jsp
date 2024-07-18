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
                    <div class="card">
                        <div class="card-body">
                            <h1 class="card-title">Información de la Aplicación</h1>
                            <p class="card-text text-justify mt-4">
                            <p>Dockpos es un proyecto web desarrollado en Java utilizando JSP, diseñado para facilitar las 
                                labores de soporte a POS (Point of Sale) en el área de retail, específicamente para el departamento de TI Operaciones y Formatos. 
                                El proyecto ofrece funcionalidades clave como rescate y manejo de logs, transacciones a bases de datos, monitoreo de ventas en caja, y conexiones SSH y VNC.</p>
                            <h4>Tecnologías Utilizadas</h4>

    Java 18: Lenguaje de programación principal del proyecto.
    Apache Maven: Herramienta de gestión y construcción del proyecto.
    JSP (JavaServer Pages): Tecnología utilizada para la creación de las páginas web dinámicas.
    PostgreSQL 15.6: Base de datos utilizada para la persistencia de datos.
    Arquitectura en Capas:
        Página Web: Interfaz de usuario construida con JSP.
        Servlet: Controlador que maneja las solicitudes del usuario y las respuestas.
        Controladora: Capa lógica del negocio que procesa las solicitudes.
        JPA (Java Persistence API): Capa de acceso a datos que interactúa con la base de datos.
        ControladoraPersistencia: Maneja las transacciones y operaciones de persistencia.
        EntityClass: Representaciones de las tablas de la base de datos como clases Java.
    Frameworks y Bibliotecas de JavaScript:
        jQuery UI 1.12.1
        jQuery 3.7.0
        Bootstrap 5.3.1

Funcionalidades Principales

    Rescate de Logs: Búsqueda, lectura y descarga de logs de manera eficiente.
    Transacciones a Bases de Datos: Manejo de transacciones para la gestión de datos.
    Monitoreo de Ventas en Caja: Control y monitoreo en tiempo real de las ventas realizadas.
    Conexiones SSH y VNC: Facilitación de conexiones remotas para la gestión y soporte de sistemas POS.

Estructura del Proyecto

    Página Web (JSP): Interfaz interactiva para el usuario final.
    Servlets: Controladores que gestionan las solicitudes HTTP.
    Controladora: Implementa la lógica del negocio y coordina las operaciones.
    ControladoraPersistencia: Gestión de la persistencia y transacciones con la base de datos.
    EntityClass: Clases Java que representan las entidades de la base de datos.

Objetivo del Proyecto

El objetivo principal del proyecto DockPOS es mejorar la eficiencia y efectividad del soporte técnico a los sistemas POS en el área de retail, proporcionando herramientas avanzadas para la gestión de logs, monitoreo de ventas, y manejo de conexiones remotas.
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



