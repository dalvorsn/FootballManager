<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" class="bg-gray-100">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>FIFA
            <c:if test="{params.page != null}">
                - ${params.page}"
            </c:if>
        </title>

        <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />

        <link href="assets/css/sb-admin-2.css" rel="stylesheet"/>

    </head>

    <body id="page-top">

        <div id="wrapper">
            <c:if test="${user != null}">
                <ul class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion" id="accordionSidebar">

                    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                        <div class="sidebar-brand-icon rotate-n-15">
                            <i class="far fa-futbol"></i>
                        </div>
                        <div class="sidebar-brand-text mx-3">
                            FIFA<sup>2020</sup>
                        </div>
                    </a>

                    <hr class="sidebar-divider my-0">

                    <li class="nav-item">
                        <a class="nav-link" href="${context}/router?action=home">
                            <i class="fas fa-fw fa-tachometer-alt"></i>
                            <span>Home</span>
                        </a>
                    </li>

                    <hr class="sidebar-divider">

                    <div class="sidebar-heading">
                        Register
                    </div>

                    <li class="nav-item">
                        <a class="nav-link" href="${context}/router?action=team">
                            <i class="fas fa-fw fa-users"></i>
                            <span>Teams</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${context}/router?action=championship">
                            <i class="fas fa-fw fa-trophy"></i>
                            <span>Championships</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${context}/router?action=player">
                            <i class="fas fa-fw fa-running"></i>
                            <span>Players</span>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${context}/router?action=rank">
                            <i class="fas fa-fw fa-chart-area"></i>
                            <span>Ranks</span>
                        </a>
                    </li>

                    <hr class="sidebar-divider d-none d-md-block">

                    <div class="text-center d-none d-md-inline">
                        <button class="rounded-circle border-0" id="sidebarToggle"></button>
                    </div>

                </ul>
            </c:if>
            <div id="content-wrapper" class="d-flex flex-column">

                <div id="content">
                    <c:if test="${user != null}">
                        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>

                            <ul class="navbar-nav ml-auto">
                                <div class="topbar-divider d-none d-sm-block"></div>

                                <li class="nav-item dropdown no-arrow">
                                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
                                        <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="#">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Profile
                                        </a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                </li>

                            </ul>
                        </nav>
                    </c:if>

                    <div class="container-fluid">
                        <c:if test="${param.page != null}">
                            <jsp:include page="pages/${param.page}.jsp" />
                        </c:if>
                    </div>
                </div>
                <c:if test="${user != null}">
                    <footer class="sticky-footer bg-white">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright &copy; FIFA 2020</span>
                            </div>
                        </div>
                    </footer>
                </c:if>
            </div>
        </div>
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <c:if test="${user != null}">
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">�</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="${context}/router?action=logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Bootstrap core JavaScript-->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="assets/js/sb-admin-2.min.js"></script>

    </body>

</html>
