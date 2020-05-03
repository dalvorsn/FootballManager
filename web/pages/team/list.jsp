<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
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
                            <td>${team.id}</td>
                            <td>${team.name}</td>
                            <td class="text-center"><img style="max-height: 30px; max-width: 30px;" src="${team.logoUrl}"></td>
                            <td class="text-center">
                                <a href="${context}/router?action=edit-team&id=${team.id}" title="Edit"><i class="fas fa-edit text-dark"></i></a>
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
    });
</script>