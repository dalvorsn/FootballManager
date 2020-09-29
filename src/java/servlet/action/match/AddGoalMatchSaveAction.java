/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.match;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.GoalDAO;
import model.dao.MatchDAO;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.domain.Goal;
import model.domain.Match;
import model.domain.Player;
import model.domain.Team;
import servlet.action.IAction;

/**
 *
 * @author luciano
 */
public class AddGoalMatchSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long matchId = Long.parseLong( request.getParameter("match_id") );
        Long playerId = Long.parseLong( request.getParameter("player_id") );
        Long teamId = Long.parseLong( request.getParameter("team_id") );
        Integer round = Integer.parseInt( request.getParameter("round") );
        Integer minutes = Integer.parseInt( request.getParameter("minutes") );
        
        Match match = new MatchDAO().get(matchId);
        Player player = new PlayerDAO().get(playerId);
        Team team = new TeamDAO().get(teamId);
        Goal goal = new Goal(match, team, player, round, minutes);
        
        new GoalDAO().insert(goal);
        
        response.sendRedirect("router?action=edit-match&id="+ match.getId());
    }
    
}
