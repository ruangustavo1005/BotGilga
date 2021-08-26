package processos;

import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;

public class ProcessoBomdia extends ProcessoBase {

    @Override
    public void execute() {
        this.disparaMensagem("Bom dia, " + this.getMencao() + "!");
    }

    private String getMencao() {
        String retorno;
        if (this.getComando().getArgumentos() != null) {
            retorno = this.getComando().getArgumento(0);
            
            Guild guild = this.getMessage().getGuild().block();
            
            Member member = guild.searchMembers(retorno, guild.getMemberCount()).blockFirst();
            
            if (member != null) {
                retorno = member.getMention();
            }
        }
        else {
            retorno = this.getMessage().getAuthor().get().getMention();
        }
        return retorno;
    }

    @Override
    public String help() {
        return "Este comando te deseja um bom dia! Caso queira dar um bom dia para um colega, use \"do bomdia <apelidoDoUsuario>\"";
    }
    
}