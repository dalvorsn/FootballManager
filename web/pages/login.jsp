<%--
    Document   : login
    Created on : 10/04/2020, 16:49:30
    Author     : dalvo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="errorMsg" value="${error}" />
<!DOCTYPE html>
<html class="white darken-2">
    <head>
        <title>FIFA - Login</title>
        <jsp:include page="/includes/header.jsp" />
        <style>
            html {
                height: 100%;
            }

            body {
                background-image: url(${context}/assets/images/login-background.jpg) ;
                background-position: center center;
                background-repeat:  no-repeat;
                background-attachment: fixed;
                background-size:  cover;
                background-color: #999;
                margin: 0;
                padding: 0;
                font-family: exo, sans-serif;
                height: 100%;
                width: 100%;
            }

            #formContainer {
                margin-top: 25vh;
            }

            #logoForm {
                height: 80px;
            }
        </style>
    </head>
    <body>
        <div class="row">
            <div id="formContainer" class="col s12 m10 l6 xl4 offset-m1 offset-l3 offset-xl4 center-align">
                <div class="card-panel grey darken-3">
                    <form action="router" method="POST">
                        <input type="hidden" name="action" value="login-check">
                        <div class="row center-align">
                            <img id="logoForm" src="${context}/assets/images/logo.png" />
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="Enter your login" id="login" type="text" name="login" class="validate white-text" required>
                                <label for="login" class="white-text">Login</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="Enter your password" id="password" name="password" type="password" class="validate white-text" required>
                                <label for="password" class="white-text">Password</label>
                            </div>
                        </div>
                        <c:if test="${error != null}">
                            <div class="card-panel red accent-1 white-text">
                                ${error}
                            </div>
                        </c:if>
                        <div>
                            <button class="btn waves-effect orange lighten-1 waves-light z-depth-5" type="submit" name="action">Login
                                <i class="material-icons right">arrow_forward</i>
                            </button>
                        </div>
                        <br>

                        <div class="row">
                            <a href="${context}/router?action=create-account" >Sign up now</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/includes/js-includes.jsp" />
        <script>
            (function(jQuery) {

            })(jQuery);
        </script>
    </body>
</html>