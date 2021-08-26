package processos;

public class ProcessoTeste extends ProcessoBase {

    @Override
    public void execute() {
        if (this.getComando().getArgumentos() != null) {
            this.disparaMensagem("Testado (" + this.getComando().getArgumentosAsString(", ") + ")!");
        }
        else {
            this.disparaMensagem("Testado!");
        }
    }

    @Override
    public String help() {
        return "Este comando serve para testar a conexão do seu servidor comigo. Podem ser passado parâmetros, mas eles não serão utilizados, apenas apresentados.";
    }
    
}