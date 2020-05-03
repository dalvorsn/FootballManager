/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.dao.util.GenericDAO;
import model.domain.Championship;
import model.dto.ChampionshipDTO;
import model.dto.ChampionshipSelectDTO;
import model.dto.TopScorerDTO;

/**
 *
 * @author dalvo
 */
public class ChampionshipDAO extends GenericDAO<Championship, Long> {

    @Override
    public Championship get(Long key) throws SQLException {
        return connection.find(Championship.class, key);
    }

    @Override
    public List<Championship> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT c FROM Championship c");
        return query.getResultList();
    }
    
    public List<ChampionshipDTO> getAllView() throws SQLException {
        
        Query query = connection.createQuery(
        "SELECT NEW model.dto.ChampionshipDTO(c.id, c.name, COUNT(s), c.startDate, c.maxSubscribers) "
            + "FROM Championship c LEFT JOIN c.subscribes s "
        + "GROUP BY c.id");
        
        return query.getResultList();
    }
    
    public boolean isTeamSubscribed(Long teamId, Long championshipId) {
        TypedQuery<Long> query = connection.createQuery(
        "SELECT COUNT(cs)  FROM ChampionshipSubscribe cs "
            + "INNER JOIN cs.championship c "
        + " WHERE cs.team.id = :teamId AND c.id = :championshipId ", Long.class);
        
        query.setParameter("teamId", teamId);
        query.setParameter("championshipId", championshipId);
        
        Long count = query.getSingleResult();
        return count > 0;
    }
    
    public List<ChampionshipSelectDTO> getAllStarted() {
        TypedQuery<ChampionshipSelectDTO> query = connection.createQuery(
            "SELECT NEW model.dto.ChampionshipSelectDTO( c.id, c.name ) FROM Championship c "
        + "WHERE  c.startDate IS NOT NULL ", ChampionshipSelectDTO.class);
        
        return query.getResultList();
    }
    
    public TopScorerDTO getTopScorer(Long championshipId) {
        TypedQuery<TopScorerDTO> query = connection.createQuery(
        "SELECT NEW model.dto.TopScorerDTO( p.id, p.name, t.id, t.name, t.logoUrl, COUNT(g) ) "
        + "FROM Championship c "
            + "JOIN c.matches m "
            + "JOIN m.goals g "
            + "JOIN g.player p "
            + "JOIN p.team t "
        + "WHERE c.id = :championshipId "
        + "GROUP BY p.id "
        + "ORDER BY COUNT(g) DESC "
        , TopScorerDTO.class);
        
        query.setParameter("championshipId", championshipId);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
