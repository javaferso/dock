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
            theme : "advanced",
            mode: "exact",
            elements : "elm1",
            theme_advanced_toolbar_location : "top",
            theme_advanced_buttons1 : "bold,italic,underline,strikethrough,separator,"
            + "justifyleft,justifycenter,justifyright,justifyfull,formatselect,"
            + "bullist,numlist,outdent,indent",
            theme_advanced_buttons2 : "link,unlink,anchor,image,separator,"
            + "undo,redo,cleanup,code,separator,sub,sup,charmap",
            theme_advanced_buttons3 : "",
            height:"350px",
            width:"600px"
           });
        </script>
    </head>
    <header>
         <%@include file="jsp/navbar.jsp" %>
    </header>
   
    
    <body class="body-maleta">
    
        <div class="container-sm shadow p-3 mb-5 bg-body-tertiary rounded">
            <h4 style="text-align: center; margin-bottom:30px;">Ingresar Peso Definido</h4>
            <div class="container-xl">
                <form id="formulario" class="text-center" style="max-width: 600px; margin: auto;">
                    <div class="form-group">
                        <label for="archivoIP" class="form-label">Archivo IP SCO:</label>
                        <div class="input-group mb-3">
                            <input type="file" class="form-control" id="archivoIP" accept=".txt" required>
                        </div>
                        <button onclick="verSCO()" type="submit" class="btn btn-outline-dark" data-toggle="tooltip" title="Debe incluir archivo">Procesar</button>
                    </div>
                </form>
            </div>
            
        </div>
        <div class="progress-wrap progress">
            <div id="barraDeProgreso" class="progress-bar progress"></div>
        </div>
        <div class="text-center mt-3">
            <div id="contenedorResultados" style="display:none;">
                <!-- Aquí es donde se mostrará el resultado de la carga de pesos -->
                <textarea id="elm1" style="width: 577px; height: 594px"></textarea>
            </div>
        </div>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end" style="margin-top: 20px;">
           <button onclick="goToDashboard()" class="btn btn-outline-dark">Volver</button>
           <button onclick="exitMonitor()" class="btn btn-outline-dark" href="/SvLogout">Salir</button>
        </div>
        
        <%@include file='scoPesoDefinido.jsp' %>

        <div id="mensajeExito" style="display:none; color: green; font-weight: bold;">
    		El proceso se finalizó con éxito.
        </div>
        <div id="logDataContainer">
           <h1>Monitor de carga de Pesos</h1>
           <pre id="resultadoContent"></pre>
        </div>
	<script>
	   function verSCO() {
             event.preventDefault();
             var archivoIP = document.getElementById("archivoIP").files[0];
             var reader = new FileReader();
             reader.onload = function(e) {
                var contenidoIP = e.target.result;
                enviarIPs(contenidoIP); // Función para enviar los datos al servidor
                };
                reader.readAsText(archivoIP);
             }

             async function enviarIPs(contenidoIP) {
                const requestOptions = {
                      method: 'POST',
                      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                      body: "contenidoIP=" + encodeURIComponent(contenidoIP)
                };

                const response = await fetch('SvProgreso', requestOptions);
                const reader = response.body.getReader();
                let chunks = '';
                while (true) {
                   const { done, value } = await reader.read();
                   if (done) {
                      break;
                   }
                                    
                  chunks += new TextDecoder().decode(value);
                   // Divide la cadena acumulada en fragmentos de 2 dígitos (asumiendo que los porcentajes son de 2 dígitos)
                  const fragmentos = chunks.match(/.{1,2}/g);
                                    
                  if (fragmentos) {
                  // Toma el último fragmento como el progreso más reciente
                     const progreso = parseInt(fragmentos.pop(), 10);
                     actualizarBarraDeProgreso(progreso);
                     // Reconstruye la cadena con los fragmentos restantes para el siguiente ciclo
                     chunks = fragmentos.join('');
                  }
              }
          }
          function actualizarBarraDeProgreso(progreso) {
             var barraDeProgreso = document.getElementById("barraDeProgreso");
             barraDeProgreso.style.width = progreso + "%";
             console.log("Progreso recibido desde servidor: ", progreso);
             barraDeProgreso.innerHTML = progreso + "%";
             if (progreso === 100) {
                document.getElementById("mensajeExito").style.display = "block";
                
             }
          }   
          function verResultados() {
             var xhr = new XMLHttpRequest();
             xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                   var resultadoContent = xhr.responseText;
                   updateLogContent(resultadoContent);   
                }
             };
             xhr.open("GET", "procesarArchivoIP.jsp", true); 
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
          window.onload = function() {
            verResultados();
            startLogUpdate();
          };
          $(document).ready(function() {
             $(window).scroll(function() {
                 if ($(this).scrollTop() > 50) {
                     $('#backToTop').fadeIn();
                 } else {
                     $('#backToTop').fadeOut();
                 }
             });

             $('#backToTop').click(function() {
                 $('body,html').animate({
                     scrollTop: 0
                 }, 500);
                 return false;
             });
          });
          var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
          var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
             return new bootstrap.Tooltip(tooltipTriggerEl)
          })
        </script>
        <button id="backToTop" class="btn btn-light btn-lg back-to-top" type="button" data-bs-toggle="tooltip" data-bs-placement="left" title="Volver arriba">
           <i class="fas fa-chevron-up"></i>
        </button>
        <br>
        <br> 
                 <footer>
                    <%@include file="jsp/footer.jsp" %>
                </footer> 
    </body>
</html>
