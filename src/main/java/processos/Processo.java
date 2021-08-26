package processos;

public interface Processo {
    
    public void execute();
    
    public boolean podeExecutar();
    
    public String help();
    
}