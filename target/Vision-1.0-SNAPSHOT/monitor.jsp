<%--
    Document   : monitor
    Created on : Jun 5, 2023, 8:48:11 PM
    Author     : JFerreira
--%>
<%@page import="logica.Usuario"%>
<%@page import="logica.Locales"%>
<%@page isELIgnored="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    String caja = (String) session.getAttribute("caja");
    String local = (String) session.getAttribute("local");
    String tienda = (String) session.getAttribute("nombreTienda");
    String ip = (String) session.getAttribute("ipCaja");
    System.out.println("Session ID en monitor.jsp: " + session.getId());
    System.out.println("ip en monitor.jsp: " + ip);
    System.out.println("caja en monitor.jsp: " + caja);
    System.out.println("Tienda seleccionada en monitor: " + tienda);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Monitor de Caja</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="favicon.ico"/>
        <link rel="stylesheet" href="css/style.css">
        <link href="css/fontawesome.css" rel="stylesheet">
        <link href="css/brands.css" rel="stylesheet">
        <link href="css/solid.css" rel="stylesheet">
        <script src="js/script.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/jquery-3.7.0.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
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
                height: "594px",
                width: "577px"
            });
        </script>
    </head>
    <header>
        <%@ include file="jsp/navbar.jsp" %>
    </header>
    <body>
        <div class="container-fluid mt-3 mb-5 p-2">
            <div id="notification-container"></div>

            <div id="content">

                <h1 class="justify-content-center">Control Escaneo Pos:</h1>
            </div>

            <div id="monitorContainer">
                <h3><%= caja%> de Tienda <%= tienda%></h3>
                <div id="boletaContent"></div> <!-- Contenedor para mostrar el contenido de la boleta -->
                <pre id="logContent"></pre>
            </div>    
            <form id="ipForm" method="post" action="logData.jsp">
                <input type="hidden" id="local" name="local" value="<%= local%>">
                <input type="hidden" id="caja" name="caja" value="<%= caja%>">
                <input type="hidden" id="ipCaja" name="ipCaja" value="<%= ip%>">
            </form>

            <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                <button class="btn btn-outline-dark me-md-2 me-3" onclick="goToDashboard()">Volver</button>
                <button class="btn btn-outline-dark me-md-2 me-3" onclick="exitMonitor()" href="/SvLogout">Salir</button>
                <button class="btn btn-outline-dark me-md-2 me-3" onclick="verBoleta()">Ver Boleta</button> <!-- Nuevo botón -->
            </div>




            <script>
                function updateLogContent(logData) {
                    var logContent = document.getElementById("logContent");
                    logContent.innerHTML = logData;
                }

                // Función para hacer una petición AJAX para obtener los datos del log
                function getLogData() {
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var logData = xhr.responseText;
                            console.log("Log data:", logData);
                            updateLogContent(logData);
                        }
                    };

                    var selectedIP = '<%= ip%>'; // Obtener la IP
                    console.log("IP seleccionada:", selectedIP);
                    xhr.open("GET", "logData.jsp?ip=" + selectedIP, true);
                    xhr.send();
                }
                // Función para actualizar el log cada cierto intervalo de tiempo
                function startLogUpdate() {
                    setInterval(getLogData, 6000); // Actualiza cada 3 segundos
                }



                // Función para obtener y mostrar el contenido de la boleta
                function verBoleta() {
                    var popupWindow = window.open("", "popupWindow", "width=600,height=600");
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var boletaContent = xhr.responseText;
                            var editor = tinyMCE.get('elm1'); //obtén la instancia del editor ya creado
                            var meta = '<meta charset="UTF-8">';
                            var tempElement = document.createElement('div');
                            tempElement.innerHTML = boletaContent;
                            var textContent = tempElement.innerText; //texto sin etiquetas HTML
                            var content = '<html><head>' + meta + '</head><body><textarea id="elm1" style="width: 577px; height: 594px">' + textContent + '</textarea></body></html>';
                            popupWindow.document.write(content);
                        }

                    };
                    xhr.open("GET", "verBoleta.jsp?ip=<%= ip%>", true); // Pasar la IP seleccionada como parámetro
                    xhr.send();
                }
                // Iniciar la actualización del log al cargar la página
                window.onload = function () {
                    getLogData();
                    startLogUpdate();
                };
            </script>
        </div>
    </body>
</html>


