/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.player;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.TeamDAO;
import model.domain.Team;
import model.domain.e.ESoccerPosition;
import servlet.action.IAction;

/**
 *
 * @author Luciano
 */
public class CreatePlayerViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=player/view");
        
        List<Team> teams = new TeamDAO().getAll();
        request.setAttribute("positions", ESoccerPosition.values());
        request.setAttribute("teams", teams);
        
        request.setAttribute("activeMenu", "player");
        rd.forward(request, response);
    }
    
}
