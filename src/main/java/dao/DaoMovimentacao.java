package dao;

import javax.persistence.Query;
import model.Movimentacao;

public class DaoMovimentacao extends Dao<Movimentacao> {

    public DaoMovimentacao() {
        super(Movimentacao.class);
    }

    public double getTotalCoinsCarteiraUser(long idUser) {
        return this.getTotalCoinsUser(idUser, Movimentacao.TIPO_CARTEIRA);
    }

    public double getTotalCoinsBancoUser(long idUser) {
        return this.getTotalCoinsUser(idUser, Movimentacao.TIPO_BANCO);
    }

    public double getTotalCoinsUser(long idUser, int tipo) {
        Query query = this.entityManager.createQuery("SELECT sum(movimentacao.valor)"
                                                   + "  FROM Movimentacao movimentacao"
                                                   + " WHERE movimentacao.idUser = :idUser"
                                                   + "   AND movimentacao.tipo   = :tipo"
                                                   + " GROUP BY movimentacao.idUser");
        query.setParameter("idUser", idUser);
        query.setParameter("tipo",   tipo);
        if (query.getResultList().size() == 1) {
            return (Double) query.getSingleResult();
        }
        else {
            return 0;
        }
    }
    
}