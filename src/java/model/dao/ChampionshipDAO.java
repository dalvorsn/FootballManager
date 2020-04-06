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
import model.domain.Championship;

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

}
