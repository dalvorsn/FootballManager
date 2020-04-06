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
import model.domain.Goal;

/**
 *
 * @author dalvo
 */
public class GoalDAO extends GenericDAO<Goal, Long> {

    @Override
    public Goal get(Long key) throws SQLException {
        return connection.find(Goal.class, key);
    }

    @Override
    public List<Goal> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT g FROM Goal g");
        return query.getResultList();
    }

}
