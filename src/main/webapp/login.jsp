<%-- 
    Document   : login
    Created on : Jul 10, 2023, 1:58:46 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/style.css">
        <link href="css/Toasts.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="favicon.ico"/>
        <link href="css/fontawesome.css" rel="stylesheet">
        <link href="css/brands.css" rel="stylesheet">
        <link href="css/solid.css" rel="stylesheet">
        <script src="js/script.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/Toasts.js" type="text/javascript"></script>
        <script src="js/jquery-3.7.0.min.js" type="text/javascript"></script>
        <script src="js/toastify.js"></script>
        <link rel="stylesheet" href="@{/webjars/bootstrap/5.3.1/css/bootstrap.min.css}" />
        <script src="@{/webjars/jquery/3.6.3/jquery.min.js}"></script>
        <script src="@{/webjars/bootstrap/5.3.1/js/bootstrap.min.js}"></script>
        <title>Login</title>
    </head>
    
    <body class="container-login">
        <div class="login-container">
            <!-- Aquí va el contenido actual de login.jsp -->
            <div class="logo">
                <img src="images/logo2.png"/>
            </div>
            <h1>DockPOS</h1>
            <form action="SvLogin" method="post">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" required>
                <br><br>
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
                <br><br>
                <button class="btn btn-outline-dark" value="Login">Login</button>
            </form>
            <%
                // Comprobar si hay un mensaje de error en la sesión
                String loginError = (String) session.getAttribute("loginError");
                if (loginError != null) {
                    session.removeAttribute("loginError"); // Limpiar el atributo para futuras visitas
                %>
                <script>
                    // Crear una nueva instancia de la clase Toasts
                    var toasts = new Toasts({
                        position: 'top-right',
                        dimOld: false
                    });

                  // Mostrar el toast cuando la página se cargue
                    $(document).ready(function() {
                          toasts.push({
                          title: 'Error de inicio de sesión',
                          content: '<%= loginError %>',
                          style: 'error'
                      });
                  });
                </script>
                <%
                }
                %>

            <div id="softwareInfo">
                <i class="fa-solid fa-copyright"></i>
                <span>2024 SMU S.A.</span>
                <i id="versionIcon" class="fa-solid fa-v"> 2.3.1</i>
            
            </div>
            <div class="progress">
                <div id="progress-bar" class="progress-bar" role="progressbar" style="width: 5%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"></div>
            </div>

        </div>
         <% String toastScript = (String) request.getAttribute("toastScript");
           if (toastScript != null) {
               out.print(toastScript);
           } %>
        <script>
            
            function showToast(message) {
              // Crear el div del toast
              var toast = $('<div class="alert alert-danger" style="position:fixed; bottom:0; right:0;">')
                .text(message)
                .hide()
                .appendTo('body')
                .fadeIn();

              // Ocultar el toast después de 3 segundos
              setTimeout(function() {
                toast.fadeOut(function() {
                  toast.remove();
                });
              }, 3000);
            }

            //Comprobar si hay algun error en la URL
            var urlParams = new URLSearchParams(window.location.search);
            var errorMessage = urlParams.get('error');
            if (errorMessage) {
                alert(errorMessage);
            }
            $(document).ready(function(){
                var clickSound = new Audio('sounds/mouse-click-sound.mp3');

                $('.btn.btn-outline-dark').click(function(){
                    clickSound.play();
                });
            });
           function updateProgressBar(percentComplete) {
                $("#progress-bar").css('width', percentComplete + '%').attr('aria-valuenow', percentComplete);
            }
            $.ajax({
                url: 'dashboard.jsp',
                success: function(data) {
                    var response = JSON.response(data);
                    updateProgressBar(response.percentComplete);
                }
            });


        </script>
    </body>
</html>
