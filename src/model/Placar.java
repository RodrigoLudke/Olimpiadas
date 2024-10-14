package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.MySQL;

public class Placar {
    private Equipe equipe1;
    private Equipe equipe2;
    private int placarEquipe1;
    private int placarEquipe2;

    // Construtor
    public Placar() {}
    
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
	
	/* DATABASE */
	public void inserir() {
	    MySQL db = new MySQL();

	    try {
	        if (this.equipe1.getIdModalidade() != this.equipe2.getIdModalidade()) {
	            throw new Exception("As equipes são de modalidades diferentes");
	        }

	        db.addParametro("int", this.equipe1.getId());
	        db.addParametro("int", this.equipe2.getId());
	        db.addParametro("int", this.placarEquipe1);
	        db.addParametro("int", this.placarEquipe2);

	        db.statement("INSERT INTO placar(idEquipe1, idEquipe2, placarEquipe1, placarEquipe2) VALUES (?, ?, ?, ?)");
	    } catch (SQLException e) {
	        //System.err.println("Erro ao inserir no banco: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        //System.err.println("Erro: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


    public static Placar buscaPlacar(Equipe equipe1, Equipe equipe2) {
    	MySQL db = new MySQL();
    	Placar placar = new Placar();
    	try {
    		db.addParametro("int", equipe1.getId());
            db.addParametro("int", equipe2.getId());
            ResultSet rs = db.statement("SELECT idEquipe1, idEquipe2, placarEquipe1, placarEquipe2 FROM placar WHERE idEquipe1 = ? AND idEquipe2 = ?");

			if (rs.next()) {
				placar.setEquipe1(Equipe.buscaEquipe(rs.getInt("idEquipe1")));
				placar.setEquipe2(Equipe.buscaEquipe(rs.getInt("idEquipe2")));
				placar.setPlacarEquipe1(rs.getInt("placarEquipe1"));
				placar.setPlacarEquipe2(rs.getInt("placarEquipe2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return placar;
    }
    
    public static ArrayList<Placar> listaTodosPlacares() {
    	ArrayList<Placar> placares = new ArrayList<Placar>();
    	MySQL db = new MySQL();
    	
    	try {
			ResultSet rs = db.statement("SELECT idEquipe1, idEquipe2, placarEquipe1, placarEquipe2 FROM placar");
			while (rs.next()) {
				placares.add(
					new Placar(
						Equipe.buscaEquipe(rs.getInt("idEquipe1")),
						Equipe.buscaEquipe(rs.getInt("idEquipe2")),
						rs.getInt("placarEquipe1"),
						rs.getInt("placarEquipe2")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return placares;
    }
    
    public static ArrayList<Placar> listaTodosPlacaresEquipe(Equipe equipe) {
    	ArrayList<Placar> placares = new ArrayList<Placar>();
    	MySQL db = new MySQL();
    	
    	try {
    		db.addParametro("int", equipe.getId());
			ResultSet rs = db.statement("SELECT idEquipe1, idEquipe2, placarEquipe1, placarEquipe2 FROM placar WHERE ? IN (idEquipe1, idEquipe2)");
			while (rs.next()) {
				placares.add(
					new Placar(
						Equipe.buscaEquipe(rs.getInt("idEquipe1")),
						Equipe.buscaEquipe(rs.getInt("idEquipe2")),
						rs.getInt("placarEquipe1"),
						rs.getInt("placarEquipe2")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return placares;
    }

    public static ArrayList<Placar> listaTodosPlacaresModalidade(int idModalidade) {
    	ArrayList<Placar> placares = new ArrayList<Placar>();
    	MySQL db = new MySQL();
    	
    	try {
    		db.addParametro("int", idModalidade);
			ResultSet rs = db.statement("SELECT p.idEquipe1, p.idEquipe2, p.placarEquipe1, p.placarEquipe2 FROM placar p JOIN equipe e1 ON e1.id = p.idEquipe1 JOIN equipe e2 ON e2.id = p.idEquipe2 WHERE e1.idModalidade = e2.idModalidade AND e1.idModalidade = ?");
			while (rs.next()) {
				placares.add(
					new Placar(
						Equipe.buscaEquipe(rs.getInt("idEquipe1")),
						Equipe.buscaEquipe(rs.getInt("idEquipe2")),
						rs.getInt("placarEquipe1"),
						rs.getInt("placarEquipe2")
					)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return placares;
    }
    
	
    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("+--------------------");
        System.out.println("| " + equipe1.getPais() + " x " + equipe2.getPais());
        System.out.println("| Placar: " + placarEquipe1 + " x " + placarEquipe2);
        System.out.println("+--------------------");
    }
}

