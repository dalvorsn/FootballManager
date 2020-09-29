/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.championship;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.TeamDAO;
import model.domain.Team;
import model.domain.User;
import model.dto.ChampionshipDTO;
import servlet.action.IAction;

/**
 *
 * @author luciano
 */
public class ChampionshipsViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=championship/list");
        
        User user = (User) request.getAttribute("user");
        List<Team> teams = new TeamDAO().getTeamsByUser(user.getId());
        List<ChampionshipDTO> championships = new ChampionshipDAO().getAllView();
        for(ChampionshipDTO championship : championships) {
            championship.setCanSubscribe( this.canSubscribe(championship, teams) );
        }
        
        request.setAttribute("championships", championships);
        request.setAttribute("teams", teams);
        
        request.setAttribute("activeMenu", "championship");
        rd.forward(request, response);
    }
    
    private boolean canSubscribe(ChampionshipDTO championship, List<Team> teams) {
        if(teams.isEmpty())
            return false;
            
        if (championship.getCanSubscribe())
            return false;
        
        if (championship.getSubscribedTeams() >= championship.getMax())
            return false;
        
        return championship.getStartDate() == null;
    }

}
