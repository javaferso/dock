<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="shortcut icon" href="favicon.ico"/>
        <script src="js/jquery-3.7.0.min.js"></script>
        <script src="resources/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <link href="resources/jquery-ui-1.12.1/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" />
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="fontawesome/css/fontawesome.css" rel="stylesheet" type="text/css"/>
        <link href="css/brands.css" rel="stylesheet" />
        <link href="css/solid.css" rel="stylesheet" />
        <script src="poppers/popper.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/solid.js"></script>
        <script src="js/toastify.js"></script>
        <script src="js/stickyfill.min.js" type="text/javascript"></script>
        <script src="js/script.js"></script>
        
    </head>
    <body>
        <h1>Balanzas</h1>
        <button id="iniciarBtn">Iniciar</button>
        <script>
            $(document).ready(function () {
                $("#iniciarBtn").click(function () {
                    $.ajax({
                        url: 'SvBalanzas',
                        method: 'GET',
                        success: function (data) {
                            console.log(data);
                        },
                        error: function (xhr, status, error) {
                            console.error("Error: " + status + " " + error);
                        }
                    });
                });
            });
        </script>
    </body>
</html>

