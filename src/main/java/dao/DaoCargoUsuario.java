package dao;

import javax.persistence.Query;
import model.CargoUsuario;

public class DaoCargoUsuario extends Dao<CargoUsuario> {

    public DaoCargoUsuario() {
        super(CargoUsuario.class);
    }

    public CargoUsuario getFromIdUser(long idUser) {
        Query query = this.entityManager.createQuery("SELECT cargoUsuario"
                                                   + "  FROM CargoUsuario cargoUsuario"
                                                   + " WHERE cargoUsuario.idUser = :idUser");
        query.setParameter("idUser", idUser);
        if (query.getResultList().size() == 1) {
            return (CargoUsuario) query.getSingleResult();
        }
        else {
            return null;
        }
    }
    
}