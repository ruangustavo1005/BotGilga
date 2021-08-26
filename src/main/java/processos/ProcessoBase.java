package processos;

import model.Comando;
import discord4j.core.object.entity.Message;

abstract public class ProcessoBase implements Processo {

    private Comando comando;
    private Message message;

    public Comando getComando() {
        if (this.comando == null) {
            this.comando = new Comando();
        }
        return comando;
    } 

    public ProcessoBase setComando(Comando comando) {
        this.comando = comando;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public ProcessoBase setMessage(Message message) {
        this.message = message;
        return this;
    }
    
    protected void disparaMensagem(String mensagem) {
        this.getMessage().getChannel().block().createMessage(mensagem).block();
    }
    
    @Override
    public boolean podeExecutar() {
        return true;
    }

    @Override
    public String help() {
        return "Não foi definido um help para este comando!";
    }
    
    @Override
    public String toString() {
        return "Comando: " + this.getComando().toString();
    }
    
}