<%-- 
    Document   : perfil
    Created on : Aug 31, 2023, 1:22:59 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Usuario user = (Usuario) session.getAttribute("user");
    System.out.println("Session ID: " + session.getId());
%>
<!DOCTYPE html>
<html>
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
        <title>Perfil Dockpos</title>
    </head>
    <body>
        <header>
            <%@include file="jsp/navbar.jsp" %>
        </header>
        <div class="container-sm">
            <div class="row justify-content-start">
                <div class="col-2 d-flex flex-column align-items-center py-4 mt-auto mb-4">
                    <%@include file="jsp/sidebar.jsp" %>
                </div>
                <div class="col-10">
                    <h1>Perfil del Usuario</h1>

                    <!-- Mostrar imagen de perfil -->
                    <div class="perfil-imagen-wrapper">
                        <img id="perfilImagen" src="SvPerfil" alt="Imagen de perfil" width="300">
                    </div>

                    <!-- Formulario para subir nueva imagen de perfil -->
                    <form id="imagenForm" method="POST" action="SvPerfil" enctype="multipart/form-data">
                        <input type="file" name="imagenPerfil">
                        <div class="button-container">
                            <button class="btn btn-outline-dark" type="submit">Actualizar Imagen</button>
                        </div>
                    </form>

                    <!-- Formulario para cambiar la contraseña -->
                    <form id="cambiarContrasenaForm" method="POST" action="SvUsuarios?action=changePassword">
                        <label for="contrasenaActual">Contraseña actual:</label>
                        <input type="password" id="contrasenaActual" name="contrasenaActual">

                        <label for="nuevaContrasena">Nueva contraseña:</label>
                        <input type="password" id="nuevaContrasena" name="nuevaContrasena">

                        <div class="button-container">
                            <button class="btn btn-outline-dark" type="submit">Cambiar Contraseña</button>
                        </div>
                    </form>

                    <%
                        // Comprobar si hay un mensaje de error en la sesión
                        String contraseñaChange = (String) session.getAttribute("changePassword");
                        if (contraseñaChange != null) {
                            session.removeAttribute("changePassword"); // Limpiar el atributo para futuras visitas
                    %>
                    <script>
                        // Crear una nueva instancia de la clase Toasts
                        var toasts = new Toasts({
                            position: 'top-right',
                            dimOld: false
                        });

                        // Mostrar el toast cuando la página se cargue
                        $(document).ready(function () {
                            toasts.push({
                                title: 'Error de inicio de sesión',
                                content: '<%= contraseñaChange %>',
                                style: 'error'
                            });
                        });
                    </script>
                    <%
                        }
                    %>
                    <%
                        String toastScript = (String) request.getAttribute("toastScript");
                        if (toastScript != null) {
                            out.print(toastScript);
                        }
                    %>
                    <script>
                        function showToast(message) {
                            // Crear el div del toast
                            var toast = $('<div class="alert alert-danger" style="position:fixed; bottom:0; right:0;">')
                                .text(message)
                                .hide()
                                .appendTo('body')
                                .fadeIn();

                            // Ocultar el toast después de 3 segundos
                            setTimeout(function () {
                                toast.fadeOut(function () {
                                    toast.remove();
                                });
                            }, 3000);
                        }
                    </script>

                    <%@include file="jsp/footer.jsp" %>
                </div>
            </div>
        </div>
    </body>
</html>

