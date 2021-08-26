package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao extends Model {

    static public final int TIPO_CARTEIRA = 1,
                            TIPO_BANCO    = 2;
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    @Column(nullable = false)
    private int tipo;
    
    @Column(nullable = false)
    private double valor;
    
    @Column(nullable = false)
    private long idUser;

    @Override
    public int getId() {
        return id;
    }

    public Movimentacao setId(int id) {
        this.id = id;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Movimentacao setData(Date data) {
        this.data = data;
        return this;
    }

    public int getTipo() {
        return tipo;
    }

    public Movimentacao setTipo(int tipo) {
        this.tipo = tipo;
        return this;
    }

    public double getValor() {
        return valor;
    }

    public Movimentacao setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public long getIdUser() {
        return idUser;
    }

    public Movimentacao setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }
    
}