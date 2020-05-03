<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="row">
    <div class="col">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <div class="row">
                    <div class="col">
                        <h6 class="m-1 font-weight-bold text-dark fa-lg">Rank</h6>    
                    </div>
                </div>
            </div>

            <div class="card-body">
                <select class="custom-select" id="selectChampionship">
                    <option value="">Select a championship</option>
                    <c:forEach items="${championships}" var="championship" varStatus="status">
                        <option value="${championship.id}" <c:if test="${curChamp.id == championship.id}">selected</c:if> >${championship.name}</option>
                    </c:forEach>
                </select>
                <hr />
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th class="text-center">Position</th>
                                <th class="text-center">Team</th>
                                <th class="text-center">Played</th>
                                <th class="text-center">Won</th>
                                <th class="text-center">Lost</th>
                                <th class="text-center">Drawn</th>
                                <th class="text-center">For</th>
                                <th class="text-center">Against</th>
                                <th class="text-center">Points</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th class="text-center">Position</th>
                                <th class="text-center">Team</th>
                                <th class="text-center">Played</th>
                                <th class="text-center">Won</th>
                                <th class="text-center">Lost</th>
                                <th class="text-center">Drawn</th>
                                <th class="text-center">For</th>
                                <th class="text-center">Against</th>
                                <th class="text-center">Points</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach items="${rank}" var="rankRow">
                                <tr>
                                    <td class="text-center align-middle" >${rankRow.position}º</td>
                                    <td class="text-center align-middle">
                                        <div class="row">
                                            <div class="col-2 offset-1">
                                                <img style="max-height: 40px; max-width: 40px;" src="${rankRow.logo}">
                                            </div>
                                            <div class="col-7 m-auto">
                                                ${rankRow.name}
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center align-middle">${rankRow.played}</td>
                                    <td class="text-center align-middle">${rankRow.won}</td>
                                    <td class="text-center align-middle">${rankRow.lost}</td>
                                    <td class="text-center align-middle">${rankRow.drawn}</td>
                                    <td class="text-center align-middle">${rankRow.goalsFor}</td>
                                    <td class="text-center align-middle">${rankRow.goalsAgainst}</td>
                                    <td class="text-center align-middle">${rankRow.points}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-3">
        <c:if test="${topScorer != null}">

            <div class="card shadow mb-4">
                <div class="card-header text-center">
                    <h6>Top Scorer</h6>
                </div>

                <div class="card-body">
                    <div class="row">
                        <div class="col text-center">
                            ${topScorer.teamName}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col text-center">
                            <img style="max-height: 50px; max-width: 50px;" src="${topScorer.teamLogo}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 offset-1">
                            ${topScorer.name} 
                        </div>
                        <div class="col-4 text-right">
                            <b>${topScorer.goals}</b> <i class="far fa-futbol text-warning"></i>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <div class="card shadow mb-4">
            <div class="card-header">
                <div class="row">
                    <div class="col-1">
                        <a href="#" id="prevRound"><i class="fas fa-chevron-left"></i></a>
                    </div>
                    <div class="col-10 text-center">
                        <h6>Rodada <span id="roundCounter">1</span></h6>
                    </div>
                    <div class="col-1">
                        <a href="#" id="nextRound"><i class="fas fa-chevron-right"></i></a>
                    </div>
                </div>
            </div>

            <div class="card-body">
                <c:forEach items="${matches}" var="match" varStatus="status">
                    <div class="row matchRound-${match.round}">
                        <div class="col-12">
                            <div class="row mt-2">
                                <div class="col-3">
                                    <img style="max-height: 50px; max-width: 50px;" src="${match.firstTeamLogo}">
                                </div>
                                <c:if test="${match.finished}">
                                    <div class="col-2 text-left mt-2">
                                        <h5><b>${match.firstTeamGoals}</b></h5>
                                    </div>
                                    <div class="col-2 text-center mt-2">
                                        <h5><b>x</b></h5>
                                    </div>
                                    <div class="col-2 text-right mt-2">
                                        <h5><b>${match.secondTeamGoals}</b></h5>
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
                                    <img style="max-height: 50px; max-width: 50px;" src="${match.secondTeamLogo}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 text-center">
                                    <b>${match.firstTeamName}</b> vs <b>${match.secondTeamName}</b>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        let currentRound = 0;
        let maxRounds = '${rounds}';
        let roundCounter = $('#roundCounter');

        function prevRound() {
            setRound(currentRound - 1);
        }

        function nextRound() {
            setRound(currentRound + 1);
        }

        function setRound(n) {
            currentRound = Math.max(1, Math.min(maxRounds, n));

            console.log(currentRound)

            $('div[class*=" matchRound-"]').hide();
            $('div.matchRound-' + currentRound).show();

            roundCounter.html(currentRound);
        }

        $('#dataTable').DataTable();

        setRound(1);

        $('#selectChampionship').change(function () {
            let selected = $(this).children('option:selected').val();
            location = "${context}/router?action=rank&id=" + selected;
        });

        $("#prevRound").click(() => {
            console.log('prev');
            prevRound();
        });
        $("#nextRound").click(() => {
            nextRound();
        });


    });
</script>