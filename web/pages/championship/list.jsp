<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="subscribeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Subscribe</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${context}/router" method="POST" id="subscribeForm">
                    <input type="hidden" name="action" value="subscribe-championship-save">
                    <input type="hidden" id="championship_id" name="championship_id" value="">
                    <div class="form-group">
                        <label for="position">Select your team to subscribe on <b id="championship_name"></b></label>
                        
                        <select class="form-control" name="team_id" required>
                            <c:forEach items="${teams}" var="team" varStatus="status">
                                <option value="${team.id}">${team.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-dark" type="button" data-dismiss="modal">Cancel</button>
                <button form="subscribeForm" type="submit" class="btn btn-success">Subscribe</button>
            </div>
        </div>
    </div>
</div>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <div class="row">
            <div class="col">
                <h6 class="m-1 font-weight-bold text-dark fa-lg">Championships</h6>    
            </div>
            <div class="col text-right">
                <a href="${context}/router?action=create-championship" title="Add" ><i class="fas fa-plus-square right fa-lg text-success"></i></a>
            </div>
        </div>
    </div>

    <div class="card-body">

        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th class="text-center">Subscribed Teams</th>
                        <th class="text-center">Start Date</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th class="text-center">Subscribed Teams</th>
                        <th class="text-center">Start Date</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach items="${championships}" var="championship" varStatus="status">
                        <tr>
                            <td>${championship.id}</td>
                            <td>${championship.name}</td>
                            <td class="text-center">${championship.subscribedTeams} / ${championship.max}</td>
                            <td class="text-center"><fmt:formatDate pattern = "dd/MM/yyyy hh:mm:ss" value = "${championship.startDate}" /></td>
                            <td class="text-center">
                                <!-- Player -->
                                <c:if test="${championship.canSubscribe}">
                                    <a href="#" title="Subscribe">
                                        <a data-id="${championship.id}" data-name="${championship.name}" class="subscribeButton" data-toggle="modal" data-target="#subscribeModal" title="Subscribe">
                                            <i class="fas fa-sign-in-alt text-success"></i>
                                        </a>
                                    </a>
                                </c:if>

                                <!-- ADM -->
                                <c:if test="${user.isAdmin}" >
                                    <a href="${context}/router?action=edit-championship&id=${championship.id}" title="Edit Championship">
                                        <i class="far fa-edit text-dark"></i>
                                    </a>
                                    <c:if test="${!championship.canSubscribe && championship.startDate == null}" >
                                        <a href="${context}/router?action=start-championship-save&id=${championship.id}" title="Start Championship">
                                            <i class="far fa-play-circle text-success"></i>
                                        </a>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#dataTable').DataTable();
        
        $(".subscribeButton").click(function () {
            $("#championship_id").val($(this).data("id"));
            $("#championship_name").html($(this).data("name"));
        });
    });
</script>