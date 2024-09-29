package model;

public class Medalha {
    private Modalidade modalidade;
    private Equipe equipe;
    private String tipo; // Ouro, Prata ou Bronze

    // Construtor
    public Medalha(Modalidade modalidade, Equipe equipe, String tipo) {
        this.modalidade = modalidade;
        this.equipe = equipe;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+--------------------");
        System.out.println("| Modalidade: " + modalidade.getModalidade());
        System.out.println("| Equipe: " + equipe.getPais());
        System.out.println("| Medalha: " + tipo);
        System.out.println("+--------------------");
    }
}

