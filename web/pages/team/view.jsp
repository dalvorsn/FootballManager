<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="action" value="create-team-save" />
<c:set var="ownerId" value="${user.getId()}" />
<c:set var="buttonText" value="Create" />
<c:if test="${team != null}">
    <c:set var="id" value="${team.getId()}" />
    <c:set var="name" value="${team.getName()}" />
    <c:set var="logo" value="${team.getLogoUrl()}" />
    <c:set var="ownerId" value="${team.getOwner().getId()}" />
    <c:set var="action" value="edit-team-save" />
    <c:set var="buttonText" value="Edit" />
</c:if>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-1 font-weight-bold text-dark fa-lg">Team</h6>
    </div>

    <div class="card-body">
        <form action="${context}/router" method="POST">
            <input type="hidden" name="action" value="${action}"/>
            <input type="hidden" name="id" value="${id}" />
            <input type="hidden" name="ownerId" value="${ownerId}">

            <c:if test="${logo != null}">
                <br>
                <div class="row">
                    <div class="col text-center">
                        <img style="max-height: 50px; max-width: 50px;" src="${logo}">
                    </div>
                </div>

                <br>
            </c:if>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" value="${name}" id="name" name="name" aria-describedby="Team name" placeholder="Enter team name" required>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="logo_url">Logo</label>
                        <input type="url" class="form-control" id="logo_url" name="logo_url" required value="${logo}">
                    </div>
                </div>
            </div>

            <c:if test="${team != null}">
                <div class="dropdown-divider"></div>
                <br>
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Team Players</h5>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped" id="playersTeam" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Age</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Age</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach items="${team.getPlayers()}" var="player" varStatus="status">
                                                <tr>
                                                    <td>${player.getId()}</td>
                                                    <td>${player.getName()}</td>
                                                    <td>${player.getPosition()}</td>
                                                    <td></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col text-center">
                                        <button type="button" id="removeFromTeam" class="btn btn-danger">Remove</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Available Players</h5>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped" id="freePlayers" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Age</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Position</th>
                                                <th>Age</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach items="${availablePlayers}" var="player" varStatus="status">
                                                <tr>
                                                    <td>${player.getId()}</td>
                                                    <td>${player.getName()}</td>
                                                    <td>${player.getPosition()}</td>
                                                    <td>${player.getAge()}</td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div class="col text-center">
                                        <button type="button" id="addToTeam" class="btn btn-success">Add to team</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <br>
            <div class="text-center">
                <button type="submit" class="btn btn-dark btn-lg">${buttonText}</button>
            </div>

        </form>
    </div>
</div>
<script>
    $(document).ready(function () {
        let dtPlayersTeam = $('#playersTeam').DataTable({select: true});
        let dtFreePlayers = $('#freePlayers').DataTable({select: true});
        
        $("#addToTeam").click( () => {
           let selected = [].map.call(dtFreePlayers.rows('.selected').data(),  e => { return e[0]; });
           if(selected.length === 0) {
               return;
           }
           
           let form = $('<form action="${context}/router" method="POST"></form>');
           form.append($('<input type="hidden" name="action" value="team-add-player" />'));
           form.append($('<input type="hidden" name="team_id" value="${id}" />'));
           
           selected.forEach( (obj) => {
               form.append($('<input type="hidden" name="playerId" value="' + obj[0] +'" />'));
           });
           
           $(document.body).append(form);
           form.submit();
        });
        
        // TODO: usar ajax
        $("#removeFromTeam").click( () => {
           let selected = [].map.call(dtPlayersTeam.rows('.selected').data(),  e => { return e[0]; });
           if(selected.length === 0) {
               return;
           }
           
           let form = $('<form action="${context}/router" method="POST"></form>');
           form.append($('<input type="hidden" name="action" value="team-rem-player" />'));
           form.append($('<input type="hidden" name="team_id" value="${id}" />'));
           
           selected.forEach( (obj) => {
               form.append($('<input type="hidden" name="playerId" value="' + obj[0] +'" />'));
           });
           
           $(document.body).append(form);
           form.submit();
        });
    });
</script>