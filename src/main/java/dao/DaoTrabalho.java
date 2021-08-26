package dao;

import javax.persistence.Query;
import model.Trabalho;

public class DaoTrabalho extends Dao<Trabalho> {

    public DaoTrabalho() {
        super(Trabalho.class);
    }

    public Trabalho getUltimoTrabalho(long idUser) {
        Query query = this.entityManager.createQuery("SELECT trabalho"
                                                   + "  FROM Trabalho trabalho"
                                                   + " WHERE trabalho.idUser = :idUser"
                                                   + " ORDER BY trabalho.data DESC");
        query.setParameter("idUser", idUser);
        if (query.getResultList().size() == 1) {
            return (Trabalho) query.getSingleResult();
        }
        else {
            return null;
        }
    }
    
}