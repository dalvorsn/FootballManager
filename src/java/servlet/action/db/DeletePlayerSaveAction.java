/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.PlayerDAO;
import model.domain.Player;
import servlet.action.IAction;
import servlet.action.view.PlayersViewAction;

/**
 *
 * @author dalvo
 */
public class DeletePlayerSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        PlayerDAO dao = new PlayerDAO();
        
        Player player = dao.get( Long.parseLong(id) );
        dao.delete(player);
        new PlayersViewAction().execute(request, response);
    }
    
}
