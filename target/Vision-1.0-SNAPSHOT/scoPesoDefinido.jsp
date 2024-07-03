<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.smu.vision.resources.JavaEE8Resource"%>

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
                theme: "advanced",
                mode: "exact",
                elements: "elm1",
                theme_advanced_toolbar_location: "top",
                theme_advanced_buttons1: "bold,italic,underline,strikethrough,separator,"
                        + "justifyleft,justifycenter,justifyright,justifyfull,formatselect,"
                        + "bullist,numlist,outdent,indent",
                theme_advanced_buttons2: "link,unlink,anchor,image,separator,"
                        + "undo,redo,cleanup,code,separator,sub,sup,charmap",
                theme_advanced_buttons3: "",
                height: "350px",
                width: "600px"
            });
            document.getElementById('formulario').addEventListener('submit', function (e) {
                e.preventDefault();
                var formData = new FormData(this);
                fetch(this.action, {
                    method: 'POST',
                    body: formData,
                })
                        .then(response => response.text())
                        .then(data => {
                            document.getElementById('contenedorResultados').innerHTML = data; // Muestra datos en 
                            el
                            contenedor
                                    console.log(data);
                        })
                        .catch(error => console.error('Error:', error));
            });
        </script>
    </head>

    <div class="container-sm shadow p-3 mb-5 bg-body-tertiary rounded">
        <h4 style="text-align: center; margin-bottom:30px;">Consultar Peso Aprendido</h4>
        <div class="container-xl">
            <form action="SvScoPesoDefinido" method="post" id="formulario" class="text-center" style="max-width: 600px; margin: auto;">
                <div class="form-group">
                    <label for="ip">Ingrese la IP del SCO a consultar:</label>
                    <input type="text" id="ip" name="ip" required class="form-control"><br><br>
                    <label for="action">Seleccione una acción:</label>
                    <select id="action" name="action" required class="form-control">
                        <option value="consultar_weights">Consultar cantidad de pesos en tabla weights</option>
                        <option value="consultar_articles">Consultar cantidad de artículos en tabla articles</option>
                    </select><br><br>
                    <label for="archivo">Ruta del archivo (si aplica):</label>
                    <input type="text" id="archivo" name="archivo" class="form-control"><br><br>
                    <input type="submit" value="Ejecutar" class="btn btn-outline-dark">
                </div>
            </form>
        </div>
          <div class="text-center mt-3">
        <div id="contenedorResultados">
            <div id="resultado" class="form-control" style="height: 200px; overflow: auto;"></div>
        </div>
    </div>
    </div>
  