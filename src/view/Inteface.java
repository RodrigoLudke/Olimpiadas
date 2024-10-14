package view;

import java.util.ArrayList;

import model.Atleta;
import model.Equipe;
import model.Medalha;
import model.Modalidade;
import model.Placar;

public interface Inteface {
	
	public static void main(String[] args) {
		/*
        // Criando medalhas - Sem comunicação com o banco
        Medalha medalhaOuroVolei = new Medalha(volei, equipeBrasil, "Ouro");
        Medalha medalhaPrataTenisMesa = new Medalha(tenisMesa, equipeChinaTenisMesa, "Prata");
        Medalha medalhaBronzeEsgrima = new Medalha(esgrima, equipeRussiaEsgrima, "Bronze");
		*/
		
		/*
		
		//Criando uma modalidade
		Modalidade m1 = new Modalidade("Basquete", 6);
		m1.inserir();
		
		//Retornando as informações de 1 Equipe
		Modalidade m2 = Modalidade.buscaModalidade(m1.getId());
		m2.exibirInformacoes();
		
		//Retornando as informações de todas as modalidades
		ArrayList<Modalidade> modalidades = Modalidade.listaTodasModalidades();
	    for (Modalidade modalidade : modalidades) {
	    	modalidade.exibirInformacoes();
		}
		
		------------------------------------------------------------------------------------------
		
		//Criando uma equipe
		Equipe e1 = new Equipe("Estado Unidos", modalidade.getId());
		for (int i = 0; i < 6; i++) {
			Atleta a = new Atleta(i, "Atleta_"+(i+1));
			e1.adicionarAtleta(a);
		}
		e1.inserir();
		
		//Retornando as informações de 1 Equipe
		Equipe e2 = Equipe.buscaEquipe(e1.getId());
		e2.exibirInformacoes();
		
		//Retornando as informações de todas as equipes
		ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
		for (Equipe equipe : equipes) {
			equipe.exibirInformacoes();
		}
		
		------------------------------------------------------------------------------------------
		
		//Criando um placar
		Placar p1 = new Placar(Equipe.buscaEquipe(7), Equipe.buscaEquipe(4), 3, 0);
		p1.inserir();
		
		//Retornando as informações de 1 Placar
		Placar p2 = Placar.buscaPlacar(p1.getEquipe1(), p1.getEquipe2());
		p2.exibirInformacoes();
		
		//Retornando as informações de todos as placares
		ArrayList<Placar> placares = Placar.listaTodosPlacares();
		for (Placar placare : placares) {
			placare.exibirInformacoes();
		}
		
		*/
    }

}
