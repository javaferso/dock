<%-- 
    Document   : bloqueousuarios
    Created on : Aug 11, 2023, 12:52:03 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bloqueo de Usuarios</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body class="container">
    <div class="container red">
        <h1>Bloqueo de Usuarios</h1>
        <p>
            Has excedido el número máximo de intentos de inicio de sesión.
            Por razones de seguridad, tu cuenta ha sido bloqueada temporalmente.
            Por favor, espera 30 segundos antes de volver a intentar iniciar sesión.
        </p>
        <p>
            Si sigues teniendo problemas con tu cuenta, por favor contacta a
            nuestro equipo de soporte enviando un mensaje a la mesa de ayuda
            o a través del correo electrónico de soporte.
        </p>
        <p id="countdownMessage">
            Intenta iniciar sesión nuevamente en:
            <span id="countdownTimer">30</span> segundos.
        </p>
    </div>
    
    <script>
        var countdown = 30;
        var countdownTimer = document.getElementById("countdownTimer");
        var countdownMessage = document.getElementById("countdownMessage");

        function startCountdown() {
            var timer = setInterval(function() {
                countdown--;
                countdownTimer.innerText = countdown;
                if (countdown <= 0) {
                    clearInterval(timer);
                    countdownMessage.innerText = "Puedes intentar iniciar sesión nuevamente.";
                    
                    setTimeout(function() {
                        window.location.href = "index.jsp"; // Redireccionar a la página de inicio de sesión
                    }, 3000); // Redireccionar después de 3 segundos
                }
            }, 1000);
                
        }

        startCountdown();
    </script>
</body>
</html>
