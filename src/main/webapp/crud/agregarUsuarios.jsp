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
        <title>Agregar Usuario</title>
    </head>
    <body>
        <div class="col-6" style="padding: 0 0px 0 160px; width: 80%; min-width: 300px;">
            <form method="POST" action="SvUsuarios?action=add">
                <div class="mb-3">
                    <label for="idUsuario" class="form-label">idUsuario</label>
                    <input type="text" class="form-control" id="idUsuario" name="idUsuario">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre">
                </div>
                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" class="form-control" id="apellido" name="apellido">
                </div>
                <div class="mb-3">
                    <label for="sexo" class="form-label">Sexo</label>
                    <select class="form-select" name="sexo" id="sexo">
                        <option selected>Seleccione...</option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                    </select>
                </div>
                 
                
 		<div class="input-group flex-nowrap mb-3">
 		  <label    class="col-sm-2 col-form-label font-weight-bold">Rol Usuario :</label>
 		  <div class="mt-2">
                    <div class="form-check  form-check-inline">
 		      <input class="form-check-input" type="hidden" name="habilitado" id="habilitado" value="1">
 		    </div>
 		    <div class="form-check  form-check-inline">
 		      <input class="form-check-input" type="radio" name="idRole" id="1" value="1">
 		      <label class="form-check-label" for="inlineRadio1">Supervisor</label>
 		    </div>
 		    <div class="form-check form-check-inline">
 		      <input class="form-check-input" type="radio" name="idRole" id="2" value="2">
 		      <label class="form-check-label" for="inlineRadio2">Tesoreria</label>
 		    </div>
 		    <div class="form-check form-check-inline">
 		      <input class="form-check-input" type="radio" name="idRole" id="3" value="3">
 		      <label class="form-check-label" for="inlineRadio2">Soporte</label>
 		    </div>
 		
 		    <div class="form-check form-check-inline">
 		      <input class="form-check-input" type="radio" name="idRole" id="4" value="4">
 		      <label class="form-check-label" for="inlineRadio2">Seguridad</label>
 		    </div>
                    <div class="form-check form-check-inline">
                      <input class="form-check-input" type="radio" name="idRole" id="5" value="5">
                      <label class="form-check-label" for="inlineRadio2">Ecommerce</label>
                    </div>
 		  </div>
 		</div>
                <button type="submit" class="btn btn-primary" id="agregarUsuarioForm">Enviar</button>
            </form>

        </div>
    </body>
</html>
