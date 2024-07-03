<%-- 
    Document   : agregarUsuarios
    Created on : Aug 30, 2023, 12:14:39 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/Toasts.css" rel="stylesheet" type="text/css"/>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/fontawesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/footers.css" rel="stylesheet" type="text/css"/>
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/sidebars.css" rel="stylesheet" type="text/css"/>
        <script src="../js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="../js/fontawesome.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../js/jquery-3.7.0.min.js" type="text/javascript"></script>
        <script src="../js/script.js" type="text/javascript"></script>
        <script src="../js/sidebars.js" type="text/javascript"></script>
        <title>Eliminar Usuario</title>
    </head>
    <div class="col-6" style="padding: 0 0px 0 160px; width: 80%; min-width: 300px;">
         <body>
            <form method="POST" action="SvUsuarios?action=delete-user">
                    <div class="mb-3">
                        <label for="idUsuario" class="form-label">idUsuario a eliminar:</label>
                        <input type="text" class="form-control" id="idUsuario" name="idUsuario" title="Ingrese Id de Usuario">
                        <div class="button-container">
                            <button class="btn btn-outline-dark" type="submit">Eliminar Usuario</button>
                         </div>
                    </div>
            </form>
        </body>
    </div>
   
</html>
