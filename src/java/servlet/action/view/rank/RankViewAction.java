/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.action.view.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.ChampionshipDAO;
import model.dao.GoalDAO;
import model.dao.TeamDAO;
import model.domain.Championship;
import model.domain.Goal;
import model.domain.Match;
import model.domain.Team;
import model.dto.ChampionshipSelectDTO;
import model.dto.MatchesResultDTO;
import model.dto.RankRowDTO;
import model.dto.TopScorerDTO;
import servlet.action.IAction;

/**
 *
 * @author dalvo
 */
public class RankViewAction implements IAction {

    @Override
    public boolean requiresAuth() {
        return true;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("template.jsp?page=rank/view");
        ChampionshipDAO dao = new ChampionshipDAO();
        List<ChampionshipSelectDTO> championships = dao.getAllStarted();

        String championshipId = request.getParameter("id");
        if (championshipId != null && !championshipId.isEmpty()) {
            Championship championship = dao.get(Long.parseLong(championshipId));
            request.setAttribute("curChamp", championship);

            List<Match> matches = championship.getMatches();
            List<MatchesResultDTO> matchResults = new ArrayList<>();
            
            Integer rounds = championship.getSubscribes().size() - 1;
            Integer teamsByRound = matches.size() / rounds;
            
            GoalDAO goalDAO = new GoalDAO();
            for( int index = 0; index < matches.size(); index++ ) {
                Match match = matches.get(index);
                Team first = match.getFirstTeam();
                Team second = match.getSecondTeam();

                Integer firstTeamGoalsCount = 0;
                Integer secondTeamGoalsCount = 0;

                if (match.getFinished()) {
                    List<Goal> goals = goalDAO.getAllByMatch(match.getId());

                    for (Goal goal : goals) {
                        if (goal.getTeam().equals(first)) {
                            firstTeamGoalsCount++;
                        } else {
                            secondTeamGoalsCount++;
                        }
                    }
                }
                
                MatchesResultDTO m = new MatchesResultDTO();

                m.setFirstTeamId(first.getId());
                m.setFirstTeamGoals(firstTeamGoalsCount);
                m.setFirstTeamLogo(first.getLogoUrl());
                m.setFirstTeamName(first.getName());

                m.setSecondTeamId(second.getId());
                m.setSecondTeamLogo(second.getLogoUrl());
                m.setSecondTeamName(second.getName());
                m.setSecondTeamGoals(secondTeamGoalsCount);
                
                m.setRound( (int) Math.ceil( (index + 1.0) / teamsByRound ) );
                m.setFinished(match.getFinished());

                matchResults.add(m);
            }

            TopScorerDTO topScorer = dao.getTopScorer(championship.getId());
            List<RankRowDTO> rank = this.calculateRank(championship, matchResults, topScorer);

            request.setAttribute("rounds", rounds);
            request.setAttribute("matches", matchResults);
            request.setAttribute("rank", rank);
            request.setAttribute("topScorer", topScorer);
        }

        request.setAttribute("championships", championships);
        request.setAttribute("activeMenu", "rank");
        rd.forward(request, response);
    }

    private List<RankRowDTO> calculateRank(Championship championship, List<MatchesResultDTO> matches, TopScorerDTO topScorer) {
        List<RankRowDTO> rank = new ArrayList<>();
        List<Team> teams = new TeamDAO().getTeamsByChampionship(championship.getId());

        RankRowDTO row;
        Integer totalPoints, gf, ga, p, w, d, l;

        Integer topScorerPoints = 0;
        if (topScorer.getGoals() > 0) {
            topScorerPoints = 5;
        }

        for (Team team : teams) {
            totalPoints = gf = ga = p = w = d = l = 0;
            row = new RankRowDTO(team.getName(), team.getLogoUrl());
            Long teamId = team.getId();
            for (MatchesResultDTO match : matches) {
                if (!match.isFinished()) {
                    continue;
                }

                if (!match.getFirstTeamId().equals(teamId) && !match.getSecondTeamId().equals(teamId)) {
                    continue;
                }

                p++;

                Integer first = match.getFirstTeamGoals();
                Integer second = match.getSecondTeamGoals();
                if (match.getFirstTeamId().equals(team.getId())) {
                    gf += first;
                    ga += second;
                    if (first > second) {
                        w++;
                    } else if (second > first) {
                        l++;
                    } else {
                        d++;
                    }
                } else {
                    gf += second;
                    ga += first;

                    if (second > first) {
                        w++;
                    } else if (first > second) {
                        l++;
                    } else {
                        d++;
                    }
                }
            }
            
            row.setDrawn(d);
            row.setWon(w);
            row.setLost(l);
            row.setGoalsAgainst(ga);
            row.setGoalsFor(gf);
            row.setPlayed(p);

            Long gaPoints = Math.round(ga * 0.5);
            totalPoints = w * 3 + gf - gaPoints.intValue();
            if (topScorer.getTeamId().equals(team.getId())) {
                totalPoints += topScorerPoints;
            }

            row.setPoints(totalPoints);
            rank.add(row);
        }

        Collections.sort(rank);

        Integer pos = 1;
        for (RankRowDTO r : rank) {
            r.setPosition(pos++);
        }

        return rank;
    }

}
