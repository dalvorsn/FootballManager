<%--
    Document   : template.jsp
    Created on : 10/04/2020, 16:32:38
    Author     : dalvo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

        <title>Importar dados</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="${context}/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="${context}/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <nav class="grey darken-3" role="navigation">
            <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo"></a>
                <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
                <ul id="nav-desktop" class="right hide-on-med-and-down sidenav">
                    <li><a href="#">Navbar Link</a></li>
                </ul>

                <ul id="nav-mobile" class="sidenav">
                    <li><a href="#">Navbar Link</a></li>
                </ul>
                <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            </div>
        </nav>
        <div class="section no-pad-bot" id="index-banner">
            <div class="container">
                <br><br>
                <br><br>

            </div>
        </div>

        <footer class="page-footer grey darken-3">
            <div class="footer-copyright grey darken-4">
                <div class="container">
                    Copyright <sup><i class="material-icons">copyright</i></sup> <a class="white-text text-lighten-3" href="https://github.com/dalvorsn">Dalvo</a> 2020
                </div>
            </div>
        </footer>

        <!--  Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="${context}/js/materialize.js"></script>
        <script src="${context}/js/init.js"></script>

        <script>
            (function(jQuery) {

            })(jQuery);
        </script>
    </body>
</html>
