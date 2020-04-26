<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="action" value="create-player-save" />
<c:set var="buttonText" value="Create" />
<c:if test="${player != null}">
    <c:set var="id" value="${player.getId()}" />
    <c:set var="name" value="${player.getName()}" />
    <c:set var="age" value="${player.getAge()}" />
    <c:set var="pos" value="${player.getPosition()}" />
    <c:if test="${player.getTeam() != null}">
        <c:set var="teamId" value="${player.getTeam().getId()}" />
    </c:if>
    
    <c:set var="action" value="edit-player-save" />
    <c:set var="buttonText" value="Edit" />
</c:if>

<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-1 font-weight-bold text-dark fa-lg">Player</h6>
    </div>

    <div class="card-body">
        <form action="${context}/router" method="POST">
            <input type="hidden" name="action" value="${action}"/>
            <input type="hidden" name="id" value="${id}">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" value="${name}" id="name" name="name" aria-describedby="Player name" placeholder="Enter full name" required>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="age">Age</label>
                        <input type="number" class="form-control" id="age" name="age" required value="${age}">
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="position">Position</label>
                        <select class="form-control" id="position" name="position" required>
                            <c:forEach items="${positions}" var="position" varStatus="status">
                                <option value="${position}" <c:if test="${position == pos}">selected</c:if> >${position}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="team">Team</label>
                        <select class="form-control" id="position" name="team">
                            <option value="">No team</option>
                            <c:forEach items="${teams}" var="team" varStatus="status">
                                <option value="${team.getId()}" <c:if test="${team.getId() == teamId}">selected</c:if> >${team.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <br>
            <div class="text-center">
                <button type="submit" class="btn btn-dark">${buttonText}</button>
            </div>

        </form>
    </div>
</div>