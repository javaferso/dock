<%-- 
    Document   : crear_usuario_form
    Created on : Jul 21, 2023, 3:32:53 PM
    Author     : e_jferreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link href="/css/fontawesome.css" rel="stylesheet">
        <link href="/css/brands.css" rel="stylesheet"  >
        <link href="/css/solid.css" rel="stylesheet">
        <link href="/less/*.scss" rel="stylesheet">
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/script.js"></script>
        <script src="/js/solid.js"></script>
        <!-- Agrega aquí tus estilos CSS personalizados para el formulario -->
        <title>Crear Usuario Vision Boleta</title>
    </head>
    <body>
        
        <h1>Crear Usuario</h1>
        <form method="post" action="UsuarioOrganizacion">
            <label for="codigo">ID:</label>
            <input type="number" name="codigo" id="codigo">
            <br><br>
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" id="nombre" required>
            <br><br>
            <label for="apellido">Apellido:</label>
            <input type="text" name="apellido" id="apellido" required>
            <br><br>
            <label for="usuario">Usuario:</label>
            <input type="text" name="usuario" id="usuario" required>
            <br><br>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" required>
            <br><br>
            <input type="submit" value="Guardar">

        </form>
    </body>
</html>
