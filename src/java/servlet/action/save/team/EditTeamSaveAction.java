package servlet.action.save.team;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.TeamDAO;
import model.domain.Team;
import servlet.action.IAction;
import servlet.action.view.team.TeamsViewAction;

/**
 *
 * @author dalvo
 */
public class EditTeamSaveAction implements IAction {
    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String teamId = request.getParameter("id");
        String name = request.getParameter("name");
        String logo = request.getParameter("logo_url");
        
        TeamDAO dao = new TeamDAO();
        Team team = dao.get(Long.parseLong(teamId));
        team.setLogoUrl(logo);
        team.setName(name);
        
        dao.update(team);
        new TeamsViewAction().execute(request, response);
    }
}
