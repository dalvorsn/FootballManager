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
import model.domain.ChampionshipSubscribe;

/**
 *
 * @author luciano
 */
public class ChampionshipSubscribeDAO extends GenericDAO<ChampionshipSubscribe, Long> {

    @Override
    public ChampionshipSubscribe get(Long key) throws SQLException {
        return connection.find(ChampionshipSubscribe.class, key);
    }

    @Override
    public List<ChampionshipSubscribe> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT cs FROM ChampionshipSubscribe cs");
        return query.getResultList();
    }
    
    public List<ChampionshipSubscribe> getAllByChampionship(Long championshipId) throws SQLException {
        Query query = connection.createQuery("SELECT cs FROM ChampionshipSubscribe cs WHERE cs.championship.id = :championshipId");
        query.setParameter("championshipId", championshipId);
        return query.getResultList();
    }
}
