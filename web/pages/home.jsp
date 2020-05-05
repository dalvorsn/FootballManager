<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="row">
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Registered Players</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${playersCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-running fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Registered Teams</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${teamsCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-users fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-danger shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Current Championships</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${championshipsCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-trophy fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Total Matches</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">${matchesCount}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-user-friends  fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="card shadow mb-4">
    <div class="card-body"> 
        <h5 class="card-title">Last Matches</h5>
        <div class="row">
            <c:forEach items="${matchResults}" var="match" varStatus="status">
                <div class="col-4 mt-auto">  
                    <div class="card shadow mb-4">
                        <div class="card-body">            
                            <div class="row">
                                <div class="col-12">
                                    <div class="row mt-2 text-center">
                                        <div class="col-3">
                                            <img style="max-height: 50px; max-width: 50px;" src="${match.firstTeamLogo}">
                                        </div>

                                        <div class="col-2 text-left mt-2">
                                            <h5><b>${match.firstTeamGoals}</b></h5>
                                        </div>
                                        <div class="col-2 text-center mt-2">
                                            <h5><b>x</b></h5>
                                        </div>
                                        <div class="col-2 text-right mt-2">
                                            <h5><b>${match.secondTeamGoals}</b></h5>
                                        </div>

                                        <div class="col-2">
                                            <img style="max-height: 50px; max-width: 50px;" src="${match.secondTeamLogo}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12 text-center">
                                            <b>${match.firstTeamName}</b> vs <b>${match.secondTeamName}</b>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12 text-center ml-2">

                                            <a class="btn" href="${context}/router?action=edit-match&id=${match.id}" title="See result" role="button">
                                                <i class="far fa-futbol "></i>
                                            </a>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>