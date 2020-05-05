<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="first" value="${match.firstTeam}" />
<c:set var="second" value="${match.secondTeam}" />

<!-- finish match modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Alert</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">You will finish this match, are you sure?</div>
            <div class="modal-footer">
                <form action="${context}/router" method="POST">
                    <input type="hidden" name="action" value="finish-match-save">
                    <input type="hidden" name="match_id" value="${match.id}">
                    <button class="btn btn-dark" type="button" data-dismiss="modal">Cancel</button>
                    <button class="btn btn-danger" type="submit">Finish</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- add goal modal -->
<div class="modal fade h-100" id="addGoalModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add Goals</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="${context}/router" method="POST" id="addGoalForm">
                    <input type="hidden" name="action" value="add-goal-match-save">
                    <input type="hidden" name="match_id" value="${match.id}">
                    <div class="form-group">
                        <label for="team">Team</label>
                        <select class="form-control" id="team" name="team_id" required>
                            <option value="${first.id}" data-pos="first">${first.name}</option>
                            <option value="${second.id}" data-pos="second">${second.name}</option>
                        </select>
                    </div>
                    <div class="form-group" id="firstTeamPlayers">
                        <label>Player</label>
                        <select class="form-control" id="selectFirst" name="player_id" required>
                            <c:forEach items="${first.players}" var="player">
                                <option value="${player.id}">${player.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group" id="secondTeamPlayers">
                        <label>Player</label>
                        <select class="form-control"  name="player_id" id="selectSecond" required>
                            <c:forEach items="${second.players}" var="player">
                                <option value="${player.id}">${player.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="row">
                        <div class="col text-center">
                            <label>Goal Time</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3 offset-3">
                            <div class="input-group">
                                <input type="number" class="form-control" name="round" value="0" required>
                                <div class="input-group-append">
                                    <span class="input-group-text">"</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-3">
                            <div class="input-group">
                                <input type="number" class="form-control" name="minutes"  value="0" required>
                                <div class="input-group-append">
                                    <span class="input-group-text">'</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" form="addGoalForm" class="btn btn-success">Add</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<div class="card shadow mb-4">

    <div class="card-header py-3">
        <div class="row">
            <div class="col">
                <h6 class="m-1 font-weight-bold text-dark fa-lg">Match</h6>    
            </div>
            <div class="col text-right">
                <a href="${context}/router?action=edit-championship&id=${match.championship.id}" title="Back" ><i class="fas fa-reply"></i></i></a>
            </div>
        </div>
    </div>

    <div class="card-body">
        <div class="row">
            <div class="col-3 offset-1 text-center">
                <img style="max-height: 150px; max-width: 150px;" src="${match.firstTeam.logoUrl}">
            </div>
            <div class="col-1 mt-auto text-center">
                <h1 class="display-1">${firstTeamGoalsCount}</h1>
            </div>
            <div class="col-2 mt-auto text-center">
                <p class="h2">vs</p>
            </div>
            <div class="col-1 mt-auto text-center">
                <h1 class="display-1">${secondTeamGoalsCount}</h1>
            </div>
            <div class="col-3 text-center">
                <img style="max-height: 150px; max-width: 150px;" src="${match.secondTeam.logoUrl}">
            </div>
        </div>
        <div class="row">
            <div class="col-5 text-center">
                <p class="h1">${match.firstTeam.name}</p>  
            </div>
            <div class="col-2"></div>
            <div class="col-5 text-center">
                <p class="h1">${match.secondTeam.name}</p>  
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <c:forEach items="${goals}" var="goal" varStatus="status">
                    <c:if test="${goal.team == first}">
                        <div class="row">
                            <div class="col-1 text-center">
                                <i class="far fa-futbol text-success"></i>
                            </div>
                            <div class="col-1 text-center">
                                <small>${goal.goalRound}" ${goal.goalMinute}'</small>
                            </div>
                            <div class="col-3">
                                ${goal.player.name}
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${goal.team == second}">
                        <div class="row">
                            <div class="col-3 offset-7 text-right">
                                ${goal.player.name}
                            </div>
                            <div class="col-1 text-center">
                                <small>${goal.goalRound}" ${goal.goalMinute}'</small>
                            </div>
                            <div class="col-1 text-center">
                                <i class="far fa-futbol text-warning"></i>
                            </div>

                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <c:if test="${not match.finished}">
            <div class="row">
                <div class="col text-center">
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addGoalModal">Add Goal</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">Finish Match</button>
                </div>
            </div>
        </c:if>
    </div>
</div>

<script>
    $(document).ready(function () {
        let firstTeamSelect = $('#firstTeamPlayers');
        let secondTeamSelect = $('#secondTeamPlayers');
        let selectSecond = $('#selectSecond');
        let selectFirst = $('#selectFirst');

        selectSecond.prop('disabled', true);
        secondTeamSelect.hide();

        $('#team').change(function () {
            let selected = $(this).children('option:selected').data('pos');
            if (selected === 'first') {
                firstTeamSelect.show();
                selectFirst.prop('disabled', false);

                secondTeamSelect.hide();
                selectSecond.prop('disabled', true);
            } else {
                firstTeamSelect.hide();
                selectFirst.prop('disabled', true);

                secondTeamSelect.show();
                selectSecond.prop('disabled', false);
            }
        });

    });
</script>
