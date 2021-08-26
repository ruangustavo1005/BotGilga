package dao;

import javax.persistence.EntityManager;

public class Dao<Type> {
    
    protected final EntityManager entityManager;

    private final Class<Type> classe;

    public Dao(Class<Type> classe) {
        this.classe        = classe;
        this.entityManager = Connection.getInstance().getEntityManager();
    }
    
    public boolean add(Type object) {
        boolean retorno = true;
        try {
            this.begin();
            this.entityManager.persist(object);
            this.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            retorno = false;
            if (this.entityManager.getTransaction().isActive()) {
                this.rollback();
            }
        }
        return retorno;
    }

    public boolean remove(Type object) {
        boolean retorno = true;
        try {
            this.begin();
            this.entityManager.remove(object);
            this.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            retorno = false;
            if (this.entityManager.getTransaction().isActive()) {
                this.rollback();
            }
        }
        return retorno;
    }
    
    protected final void begin() {
        this.entityManager.getTransaction().begin();
    }

    protected final void commit() {
        if (this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().commit();
        }
    }

    protected final void rollback() {
        this.entityManager.getTransaction().rollback();
    }
    
}