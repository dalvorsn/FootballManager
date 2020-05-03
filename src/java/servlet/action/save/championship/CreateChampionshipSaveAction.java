/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.championship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.domain.Championship;
import servlet.action.IAction;

/**
 *
 * @author dalvo
 */
public class CreateChampionshipSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        Integer maxSubscribers = Integer.parseInt(request.getParameter("max_subscribers"));
        
        
        ChampionshipDAO dao = new ChampionshipDAO();
        Championship championship = dao.insert(new Championship(name, maxSubscribers));
        
        response.sendRedirect("router?action=edit-championship&id="+ championship.getId());
    }
    
}
