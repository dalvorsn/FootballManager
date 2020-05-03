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
import model.dao.PlayerDAO;
import model.domain.Player;
import servlet.action.IAction;

/**
 *
 * @author dalvo
 */
public class PlayersViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=player/list");
        
        List<Player> players = new PlayerDAO().getAll();
        request.setAttribute("players", players);
        
        request.setAttribute("activeMenu", "player");
        rd.forward(request, response);
    }

}
