<%-- 
    Document   : buscarBoleta
    Created on : Aug 2, 2023, 3:52:27 PM
    Author     : JFerreira
--%>

<%@page import="logica.Locales"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    System.out.println("Session ID buscarBoleta: " + session.getId());
    String caja = (String) session.getAttribute("caja");
    String local = (String) session.getAttribute("local");
    String ip = (String) session.getAttribute("ipCaja");
    String formato = (String) session.getAttribute("formato");
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Busqueda de Documentos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="favicon.ico"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="css/fontawesome.css" rel="stylesheet">
        <link href="css/brands.css" rel="stylesheet">
        <link href="css/solid.css" rel="stylesheet">
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <script src="js/solid.js"></script>
        <script language="javascript" type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
        <script language="javascript" type="text/javascript">
            tinyMCE.init({
            theme : "advanced",
            mode: "exact",
            elements : "elm1",
            theme_advanced_toolbar_location : "top",
            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,separator,"
            + "justifyleft,justifycenter,justifyright,justifyfull,formatselect,"
            + "bullist,numlist,outdent,indent",
            theme_advanced_buttons2 : "link,unlink,anchor,image,separator,"
            +"undo,redo,cleanup,code,separator,sub,sup,charmap",
            theme_advanced_buttons3 : "",
            height:"594px",
            width:"577px"
            });
        </script>
    </head>
    <body class="body-boleta">
        <div class="container-boleta">
            <h2 class="text-center">Buscar Boleta en <%= caja %> de Local <%= local %></h2>
            <form id="formulario" style="max-width: 600px; margin: auto;">
                <div class="form-group">
                    <label for="numeroBoleta">Número de Boleta:</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="numeroBoleta" name="numeroBoleta" required>
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <button type="button" class="btn btn-outline-dark" data-toggle="tooltip" title="Debe incluir numero de boleta" onclick="verTicket()">Buscar</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <button onclick="goToDashboard()" class="btn btn-outline-dark">Volver</button>
                <button onclick="exitMonitor()" class="btn btn-outline-dark" href="/SvLogout">Salir</button>
            </div>
        </div>
        <div class="text-center mt-3">
            <div id="boletaContent">
                <!-- Aquí es donde se mostrará el resultado de la búsqueda de la boleta -->
            </div>
        </div>
            <div>
                <label for="caja">Caja:</label>
                       <select id="caja">
                           <option value="">Seleccionar caja</option>
                       </select>
            </div>

        <script>
            function verTicket() {
                var numeroBoleta = document.getElementById("numeroBoleta").value;
                var popupWindow = window.open("", "popupWindow", "width=600,height=600");
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var boletaContent = xhr.responseText;
                        var editor = tinyMCE.get('elm1'); //obtén la instancia del editor ya creado
                        var meta = '<meta charset="UTF-8">';
                        var tempElement = document.createElement('div');
                        tempElement.innerHTML = boletaContent;
                        var textContent = tempElement.innerText; //texto sin etiquetas HTML
                        var content = '<html><head>' + meta + '</head><body><textarea id="elm1" style="width: 577px; height: 594px">' + textContent + '</textarea><button onclick="downloadTxt()">Descargar</button><script>function downloadTxt() {var textToSave = document.getElementById(\'elm1\').value;var hiddenElement = document.createElement(\'a\');hiddenElement.href = \'data:attachment/text,\' + encodeURI(textToSave);hiddenElement.target = \'_blank\';hiddenElement.download = \'boleta_' + numeroBoleta + '.txt\';hiddenElement.click();}<\/script></body></html>';
                        popupWindow.document.write(content);
                    }
                };

                xhr.open("GET", "verificarTicket.jsp?numeroBoleta=" + numeroBoleta, true); // Pasar la IP seleccionada como parámetro
                xhr.send();
            }

            $(document).ready(function(){
                var clickSound = new Audio('sounds/mouse-click-sound.mp3');

                $('.btn.btn-outline-dark').click(function(){
                    clickSound.play();
                });
            });
        </script>


    </body>
</html>
