<%-- 
    Document   : logout
    Created on : Jul 10, 2023, 6:58:15 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
        <style>
            body {
                background-image: url('images/smu_entrada.jpeg');
                background-size: cover;
                background-position: center;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .message-container {
                background-color: rgba(0, 0, 0, 0.7);
                color: #fff;
                padding: 20px;
                border-radius: 5px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="message-container">
            <h2>Finalizando Sesion</h2>
            <p>cerrando sesion...</p>
        </div>
        <script>
            setTimeout(function() {
                window.location.href = "index.jsp";
            }, 3000);
        </script>
    </body>
</html>
