<%-- 
    Document   : graficos
    Created on : May 7, 2024, 7:44:31 PM
    Author     : JFerreira
--%>

<%@page import="logica.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <head>
        <meta charset="UTF-8" />
        <title>Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link href="css/fontawesome.css" rel="stylesheet" />
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <script src="poppers/popper.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/stickyfill.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
    </head>
    <title>Dashboard de Gráficos</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<header>
        <%@ include file="navbar.jsp" %>
</header>
<body>
    <div class="container-fluid">
        <h2>Dashboard de Gráficos</h2>
        <div class="row">
            <div class="col-md-6">
                <h3>Servidores</h3>
                <img src="SvGraficos?tipo=tortaIp" alt="Grafico de Torta Estado IP">
                <img src="SvGraficos?tipo=relojIp" alt="Grafico de Reloj Porcentaje IPs Online">
            </div>
            <div class="col-md-6">
                <h3>Enlaces</h3>
                <img src="SvGraficos?tipo=tortaEnlace" alt="Grafico de Torta Estado Enlace">
                <img src="SvGraficos?tipo=relojEnlace" alt="Grafico de Reloj Porcentaje Enlaces Online">
            </div>
        </div>
    </div>
</body>
</html>

