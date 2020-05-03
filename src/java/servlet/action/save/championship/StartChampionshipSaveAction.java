/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.save.championship;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.MatchDAO;
import model.domain.Championship;
import model.domain.ChampionshipSubscribe;
import model.domain.Match;
import model.domain.Team;
import servlet.action.IAction;
import servlet.action.view.championship.ChampionshipsViewAction;

/**
 *
 * @author dalvo
 */
public class StartChampionshipSaveAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long championshipId = Long.parseLong( request.getParameter("id"));
        ChampionshipDAO dao = new ChampionshipDAO();
        Championship championship = dao.get(championshipId);
        
        if (championship.getStartDate() == null ) {
            MatchDAO daoMatch = new MatchDAO();
            List<Match> matches = this.makeMatches(championship);
            for(Match match : matches) {
                daoMatch.insert(match);
            }
            
            championship.setStartDate(new Timestamp(System.currentTimeMillis()));
            dao.update(championship);
            
            request.setAttribute("title", "Information!");
            request.setAttribute("message", "Subscribe done successfully.");
            request.setAttribute("colorClass", "success");
        } else {
            request.setAttribute("message", "This champioship already start.");
            request.setAttribute("colorClass", "danger");
        }
        
        new ChampionshipsViewAction().execute(request, response);
    }
    
    public List<Match> makeMatches(Championship championship) {
        List<Match> matches = new ArrayList<>();
        List<ChampionshipSubscribe> subscribers = new ArrayList<>(championship.getSubscribes());
            
        Team first, second;
        int t = subscribers.size();
        int m = subscribers.size() / 2;
        for (int i = 0; i < t - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    first = subscribers.get(t - j - 1).getTeam();
                    second = subscribers.get(j).getTeam();
                } else {
                    first = subscribers.get(j).getTeam();
                    second = subscribers.get(t - j - 1).getTeam();
                }
                
                matches.add( new Match( championship, first, second ));
            }
            
            subscribers.add(1, subscribers.remove(subscribers.size()-1));
        }
        
        return matches;
    }
}
