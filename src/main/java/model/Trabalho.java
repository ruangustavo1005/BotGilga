package model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Trabalho extends Model {

    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false)
    private long idUser;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idmovimentacao")
    private Movimentacao movimentacao;

    @Override
    public int getId() {
        return id;
    }

    public Trabalho setId(int id) {
        this.id = id;
        return this;
    }

    public long getIdUser() {
        return idUser;
    }

    public Trabalho setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Trabalho setData(Date data) {
        this.data = data;
        return this;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public Trabalho setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
        return this;
    }
    
}