<%--
    Document   : login
    Created on : 10/04/2020, 16:49:30
    Author     : luciano
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="container">
    <div class="row">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5 my-5">
                                <form action="${context}/router" class="user" method="POST">
                                    <input type="hidden" name="action" value="login-check">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="login" placeholder="Enter your login">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" name="password" placeholder="Password">
                                    </div>
                                    <c:if test="${error != null}">
                                        <div class="alert alert-danger show text-center" role="alert">
                                            ${error}
                                        </div>
                                    </c:if>

                                    <button class="btn btn-primary btn-user btn-block bg-success">
                                        Login
                                    </button>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="${context}/router?action=create-account">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>