/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.TeamDAO;
import model.dao.UserDAO;
import model.domain.Team;
import model.domain.User;
import servlet.action.IAction;
import servlet.action.view.team.TeamsViewAction;

/**
 *
 * @author dalvo
 */
public class CreateTeamSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Long ownerId = Long.parseLong(request.getParameter("ownerId"));
        String logo = request.getParameter("logo_url");
        
        User user = new UserDAO().get(ownerId);
        
        Team team = new Team();
        team.setLogoUrl(logo);
        team.setName(name);
        team.setOwner(user);
        
        new TeamDAO().insert(team);
        new TeamsViewAction().execute(request, response);
    }
    
}
