/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.util;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author dalvo
 */
public abstract class GenericDAO<C, K> {

    protected EntityManager connection;

    public GenericDAO() {
        connection = Connection.getInstance().getConnection();
    }

    public C insert(C obj) throws SQLException {
        EntityTransaction transaction = connection.getTransaction();
        transaction.begin();

        connection.persist(obj);

        transaction.commit();
        return obj;
    }

    public C update(C obj) throws SQLException {
        EntityTransaction transaction = connection.getTransaction();
        transaction.begin();

        connection.merge(obj);

        transaction.commit();
        return obj;
    }

    public void delete(C obj) throws SQLException {
        EntityTransaction transaction = connection.getTransaction();
        transaction.begin();

        connection.remove(obj);

        transaction.commit();
    }

    public abstract C get(K key) throws SQLException;

    public abstract List<C> getAll() throws SQLException;
}
