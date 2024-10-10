package model;

public class Modalidade {
    private int id;
    private String modalidade;
    private int numeroAtletas;

    // Construtor
    public Modalidade(int id, String modalidade, int numeroAtletas) {
        this.id = id;
        this.modalidade = modalidade;
        this.numeroAtletas = numeroAtletas;
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
        return numeroAtletas > 1;
    }

    public void setColetivo(int numeroAtletas) {
        this.numeroAtletas = numeroAtletas;
    }
    
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+--------------------");
        System.out.println("| id: " + id);
        System.out.println("| modalidade: " + modalidade);
        System.out.println("| numero de atletas: " + numeroAtletas);
        System.out.println("+--------------------");
    }
}