package factory;

import discord4j.core.object.entity.Message;
import java.util.logging.Level;
import java.util.logging.Logger;
import processos.Processo;
import processos.ProcessoBase;

public class FactoryProcesso {

    public static final String PACOTE_PROCESSOS = "processos";
    public static final String PREFIXO_CLASSE_PROCESSO = "Processo";
    
    public static Processo factoryProcesso(Message message, String comando, String[] argumentos) throws ClassNotFoundException {
        try {
            ProcessoBase processoBase = (ProcessoBase) Class.forName(getNomeClasseProcesso(comando)).newInstance();
            
            processoBase.setMessage(message)
                    .getComando()
                    .setComando(comando)
                    .setArgumentos(argumentos);
            
            return processoBase;
        }
        catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryProcesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getNomeClasseProcesso(String comando) {
        return PACOTE_PROCESSOS + "." + PREFIXO_CLASSE_PROCESSO + comando.substring(0, 1).toUpperCase() + comando.substring(1);
    }
    
}