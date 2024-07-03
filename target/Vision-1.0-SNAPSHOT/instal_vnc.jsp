<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
    String ipCaja = (String) session.getAttribute("ipCaja");

%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SCO PESO DEFINIDO</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="favicon.ico"/>
        <link rel="stylesheet" href="css/style.css">
        <link href="css/fontawesome.css" rel="stylesheet">
        <link href="css/brands.css" rel="stylesheet">
        <link href="css/solid.css" rel="stylesheet">
        <script src="js/script.js"></script>
        <script src="js/solid.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script language="javascript" type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
        <script language="javascript" type="text/javascript">
            tinyMCE.init({
                theme: "advanced",
                mode: "exact",
                elements: "elm1",
                theme_advanced_toolbar_location: "top",
                theme_advanced_buttons1: "bold,italic,underline,strikethrough,separator,"
                        + "justifyleft,justifycenter,justifyright,justifyfull,formatselect,"
                        + "bullist,numlist,outdent,indent",
                theme_advanced_buttons2: "link,unlink,anchor,image,separator,"
                        + "undo,redo,cleanup,code,separator,sub,sup,charmap",
                theme_advanced_buttons3: "",
                height: "350px",
                width: "600px"
            });
        </script>
    </head>
    <header>
        <%@include file="jsp/navbar.jsp" %>
    </header>


    <body class="body-boleta">
        <div class="container-boleta">
            <h4 style="text-align: center; margin-bottom:30px;">Instalar VNC</h4>
            <div id="logDataContainer">
                <h1>Monitor VNC</h1>
                <pre id="resultadoContent"></pre>
            </div>
        </div>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end" style="margin-top: 20px; margin-left: 10px;">
           <button onclick="goToDashboard()" class="btn btn-outline-dark">Volver</button>
           <button onclick="exitMonitor()" class="btn btn-outline-dark" href="/SvLogout">Salir</button>
        </div>
        <script>
            function verResultados() {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var resultadoContent = xhr.responseText;
                        updateLogContent(resultadoContent);
                    }
                };
                xhr.open("GET", "vnc.jsp", true);
                xhr.send();
            }

            function updateLogContent(logData) {
                var logContent = document.getElementById("resultadoContent");
                logContent.innerHTML = logData;
            }
            //Actualizar contenido cada cierto intervalo de tiempo
            function startLogUpdate() {
                setInterval(verResultados, 6000); // Actualiza cada 6 segundos
            }
            //Iniciar actualizacion de log al cargar la pagina
            window.onload = function () {
                verResultados();
                startLogUpdate();
            };
        </script>
    </body>
</html>
