/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author dalvo
 */
public class Connection {

    private static Connection instance;
    protected EntityManager connection;

    private Connection() {
        connection = Persistence.createEntityManagerFactory("FootballManagerPU").createEntityManager();
    }

    public EntityManager getConnection() {
        return connection;
    }

    public static synchronized Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }

        return instance;
    }
}
