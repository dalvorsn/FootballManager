<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Alert</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">You will delete this player, are you sure?</div>
            <div class="modal-footer">
                <button class="btn btn-dark" type="button" data-dismiss="modal">Cancel</button>
                <a id="deleteAction" class="btn btn-danger" href="#">Delete</a>
            </div>
        </div>
    </div>
</div>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <div class="row">
            <div class="col">
                <h6 class="m-1 font-weight-bold text-dark fa-lg">Teams</h6>    
            </div>
            <div class="col text-right">
                <a href="${context}/router?action=create-team" title="Add" ><i class="fas fa-plus-square right fa-lg text-success"></i></a>
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
                        <th>Logo</th>
                        <th></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Logo</th>
                        <th></th>
                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach items="${teams}" var="team" varStatus="status">
                        <tr>
                            <td>${team.getId()}</td>
                            <td>${team.getName()}</td>
                            <td class="text-center"><img style="max-height: 30px; max-width: 30px;" src="${team.getLogoUrl()}"></td>
                            <td class="text-center">
                                <a href="${context}/router?action=edit-team&id=${team.getId()}" title="Edit"><i class="fas fa-edit text-dark"></i></a>
                                <a data-action="${context}/router?action=delete-player&id=${team.getId()}" class="deleteButton" data-toggle="modal" data-target="#deleteModal" title="Delete"><i class="far fa-trash-alt text-danger"></i></a>
                                
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

        $(".deleteButton").click(function () {
            $("#deleteAction").attr("href", $(this).data("action"));
        });

    });
</script>