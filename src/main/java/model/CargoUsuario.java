package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CargoUsuario extends Model {

    @Id
    @GeneratedValue
    private int id;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EnumCargo cargo;
    
    @Column(nullable = false, unique = true)
    private long idUser;

    @Override
    public int getId() {
        return id;
    }

    public CargoUsuario setId(int id) {
        this.id = id;
        return this;
    }

    public EnumCargo getCargo() {
        return cargo;
    }

    public void setCargo(EnumCargo cargo) {
        this.cargo = cargo;
    }

    public long getIdUser() {
        return idUser;
    }

    public CargoUsuario setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }
    
}