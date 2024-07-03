<%-- 
    Document   : SSHCommand
    Created on : Apr 22, 2024, 9:00:53 PM
    Author     : JFerreira
--%>
<%@page import="logica.Usuario"%>
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
    String ipCaja = (String) session.getAttribute("ipCaja");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conexion POS</title>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="js/script.js"></script>
        <script language="javascript" type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
        <script language="javascript" type="text/javascript"></script>
    </head>
    <body>
        <div class="text-center mt-3">
            <div id="output">
                <!-- Aquí es donde se mostrará el terminal -->
                <textarea style="width: 577px; height: 594px"></textarea>
            </div>
            <button id="executeCommand">Ejecutar Comando</button>
            <a href="ssh://root@<%= ipCaja %>">Conectar con PuTTY</a>
            <button id ="buttonVNC">Ejecutar VNC</button>
           <a href="vnc://<%= ipCaja %>:5900">Conectar VNC</a>

        </div>

        <script>
            $(document).ready(function() {
            $("#executeCommand").click(function() {
                $.ajax({
                    url: 'SvSSHCommand',
                    method: 'POST',
                    data: {
                        ip: '<%= ipCaja%>',
                        username: 'root',
                        command: 'ls -l'
                    },
                    dataType: 'json',
                    success: function (response) {
                        
                        var output = JSON.parse(response).output;
                        $('#output').html(output);   
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('Error: ' + textStatus + ' ' + errorThrown);
                    }
                });
                });
                });
        </script>
    </body>
</html>
