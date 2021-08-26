package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Connection {

    private static Connection instance;

    private final EntityManager entityManager;
    
    private Connection() {
        this.entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    public static Connection getInstance() {
        if (Connection.instance == null) {
            Connection.instance = new Connection();
        }
        return Connection.instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}