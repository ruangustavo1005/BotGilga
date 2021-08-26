package processos;

import dao.DaoMovimentacao;
import model.Movimentacao;
import utils.DateUtils;

public class ProcessoDeposito extends ProcessoBase {

    @Override
    public void execute() {
        if (this.getComando().getArgumentos() == null) {
            this.disparaMensagem("Você não informou o valor a ser sacado ou depositado!");
        }
        else {
            try {
                double valor = Double.valueOf(this.getComando().getArgumento(0).replace(",", "."));
                
                if (valor > 0) {
                    this.deposito(valor);
                }
                else if (valor < 0) {
                    this.saque(valor);
                }
                else {
                    this.disparaMensagem("Informe um valor válido!");
                }
            }
            catch (NumberFormatException ex) {
                this.disparaMensagem("Informe um valor válido!");
            }
        }
    }

    private void deposito(double valor) {
        long idUser = this.getMessage().getAuthor().get().getId().asLong();
        DaoMovimentacao daoMovimentacao = new DaoMovimentacao();
        double totalCoinsCarteiraUser = daoMovimentacao.getTotalCoinsCarteiraUser(idUser);
        
        if (valor > totalCoinsCarteiraUser) {
            this.disparaMensagem("Não é possível efetuar o depósito de " + valor + " coins pois você possui apenas " + totalCoinsCarteiraUser + " coins na carteira!");
        }
        else {
            Movimentacao movimentacaoBanco = new Movimentacao();
            movimentacaoBanco.setData(DateUtils.now()).setIdUser(idUser).setTipo(Movimentacao.TIPO_BANCO).setValor(valor);
            
            Movimentacao movimentacaoCarteira = new Movimentacao();
            movimentacaoCarteira.setData(DateUtils.now()).setIdUser(idUser).setTipo(Movimentacao.TIPO_CARTEIRA).setValor(-valor);
            
            if (daoMovimentacao.add(movimentacaoBanco) && daoMovimentacao.add(movimentacaoCarteira)) {
                this.disparaMensagem("Depósito de " + valor + " coins efetuado com sucesso!");
            }
            else {
                this.disparaMensagem("Houve um erro ao depositar. Contate o suporte.");
            }
        }
    }

    private void saque(double valor) {
        valor = Math.abs(valor);
        
        long idUser = this.getMessage().getAuthor().get().getId().asLong();
        DaoMovimentacao daoMovimentacao = new DaoMovimentacao();
        double totalCoinsBancoUser = daoMovimentacao.getTotalCoinsBancoUser(idUser);
        
        if (valor > totalCoinsBancoUser) {
            this.disparaMensagem("Não é possível efetuar o saque de " + valor + " coins pois você possui apenas " + totalCoinsBancoUser + " coins no banco!");
        }
        else {
            Movimentacao movimentacaoCarteira = new Movimentacao();
            movimentacaoCarteira.setData(DateUtils.now()).setIdUser(idUser).setTipo(Movimentacao.TIPO_CARTEIRA).setValor(valor);
            
            Movimentacao movimentacaoBanco = new Movimentacao();
            movimentacaoBanco.setData(DateUtils.now()).setIdUser(idUser).setTipo(Movimentacao.TIPO_BANCO).setValor(-valor);
            
            if (daoMovimentacao.add(movimentacaoCarteira) && daoMovimentacao.add(movimentacaoBanco)) {
                this.disparaMensagem("Saque de " + valor + " coins efetuado com sucesso!");
            }
            else {
                this.disparaMensagem("Houve um erro ao sacar. Contate o suporte.");
            }
        }
    }
    
    @Override
    public String help() {
        return "Este comando é usado para guardar seu dinheiro no banco, ou para sacar ele do banco! Use \"**do deposito <valor>**\" com valores positivos para depositar e com valores negativos para sacar!";
    }
    
}