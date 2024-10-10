package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Atleta {
    private int id;
    private String nome;

    // Construtor
    public Atleta() {}
    
    public Atleta(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
    public static Atleta buscaAtleta(int id)
    {
    	MySQL db = new MySQL();
    	Atleta a = new Atleta();
    	try {
			PreparedStatement pstmt = db.conn.prepareStatement("SELECT id, nome FROM atleta WHERE id = ?");
			pstmt.setInt(1, id);

			ResultSet rs;
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				 a.setId(rs.getInt("id"));
				 a.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return a;
    }
    
    public static ArrayList<Atleta> listaAtletas()
    {
    	ArrayList<Atleta> atletas = new ArrayList<Atleta>();
    	MySQL db = new MySQL();
    	
    	try {
			ResultSet rs = db.statement("SELECT id, nome FROM atleta", null);
			
			while (rs.next()) {
				 atletas.add(new Atleta(rs.getInt("id"), rs.getString("nome")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
