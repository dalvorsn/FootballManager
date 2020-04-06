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
import model.domain.Player;

/**
 *
 * @author dalvo
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

}
