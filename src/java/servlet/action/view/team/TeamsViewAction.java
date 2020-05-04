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
import model.dao.TeamDAO;
import model.domain.Team;
import model.domain.User;
import servlet.action.IAction;

/**
 *
 * @author Luciano
 */
public class TeamsViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=team/list");
        
        User user = (User) request.getSession().getAttribute("user");
        List<Team> teams = new TeamDAO().getTeamsByUser(user.getId());
        request.setAttribute("teams", teams);
        
        request.setAttribute("activeMenu", "team");
        rd.forward(request, response);
    }

}
