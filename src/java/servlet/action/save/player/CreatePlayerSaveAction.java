package servlet.action.save.player;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.PlayerDAO;
import model.dao.TeamDAO;
import model.domain.Player;
import model.domain.Team;
import model.domain.e.ESoccerPosition;
import servlet.action.IAction;
import servlet.action.view.player.PlayersViewAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalvo
 */
public class CreatePlayerSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String position = request.getParameter("position");
        String teamId = request.getParameter("team");
        
        Player player = new Player();
        player.setAge(age);
        player.setName(name);
        player.setPosition(ESoccerPosition.valueOf(position));
        
        if(teamId != null && !teamId.isEmpty()) {
            Team team = new TeamDAO().get(Long.parseLong(teamId));
            if(team != null) {
                player.setTeam(team);
            }
        }
        
        new PlayerDAO().insert(player);
        new PlayersViewAction().execute(request, response);
    }
    
}
