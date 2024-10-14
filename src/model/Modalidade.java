package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Modalidade {
    private int id;
    private String modalidade;
    private int numeroAtletas;

    // Construtor
    public Modalidade() {}
    
    public Modalidade(String modalidade, int numeroAtletas) {
        this.modalidade = modalidade;
        this.numeroAtletas = numeroAtletas;
    }
    
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

     public String getNumeroAtletas() {
        return modalidade;
    }

    public void setNumeroAtletas(int numeroAtletas) {
        this.numeroAtletas = numeroAtletas;
    }
    
    public boolean isColetivo() {
        return numeroAtletas > 1;
    }

    /* DATABASE */
    public void inserir() {
        MySQL db = new MySQL();
        
        try {
            db.addParametro("string", this.modalidade);
            db.addParametro("int", this.numeroAtletas);
            ResultSet rs = db.statement("INSERT INTO modalidade(modalidade, numeroAtletas) VALUES (?, ?)");
            
            if (rs.next()) {
				this.setId(rs.getInt(1));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static Modalidade buscaModalidade(int id) {
    	MySQL db = new MySQL();
    	Modalidade modalidade = new Modalidade();
    	try {
            db.addParametro("int", id);
            ResultSet rs = db.statement("SELECT id, modalidade, numeroAtletas FROM Modalidade WHERE id = ?");

			if (rs.next()) {
				modalidade.setId(rs.getInt("id"));
				modalidade.setModalidade(rs.getString("modalidade"));
                modalidade.setNumeroAtletas(rs.getInt("numeroAtletas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return modalidade;
    }
    
    public static ArrayList<Modalidade> listaTodasModalidades() {
    	ArrayList<Modalidade> modalidade = new ArrayList<Modalidade>();
    	MySQL db = new MySQL();
    	
    	try {
			ResultSet rs = db.statement("SELECT id, modalidade, numeroAtletas FROM Modalidade");
			while (rs.next()) {
				modalidade.add(new Modalidade(rs.getInt("id"), rs.getString("modalidade"), rs.getInt("numeroAtletas")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return modalidade;
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