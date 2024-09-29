package model;

public class Modalidade {
    private int id;
    private String modalidade;
    private boolean coletivo;

    // Construtor
    public Modalidade(int id, String modalidade, boolean coletivo) {
        this.id = id;
        this.modalidade = modalidade;
        this.coletivo = coletivo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public boolean isColetivo() {
        return coletivo;
    }

    public void setColetivo(boolean coletivo) {
        this.coletivo = coletivo;
    }
    
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+--------------------");
        System.out.println("| id: " + id);
        System.out.println("| modalidade: " + modalidade);
        System.out.println("| coletivo: " + coletivo);
        System.out.println("+--------------------");
    }
}