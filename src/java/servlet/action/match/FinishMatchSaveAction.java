/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.match;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.MatchDAO;
import model.domain.Match;
import servlet.action.IAction;

/**
 *
 * @author luciano
 */
public class FinishMatchSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long matchId = Long.parseLong( request.getParameter("match_id") );
        MatchDAO dao = new MatchDAO();
        Match match = dao.get(matchId);
        match.setFinished(true);
        dao.update(match);
        
        response.sendRedirect("router?action=edit-match&id="+ match.getId());
    }
    
}
