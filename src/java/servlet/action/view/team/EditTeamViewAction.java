/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.team;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.domain.Player;
import model.domain.Team;
import servlet.action.IAction;

/**
 *
 * @author Luciano
 */
public class EditTeamViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=team/view");
        Long id = Long.parseLong(request.getParameter("id"));
        Team team = new TeamDAO().get(id);
        
        PlayerDAO dao = new PlayerDAO();
        List<Player> availablePlayers = dao.getAllAvailable();
        List<Player> teamPlayers = dao.getAllByTeam(team.getId());
        
        request.setAttribute("availablePlayers", availablePlayers);
        request.setAttribute("teamPlayers", teamPlayers);
        request.setAttribute("team", team);
        
        request.setAttribute("activeMenu", "team");
        rd.forward(request, response);
    }
    
}
