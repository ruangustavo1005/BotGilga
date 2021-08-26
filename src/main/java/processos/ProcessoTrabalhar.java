package processos;

import dao.Dao;
import dao.DaoCargoUsuario;
import dao.DaoTrabalho;
import model.CargoUsuario;
import model.EnumCargo;
import model.Movimentacao;
import model.Trabalho;
import utils.DateUtils;

public class ProcessoTrabalhar extends ProcessoBase {

    @Override
    public void execute() {
        if (this.getComando().getArgumentos() == null) {
            this.trabalhar();
        }
        else {
            this.emprega();
        }
    }
    
    private void trabalhar() {
        DaoCargoUsuario daoCargoUsuario = new DaoCargoUsuario();
        long idUser = this.getMessage().getAuthor().get().getId().asLong();
        CargoUsuario cargoUsuario = daoCargoUsuario.getFromIdUser(idUser);

        if (cargoUsuario != null) {
            int descansoRestante = this.descansoRestante(cargoUsuario.getCargo(), idUser);

            if (descansoRestante == 0) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setData(DateUtils.now())
                            .setIdUser(idUser)
                            .setTipo(Movimentacao.TIPO_CARTEIRA)
                            .setValor(cargoUsuario.getCargo().remuneracao());

                Dao<Movimentacao> daoMovimentacao = new Dao<>(Movimentacao.class);

                if (daoMovimentacao.add(movimentacao)) {
                    Trabalho trabalho = new Trabalho();
                    trabalho.setData(DateUtils.now()).setIdUser(idUser).setMovimentacao(movimentacao);

                    DaoTrabalho daoTrabalho = new DaoTrabalho();

                    if (daoTrabalho.add(trabalho)) {
                        this.disparaMensagem("Bom trabalho! Você ganhou " + movimentacao.getValor() + " coins!");
                    }
                    else {
                        this.disparaMensagem("Houve um erro ao trabalhar. Contate o suporte.");
                    }
                }
                else {
                    this.disparaMensagem("Houve um erro ao trabalhar. Contate o suporte.");
                }
            }
            else {
                this.disparaMensagem("Você ainda não pode trabalhar de novo, descanse mais " + descansoRestante + " segundos!");
            }
        }
        else {
            this.disparaMensagem("Você ainda não tem um cargo! Use \"**do trabalhar <nomeDoCargo>**\" para escolher um cargo!");
        }
    }
    
    private void emprega() {
        String nomeCargo = this.getComando().getArgumento(0);
        
        try {
            long idUser = this.getMessage().getAuthor().get().getId().asLong();
            EnumCargo cargo = EnumCargo.valueOf(nomeCargo.toUpperCase());
            
            CargoUsuario cargoUsuario = new CargoUsuario();
            cargoUsuario.setIdUser(idUser)
                        .setCargo(cargo);
            
            DaoCargoUsuario daoCargoUsuario = new DaoCargoUsuario();
            
            CargoUsuario cargoAtualUsuario = daoCargoUsuario.getFromIdUser(idUser);
            
            if (cargoAtualUsuario != null) {
                if (!daoCargoUsuario.remove(cargoAtualUsuario)) {
                    this.disparaMensagem("Houve um erro ao trabalhar. Contate o suporte.");
                }
            }
            
            if (daoCargoUsuario.add(cargoUsuario)) {
                this.disparaMensagem("Parabéns pelo cargo de " + cargo.nome() + "! Use \"**do trabalhar**\" para começar!");
            }
            else {
                this.disparaMensagem("Houve um erro ao trabalhar. Contate o suporte.");
            }
        }
        catch (IllegalArgumentException ex) {
            this.disparaMensagem("O cargo \"" + nomeCargo + "\" não existe!");
        }
    }
    
    private int descansoRestante(EnumCargo enumCargo, long idUser) {
        int retorno = 0;
        
        DaoTrabalho daoTrabalho = new DaoTrabalho();
        Trabalho ultimoTrabalho = daoTrabalho.getUltimoTrabalho(idUser);
        
        if (ultimoTrabalho != null) {
            long tempoUltimoTrabalho = DateUtils.dateToLong(ultimoTrabalho.getData());
            long tempoAtual          = DateUtils.dateToLong(DateUtils.now());
            long tempoDescansado     = tempoAtual - tempoUltimoTrabalho;
            
            if (tempoDescansado >= enumCargo.tempoDescanso()) {
                retorno = 0;
            }
            else {
                retorno = (int) (enumCargo.tempoDescanso() - tempoDescansado);
            }
        }
        
        return retorno;
    }

    @Override
    public String help() {
        String retorno = "Este comando permite que você consiga um trabalho e faça dinheiro trabalhando com ele! Use \"**do trabalhar <nomeDoCargo>**\" para conseguir um trabalho e \"**do trabalhar**\" para começar a trabalhar!\n";
        
        retorno = retorno.concat("Os cargos disponíveis são:");
        
        for (EnumCargo cargo : EnumCargo.values()) {
            retorno = retorno.concat("\n" + cargo.id() + " - \"" + cargo.nome() + "\" (" + cargo.name() + "), tempo de descanso necessário (em segundos): " + cargo.tempoDescanso() + ", Remuneração: " + cargo.remuneracao() + "coins");
        }
        
        return retorno;
    }
    
}