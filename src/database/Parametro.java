package database;

public class Parametro {
    private String tipo;
    private Object valor;
    
    public Parametro(String tipo, Object valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getValor() {
        return valor;
    }
}
