/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.dao.util.GenericDAO;
import model.domain.User;

/**
 *
 * @author dalvo
 */
public class UserDAO extends GenericDAO<User, Long> {

    @Override
    public User get(Long key) throws SQLException {
        return connection.find(User.class, key);
    }

    @Override
    public List<User> getAll() throws SQLException {
        Query query = connection.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        Query query = connection.createQuery("SELECT u FROM User u WHERE u.login = :login");
        query.setParameter("login", login);

        try {
            User user = (User) query.getSingleResult();
            if (user != null && !user.getPasswordHash().equals(password)) {
                return null;
            }

            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
