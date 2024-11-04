package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Equipe {
    private int id;
    private String pais;
    private Modalidade modalidade;
    private ArrayList<Atleta> atletas; // Lista de atletas da equipe

    // Construtor
    public Equipe() {}

    public Equipe(String pais, Modalidade modalidade) {
    	this.pais = pais;
        this.modalidade = modalidade;
        this.atletas = new ArrayList<Atleta>();
    }
    
    public Equipe(int id, String pais, Modalidade modalidade) {
        this.id = id;
        this.pais = pais;
        this.modalidade = modalidade;
        this.atletas = new ArrayList<Atleta>();
    }
    
    public Equipe(int id, String pais, Modalidade modalidade, ArrayList<Atleta> atletas) {
        this.id = id;
        this.pais = pais;
        this.modalidade = modalidade;
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

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
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
            db.addParametro("int", this.modalidade.getId());
            ResultSet rs = db.statement("INSERT INTO equipe(pais, idModalidade) VALUES (?, ?)");
            
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
            ResultSet rs = db.statement("SELECT id, pais, idModalidade FROM equipe WHERE id = ?");

			if (rs.next()) {
				equipe.setId(rs.getInt("id"));
				equipe.setPais(rs.getString("pais"));
                equipe.setModalidade(Modalidade.buscaModalidade(rs.getInt("idModalidade")));
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
			ResultSet rs = db.statement("SELECT id, pais, idModalidade FROM equipe");
			while (rs.next()) {
				Equipe temp = new Equipe(rs.getInt("id"), rs.getString("pais"), Modalidade.buscaModalidade(rs.getInt("idModalidade")));
				temp.setAtletas(Atleta.listaTodosAtletasEquipe(temp.id));
				
				equipe.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return equipe;
    }

    public static ArrayList<Equipe> listaTodasEquipesModaliade(int idModalidade) {
    	ArrayList<Equipe> equipe = new ArrayList<Equipe>();
    	MySQL db = new MySQL();
    	
    	try {
            db.addParametro("int", idModalidade);
			ResultSet rs = db.statement("SELECT id, pais, idModalidade FROM equipe WHERE idModalidade = ?");
			while (rs.next()) {
				Equipe temp = new Equipe(rs.getInt("id"), rs.getString("pais"), Modalidade.buscaModalidade(rs.getInt("idModalidade")));
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
