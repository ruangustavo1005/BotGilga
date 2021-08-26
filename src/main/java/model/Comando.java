package model;

public class Comando {

    public static final String CHAVE = "do";
    
    private String comando;
    private String[] argumentos;

    public Comando() {
        
    }

    public Comando(String comando, String[] argumentos) {
        this.comando = comando;
        this.argumentos = argumentos;
    }

    public String getComando() {
        return comando;
    }

    public Comando setComando(String comando) {
        this.comando = comando;
        return this;
    }

    public String[] getArgumentos() {
        return argumentos;
    }

    public Comando setArgumentos(String[] argumentos) {
        this.argumentos = argumentos;
        return this;
    }
    
    public String getArgumento(int i) {
        return argumentos[i];
    }
    
    public String getArgumentosAsString() {
        return this.getArgumentosAsString(" ");
    }
    
    public String getArgumentosAsString(String separador) {
        String retorno = "";
        for (int i = 0; i < this.getArgumentos().length - 1; i++) {
            retorno = retorno.concat(this.getArgumentos()[i] + separador);
        }
        retorno = retorno.concat(this.getArgumentos()[this.getArgumentos().length - 1]);
        return retorno;
    }

    @Override
    public String toString() {
        return CHAVE + " " + this.getComando() + " " + getArgumentosAsString();
    }
    
}