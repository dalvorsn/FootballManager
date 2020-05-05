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
import model.domain.Player;

/**
 *
 * @author Luciano
 */
public class PlayerDAO extends GenericDAO<Player, Long> {

    @Override
    public Player get(Long key) throws SQLException {
        return connection.find(Player.class, key);
    }

    @Override
    public List<Player> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT p FROM Player p");
        return query.getResultList();
    }
    
    public List<Player> getAllAvailable() throws SQLException {
        Query query = connection.createQuery("SELECT p FROM Player p WHERE p.team IS NULL");
        return query.getResultList();
    }
    
    public List<Player> getAllByTeam(Long teamId) throws SQLException {
        Query query = connection.createQuery("SELECT p FROM Player p WHERE p.team.id = :teamId");
        query.setParameter("teamId", teamId);
        return query.getResultList();
    }
    public Long getCount() {
        TypedQuery<Long> query = connection.createQuery("SELECT COUNT(p) FROM Player p", Long.class);
        return query.getSingleResult();
    }
}
