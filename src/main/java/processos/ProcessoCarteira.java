package processos;

import dao.DaoMovimentacao;

public class ProcessoCarteira extends ProcessoBase {

    @Override
    public void execute() {
        long idUser = this.getMessage().getAuthor().get().getId().asLong();
        DaoMovimentacao daoMovimentacao = new DaoMovimentacao();
        
        this.disparaMensagem("Você possui " + daoMovimentacao.getTotalCoinsCarteiraUser(idUser) + " coins na carteira e " + daoMovimentacao.getTotalCoinsBancoUser(idUser) + " no banco!");
    }

    @Override
    public String help() {
        return "Este comando mostra quantons coins você tem!";
    }

}