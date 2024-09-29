package model;

public class Placar {
    private Equipe equipe1;
    private Equipe equipe2;
    private int placarEquipe1;
    private int placarEquipe2;

    // Construtor
    public Placar(Equipe equipe1, Equipe equipe2, int placarEquipe1, int placarEquipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.placarEquipe1 = placarEquipe1;
        this.placarEquipe2 = placarEquipe2;
    }

    // Getters e Setters
	public Equipe getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(Equipe equipe1) {
		this.equipe1 = equipe1;
	}

	public Equipe getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(Equipe equipe2) {
		this.equipe2 = equipe2;
	}

	public int getPlacarEquipe1() {
		return placarEquipe1;
	}

	public void setPlacarEquipe1(int placarEquipe1) {
		this.placarEquipe1 = placarEquipe1;
	}

	public int getPlacarEquipe2() {
		return placarEquipe2;
	}

	public void setPlacarEquipe2(int placarEquipe2) {
		this.placarEquipe2 = placarEquipe2;
	}
	
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+--------------------");
        System.out.println("| " + equipe1.getPais() + " x " + equipe2.getPais());
        System.out.println("| Placar: " + placarEquipe1 + " x " + placarEquipe2);
        System.out.println("+--------------------");
    }
}

