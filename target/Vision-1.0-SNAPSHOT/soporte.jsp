<%-- 
    Document   : soporte
    Created on : Sep 1, 2023, 2:15:22 PM
    Author     : JFerreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Soporte TI</title>
        <link href="css/soporte.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/jquery-3.7.0.min.js" type="text/javascript"></script>
        <script src="js/script.js" type="text/javascript"></script>
    </head>


    <body>
        <div class="wrapper">

            <div class="header">
                <h1 class="header__title">Expanding Card Grid</h1>
                <h2 class="header__subtitle">with Flexbox</h2>
            </div>

            <div class="cards">

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

                <div class=" card [ is-collapsed ] ">
                    <div class="card__inner [ js-expander ]">
                        <span>Card</span>
                        <i class="fa fa-folder-o"></i>
                    </div>
                    <div class="card__expander">
                        <i class="fa fa-close [ js-collapser ]"></i>
                        Expander
                    </div>
                </div>

            </div>

        </div>
        <script>
            var $cell = $('.card');

//open and close card when clicked on card
            $cell.find('.js-expander').click(function () {

                var $thisCell = $(this).closest('.card');

                if ($thisCell.hasClass('is-collapsed')) {
                    $cell.not($thisCell).removeClass('is-expanded').addClass('is-collapsed').addClass('is-inactive');
                    $thisCell.removeClass('is-collapsed').addClass('is-expanded');

                    if ($cell.not($thisCell).hasClass('is-inactive')) {
                        //do nothing
                    } else {
                        $cell.not($thisCell).addClass('is-inactive');
                    }

                } else {
                    $thisCell.removeClass('is-expanded').addClass('is-collapsed');
                    $cell.not($thisCell).removeClass('is-inactive');
                }
            });

//close card when click on cross
            $cell.find('.js-collapser').click(function () {

                var $thisCell = $(this).closest('.card');

                $thisCell.removeClass('is-expanded').addClass('is-collapsed');
                $cell.not($thisCell).removeClass('is-inactive');

            });
        </script>
    </body>

