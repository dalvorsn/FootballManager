package servlet.action.view;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.GoalDAO;
import model.dao.MatchDAO;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.domain.Goal;
import model.domain.Match;
import model.domain.Team;
import model.dto.MatchesResultDTO;
import servlet.action.IAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luciano
 */
public class HomeViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=home");

        List<Match> matches = new MatchDAO().getLastMatches();
        List<MatchesResultDTO> matchResults = new ArrayList<>();

        GoalDAO goalDAO = new GoalDAO();
        for (int index = 0; index < matches.size(); index++) {
            Match match = matches.get(index);
            Team first = match.getFirstTeam();
            Team second = match.getSecondTeam();

            Integer firstTeamGoalsCount = 0;
            Integer secondTeamGoalsCount = 0;

            if (match.getFinished()) {
                List<Goal> goals = goalDAO.getAllByMatch(match.getId());

                for (Goal goal : goals) {
                    if (goal.getTeam().equals(first)) {
                        firstTeamGoalsCount++;
                    } else {
                        secondTeamGoalsCount++;
                    }
                }
            }

            MatchesResultDTO m = new MatchesResultDTO();
            
            m.setId(match.getId());
            m.setFirstTeamId(first.getId());
            m.setFirstTeamGoals(firstTeamGoalsCount);
            m.setFirstTeamLogo(first.getLogoUrl());
            m.setFirstTeamName(first.getName());

            m.setSecondTeamId(second.getId());
            m.setSecondTeamLogo(second.getLogoUrl());
            m.setSecondTeamName(second.getName());
            m.setSecondTeamGoals(secondTeamGoalsCount);
            m.setFinished(match.getFinished());

            matchResults.add(m);
        }

        
        request.setAttribute("matchResults", matchResults);
        
        request.setAttribute("playersCount", new PlayerDAO().getCount());
        request.setAttribute("teamsCount", new TeamDAO().getCount());
        request.setAttribute("championshipsCount", new ChampionshipDAO().getCount());
        request.setAttribute("matchesCount", new MatchDAO().getCount());

        request.setAttribute("activeMenu", "home");
        rd.forward(request, response);
    }

}
