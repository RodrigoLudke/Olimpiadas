package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Atleta {
    private int id;
    private String nome;
    private int idEquipe;

    // Construtor
    public Atleta() {}

    public Atleta(String nome) {
        this.nome = nome;
    }
    
    public Atleta(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Atleta(String nome, int idEquipe) {
        this.nome = nome;
        this.idEquipe = idEquipe;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    
    /* DATABASE */
    public void inserir() {
        MySQL db = new MySQL();
        
        try {
            db.addParametro("string", this.nome);
            db.addParametro("int", this.idEquipe);
            db.statement("INSERT INTO atleta(nome, idEquipe) VALUES (?, ?)");		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static Atleta buscaAtleta(int id) {
    	MySQL db = new MySQL();
    	Atleta atleta = new Atleta();
    	try {
            db.addParametro("int", id);
            ResultSet rs = db.statement("SELECT id, nome FROM atleta WHERE id = ?");

			if (rs.next()) {
				atleta.setId(rs.getInt("id"));
				atleta.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return atleta;
    }
    
    public static ArrayList<Atleta> listaTodosAtletas() {
    	ArrayList<Atleta> atletas = new ArrayList<Atleta>();
    	MySQL db = new MySQL();
    	
    	try {
			ResultSet rs = db.statement("SELECT id, nome FROM atleta");
			while (rs.next()) {
				atletas.add(new Atleta(rs.getInt("id"), rs.getString("nome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return atletas;
    }
    
    public static ArrayList<Atleta> listaTodosAtletasEquipe(int idEquipe) {
    	ArrayList<Atleta> atletas = new ArrayList<Atleta>();
    	MySQL db = new MySQL();
    	
    	try {
    		db.addParametro("int", idEquipe);
    		ResultSet rs = db.statement("SELECT id, nome FROM atleta WHERE idEquipe = ?");
			while (rs.next()) {
				atletas.add(new Atleta(rs.getInt("id"), rs.getString("nome")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return atletas;
    }
    
    
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+---------------");
        System.out.println("| id: " + id);
        System.out.println("| nome: " + nome);
        System.out.println("+---------------");
    }
}
