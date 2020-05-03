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
}
