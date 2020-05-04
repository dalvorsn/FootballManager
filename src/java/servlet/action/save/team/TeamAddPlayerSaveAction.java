package servlet.action.save.team;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.domain.Player;
import model.domain.Team;
import servlet.action.IAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luciano
 */
public class TeamAddPlayerSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TeamDAO teamDao = new TeamDAO();
        String teamId = request.getParameter("team_id");
        Team team = teamDao.get(Long.parseLong(teamId));
        String[] playerIds = request.getParameterValues("playerId");
        
        PlayerDAO playerDao = new PlayerDAO();
        for(String playerId : playerIds) {
            Player player = playerDao.get(Long.parseLong(playerId));
            player.setTeam(team);
            playerDao.update(player);
        }
        
        response.sendRedirect("router?action=edit-team&id="+teamId);
    }
    
}
