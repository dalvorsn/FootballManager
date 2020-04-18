<%--
    Document   : teste
    Created on : 09/04/2020, 13:16:33
    Author     : dalvo
--%>
<%--
    Document   : index
    Created on : 07/04/2020, 21:42:17
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
            <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
                <ul class="right hide-on-med-and-down">
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
                <ul id="paginator" class="pagination center-align">
                    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                    <li class="active"><a href="#!">1</a></li>
                    <li class="waves-effect"><a href="#!">2</a></li>
                    <li class="waves-effect"><a href="#!">3</a></li>
                    <li class="waves-effect"><a href="#!">4</a></li>
                    <li class="waves-effect"><a href="#!">5</a></li>
                    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                </ul>
                <ul id="players" class="collection">

                </ul>
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
                jQuery(function() {
                    function drawPagination(currentPage, maxPages) {
                        let paginator = jQuery('#paginator');
                        paginator.children().remove();

                        paginator.append(jQuery('<li class="disabled"><a href="#" onclick="window.getPlayersByPage(' + (currentPage - 1) + ')"><i class="material-icons">chevron_left</i></a></li>'));
                        let startAt = Math.max(1, currentPage - 2);
                        for (var i = startAt; i < startAt + 5; i++) {
                            if (i === currentPage) {
                                paginator.append(jQuery('<li class="active"><a href="#" >' + i + '</a></li>'));
                            } else {
                                paginator.append(jQuery('<li class="waves-effect"><a href="#" onclick="window.getPlayersByPage(' + i + ')">' + i + '</a></li>'));
                            }
                        }

                        paginator.append(jQuery('<li class="disabled"><a href="#" onclick="window.getPlayersByPage(' + (currentPage + 1) + ')"><i class="material-icons">chevron_right</i></a></li>'));
                    }

                    function getPlayersByPage(page) {
                        jQuery.post('Proxy', {page: page}, res => {
                            let $list = jQuery('#players');
                            $list.children().remove();
                            let playersMap = new Map();

                            drawPagination(page, res.count);

                            res.items.forEach((obj, index) => {
                                var name = obj.firstName + ` ` + obj.lastName;
                                if (playersMap.get(name))
                                    return;

                                playersMap.set(name, true);

                                $list.append(`
                                    <li class="collection-item avatar">
                                        <img src="` + obj.headshot.imgUrl + `" alt="" class="circle">
                                        <span class="title">` + name + `</span>
                                        <p>` + obj.positionFull + `<br><br>
                                           <span class="valign-wrapper">` + (obj.specialities ? obj.specialities.join(', ') : '') + `</span>
                                            <br>
                                        </p>
                                        <a href="#!" class="secondary-content"><img width="45" src=` + obj.club.imageUrls.light.small + ` alt=""></a>
                                    </li>
                                `);
                            });
                        });
                    }

                    window.getPlayersByPage = getPlayersByPage;

                    getPlayersByPage(1);
                }); // end of document ready
            })(jQuery);
        </script>
    </body>
</html>
