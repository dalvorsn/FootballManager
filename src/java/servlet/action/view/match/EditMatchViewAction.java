/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.match;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.GoalDAO;
import model.dao.MatchDAO;
import model.domain.Goal;
import model.domain.Match;
import servlet.action.IAction;

/**
 *
 * @author dalvo
 */
public class EditMatchViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=match/view");
        
        Long matchId = Long.parseLong(request.getParameter("id"));
        Match match = new MatchDAO().get(matchId);
        List<Goal> goals = new GoalDAO().getAllByMatch(matchId);
        Integer firstTeamGoalsCount = 0;
        Integer secondTeamGoalsCount = 0;
        for(Goal goal : goals) {
            if(goal.getTeam() == match.getFirstTeam()) {
                firstTeamGoalsCount++;
            } else {
                secondTeamGoalsCount++;
            }
        }
        
        
        request.setAttribute("match", match);
        request.setAttribute("goals", goals);
        request.setAttribute("firstTeamGoalsCount", firstTeamGoalsCount);
        request.setAttribute("secondTeamGoalsCount", secondTeamGoalsCount);
        
        request.setAttribute("activeMenu", "championship");
        rd.forward(request, response);
    }
    
}
