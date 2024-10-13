package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Equipe {
    private int id;
    private String pais;
    private ArrayList<Atleta> atletas; // Lista de atletas da equipe

    // Construtor
    public Equipe() {
        this.atletas = new ArrayList<Atleta>();
    }

    public Equipe( String pais) {
    	this.pais = pais;
        this.atletas = new ArrayList<Atleta>();
    }
    
    public Equipe(int id, String pais) {
        this.id = id;
        this.pais = pais;
        this.atletas = new ArrayList<Atleta>();
    }
    
    public Equipe(int id, String pais, ArrayList<Atleta> atletas) {
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

    public ArrayList<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(ArrayList<Atleta> atletas) {
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
    
    /* DATABASE */
    public void inserir() {
        MySQL db = new MySQL();
        
        try {
            db.addParametro("string", this.pais);
            ResultSet rs = db.statement("INSERT INTO equipe(pais) VALUES (?)");
            
            if (rs.next()) {
				this.setId(rs.getInt(1));
            }
            
            for (Atleta atleta : this.atletas) {
            	atleta.setIdEquipe(this.id);
    		    atleta.inserir();
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static Equipe buscaEquipe(int id) {
    	MySQL db = new MySQL();
    	Equipe equipe = new Equipe();
    	try {
            db.addParametro("int", id);
            ResultSet rs = db.statement("SELECT id, pais FROM equipe WHERE id = ?");

			if (rs.next()) {
				equipe.setId(rs.getInt("id"));
				equipe.setPais(rs.getString("pais"));
				equipe.setAtletas(Atleta.listaTodosAtletasEquipe(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return equipe;
    }
    
    public static ArrayList<Equipe> listaTodasEquipes() {
    	ArrayList<Equipe> equipe = new ArrayList<Equipe>();
    	MySQL db = new MySQL();
    	
    	try {
			ResultSet rs = db.statement("SELECT id, pais FROM equipe");
			while (rs.next()) {
				Equipe temp = new Equipe(rs.getInt("id"), rs.getString("pais"));
				temp.setAtletas(Atleta.listaTodosAtletasEquipe(temp.id));
				
				equipe.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return equipe;
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
