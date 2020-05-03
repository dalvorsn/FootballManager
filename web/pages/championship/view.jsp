<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="action" value="create-championship-save" />
<c:set var="buttonText" value="Create" />
<c:if test="${championship != null}">
    <c:set var="action" value="edit-championship-save" />
    <c:set var="buttonText" value="Edit" />
    <c:if test="${championship.startDate != null}">
        <c:set var="readonly" value="readonly" />
        <c:set var="startDisabled" value="disabled" />
    </c:if>
    <c:if test="${championship.subscribes.size() < championship.maxSubscribers}">
        <c:set var="startDisabled" value="disabled" />    
    </c:if>
</c:if>
<c:if test="${championship == null}">
    <c:set var="startDisabled" value="disabled" />
</c:if>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-1 font-weight-bold text-dark fa-lg">Championship</h6>
    </div>

    <div class="card-body">
        <form action="${context}/router" method="POST">
            <input type="hidden" name="action" value="${action}"/>
            <input type="hidden" name="id" value="${championship.id}">
            <div class="row">
                <div class="col-9">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" value="${championship.name}" name="name" placeholder="Type the name of championship" required />
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="max">Max Subscribers</label>
                        <input type="number" class="form-control text-right" id="max" name="max_subscribers" placeholder="Define max subscribes" required value="${championship.maxSubscribers}" ${readonly}/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-2">
                    <div class="form-group">
                        <label for="max">Start Date</label>
                        <input type="text" class="form-control text-center" readonly value="<fmt:formatDate pattern = "dd/MM/yyyy hh:mm:ss" value = "${championship.startDate}" />" />
                    </div>
                </div>
                <div class="col-8 h-100 d-inline-block m-auto text-right">
                    <button type="button" class="btn btn-success ${startDisabled}"><i class="fas fa-play-circle"></i> Start</button>
                </div>
            </div> 
            <hr>

            <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active toggleTag" data-name="rowTeams" data-toggle="tab" href="javascript:void()">Teams</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link toggleTag" data-name="rowMatchs" data-toggle="tab" href="javascript:void()">Matchs</a>
                    </li>
                </ul>
            </div>
            <div class="row" id="rowMatchs">
                <c:forEach items="${matches}" var="match" varStatus="status">
                    <div class="col-3 mt-3 h-100">
                        <div class="card">
                            <div class="card-body">
                                <div class="row mt-2">
                                    <div class="col-3">
                                        <img style="max-height: 50px; max-width: 50px;" src="${match.firstTeam.logoUrl}">
                                    </div>
                                    <c:if test="${match.finished}">

                                        <div class="col-2 text-left mt-2">
                                            <h5><b>${matchGoals[match].key}</b></h5>
                                        </div>
                                        <div class="col-2 text-center mt-2">
                                            <h5><b>x</b></h5>
                                        </div>
                                        <div class="col-2 text-right mt-2">
                                            <h5><b>${matchGoals[match].value}</b></h5>
                                        </div>
                                    </c:if>
                                    <c:if test="${not match.finished}">
                                        <div class="col-2 text-left mt-2">
                                            <h5><b>-</b></h5>
                                        </div>
                                        <div class="col-2 text-center mt-2">
                                            <h5><b>x</b></h5>
                                        </div>
                                        <div class="col-2 text-right mt-2">
                                            <h5><b>-</b></h5>
                                        </div>
                                    </c:if>
                                    <div class="col-2">
                                        <img style="max-height: 50px; max-width: 50px;" src="${match.secondTeam.logoUrl}">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 text-center ml-2">
                                        <b>${match.firstTeam.name}</b> vs <b>${match.secondTeam.name}</b>
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
                </c:forEach>
            </div>
            <br>
            <div class="row" id="rowTeams">
                <div class="table-responsive m-4">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Team</th>
                                <th>Owner</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>#</th>
                                <th>Team</th>
                                <th>Owner</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach items="${subscribes}" var="subscribe" varStatus="status">
                                <tr>
                                    <td>${subscribe.team.id}</td>
                                    <td>
                                        <div class="row">
                                            <div class="col-2 offset-1">
                                                <img style="max-height: 40px; max-width: 40px;" src="${subscribe.team.logoUrl}">
                                            </div>
                                            <div class="col-7 p-2">
                                                ${subscribe.team.name}
                                            </div>
                                        </div>
                                    </td>
                                    <td>${subscribe.team.owner.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-dark">${buttonText}</button>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#dataTable').DataTable();

        let rowMatchs = $("#rowMatchs");
        let rowTeams = $("#rowTeams");

        rowMatchs.hide();

        $('.toggleTag').click(function () {
            let name = $(this).data('name');
            if (name === "rowTeams") {
                rowMatchs.hide();
                rowTeams.show();
            } else {
                rowTeams.hide();
                rowMatchs.show();
            }
        });
    });
</script>