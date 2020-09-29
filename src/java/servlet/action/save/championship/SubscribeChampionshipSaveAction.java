/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.championship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.ChampionshipSubscribeDAO;
import model.dao.TeamDAO;
import model.domain.Championship;
import model.domain.ChampionshipSubscribe;
import model.domain.Team;
import servlet.action.IAction;
import servlet.action.view.championship.ChampionshipsViewAction;

/**
 *
 * @author luciano
 */
public class SubscribeChampionshipSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long championshipId = Long.parseLong(request.getParameter("championship_id") );
        Long teamId = Long.parseLong( request.getParameter("team_id") );
            
        ChampionshipDAO dao = new ChampionshipDAO();
        if(dao.isTeamSubscribed(teamId, championshipId)) {
            request.setAttribute("message", "You are already subscribed on this champioship.");
            request.setAttribute("colorClass", "danger");
        } else {
            Team team = new TeamDAO().get(teamId);
            Championship championship = dao.get(championshipId);
            new ChampionshipSubscribeDAO().insert( new ChampionshipSubscribe(championship, team));
            
            request.setAttribute("title", "Information!");
            request.setAttribute("message", "Subscribe done successfully.");
            request.setAttribute("colorClass", "success");
        }
        
        new ChampionshipsViewAction().execute(request, response);
    }
    
}
