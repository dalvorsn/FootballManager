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
import model.domain.Match;

/**
 *
 * @author luciano
 */
public class MatchDAO extends GenericDAO<Match, Long> {

    @Override
    public Match get(Long key) throws SQLException {
        return connection.find(Match.class, key);
    }

    @Override
    public List<Match> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT m FROM Match m");
        return query.getResultList();
    }
    
    public Long getCount() {
        TypedQuery<Long> query = connection.createQuery("SELECT COUNT(m) FROM Match m WHERE m.finished = TRUE", Long.class);
        return query.getSingleResult();
    }
    
    public List<Match> getLastMatches() throws SQLException {
        Query query = connection.createQuery("SELECT m FROM Match m WHERE m.finished = TRUE ORDER BY m.id DESC");
        query.setMaxResults(6);
        
        return query.getResultList();
    }
}
