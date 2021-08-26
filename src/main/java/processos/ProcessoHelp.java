package processos;

import factory.FactoryProcesso;
import org.reflections.Reflections;

public class ProcessoHelp extends ProcessoBase {

    @Override
    public void execute() {
        if (this.getComando().getArgumentos() == null) {
            this.disparaMensagem(this.getHelpGeral());
        }
        else {
            this.disparaMensagem(this.getHelpEspecifico());
        }
    }

    private String getHelpGeral() {
        String retorno = "Olá, eu sou a Gilga!\nExistem várias coisas que eu sei fazer, e aqui estão todas elas:\n\n\n";
        
        Reflections reflections = new Reflections(FactoryProcesso.PACOTE_PROCESSOS);
        
        for (Class clazz : reflections.getSubTypesOf(ProcessoBase.class)) {
            String comando = clazz.getName().substring(18).toLowerCase();
            try {
                Processo processo = FactoryProcesso.factoryProcesso(null, comando, null);
                retorno = retorno.concat("-> \"**do " + comando + "**\": " + processo.help() + "\n\n");
            }
            catch (ClassNotFoundException ex) {}
        }
        
        return retorno;
    }

    private String getHelpEspecifico() {
        String retorno;
        try {
            Processo processo = (Processo) Class.forName(FactoryProcesso.getNomeClasseProcesso(this.getComando().getArgumento(0))).newInstance();
            
            retorno = processo.help();
        }
        catch (ClassNotFoundException ex) {
            retorno = "Este comando não existe ou não foi encontrado. Use \"do help\" para ver a lista de comandos";
        }
        catch (InstantiationException | IllegalAccessException ex) {
            retorno = "Houve algum erro não esperado. Por favor, Contato a equipe de suporte (dica: não existe equipe de suporte).";
        }
        return retorno;
    }

}