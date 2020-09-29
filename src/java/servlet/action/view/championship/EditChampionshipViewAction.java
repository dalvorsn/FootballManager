/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.championship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.ChampionshipSubscribeDAO;
import model.dao.GoalDAO;
import model.dao.MatchDAO;
import model.domain.Championship;
import model.domain.ChampionshipSubscribe;
import model.domain.Goal;
import model.domain.Match;
import servlet.action.IAction;

/**
 *
 * @author luciano
 */
public class EditChampionshipViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=championship/view");

        Championship championship = new ChampionshipDAO().get(Long.parseLong(request.getParameter("id")));
        List<ChampionshipSubscribe> subscribes = new ChampionshipSubscribeDAO().getAllByChampionship(championship.getId());
        List<Match> matches = championship.getMatches();

        Map<Match, Pair<Integer, Integer>> matchGoals = new HashMap<>();
        Integer firstTeamGoalsCount, secondTeamGoalsCount;
        for (Match match : matches) {
            if(!match.getFinished())
                continue;
            
            List<Goal> goals = new GoalDAO().getAllByMatch(match.getId());
            firstTeamGoalsCount = 0;
            secondTeamGoalsCount = 0;
            for (Goal goal : goals) {
                if (goal.getTeam() == match.getFirstTeam()) {
                    firstTeamGoalsCount++;
                } else {
                    secondTeamGoalsCount++;
                }
            }
            
            matchGoals.put(match, new Pair(firstTeamGoalsCount, secondTeamGoalsCount));
        }
        
        request.setAttribute("championship", championship);
        request.setAttribute("subscribes", subscribes);
        request.setAttribute("matches", matches);
        request.setAttribute("matchGoals", matchGoals);
        request.setAttribute("activeMenu", "championship");
        rd.forward(request, response);
    }

}
