/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;
import model.dao.util.GenericDAO;
import model.domain.Team;

/**
 *
 * @author Luciano
 */
public class TeamDAO extends GenericDAO<Team, Long> {

    @Override
    public Team get(Long key) throws SQLException {
        return connection.find(Team.class, key);
    }

    @Override
    public List<Team> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT t FROM Team t");
        return query.getResultList();
    }
    
    public List<Team> getTeamsByUser(Long userId) {
        Query query = connection.createQuery("SELECT t FROM Team t WHERE t.owner.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    public List<Team> getTeamsByChampionship(Long championshipId) {
        Query query = connection.createQuery(
        "SELECT t FROM Championship c "
            + "JOIN c.subscribes s "
            + "JOIN s.team t "
        + "WHERE c.id = :championshipId");
        
        query.setParameter("championshipId", championshipId);
        return query.getResultList();
    }

}
