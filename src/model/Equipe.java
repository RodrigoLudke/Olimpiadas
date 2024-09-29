package model;

import java.util.List;

public class Equipe {
    private int id;
    private String pais;
    private List<Atleta> atletas; // Lista de atletas da equipe

    // Construtor
    public Equipe(int id, String pais) {
        this.id = id;
        this.pais = pais;
        this.atletas = null;
    }
    
    public Equipe(int id, String pais, List<Atleta> atletas) {
        this.id = id;
        this.pais = pais;
        this.atletas = atletas;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    // Método para adicionar um atleta à equipe
    public void adicionarAtleta(Atleta atleta) {
        this.atletas.add(atleta);
    }

    // Método para remover um atleta da equipe
    public void removerAtleta(Atleta atleta) {
        this.atletas.remove(atleta);
    }
    
    // Método para exibir informações
    public void exibirInformacoes() {
	    System.out.println("+------------------");
	    System.out.println("| id: " + id);
	    System.out.println("| país: " + pais);
	    System.out.println("+------------------");
	
	    // Exibir informações dos atletas da equipe
	    for (Atleta atleta : atletas) {
	        atleta.exibirInformacoes();
	    }
	    
	    System.out.println();
	    
    }
}
