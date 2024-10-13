package view;

import java.util.ArrayList;
import java.util.List;

import database.MySQL;
import model.Atleta;
import model.Equipe;
import model.Medalha;
import model.Modalidade;
import model.Placar;

public interface Inteface {
	
	public static void main(String[] args) {
		/*
        // Criando atletas

        // Criando modalidades
        Modalidade volei = new Modalidade(1, "Vôlei", 6);
        Modalidade tenisMesa = new Modalidade(2, "Tênis de Mesa", 2);
        Modalidade esgrima = new Modalidade(3, "Esgrima", 1);

        // Criando medalhas
        Medalha medalhaOuroVolei = new Medalha(volei, equipeBrasil, "Ouro");
        Medalha medalhaPrataTenisMesa = new Medalha(tenisMesa, equipeChinaTenisMesa, "Prata");
        Medalha medalhaBronzeEsgrima = new Medalha(esgrima, equipeRussiaEsgrima, "Bronze");

        // Criando placares
        Placar placarVoleiFinal = new Placar(equipeBrasil, equipeUsaEsgrima, 3, 2);
        Placar placarTenisMesaFinal = new Placar(equipeChinaTenisMesa, equipeBrasilTenisMesa, 4, 3);

        // Exibindo informações
        System.out.println("=== Equipes e Atletas ===");
        equipeBrasil.exibirInformacoes();
        equipeBrasilTenisMesa.exibirInformacoes();
        equipeChinaTenisMesa.exibirInformacoes();
        equipeRussiaEsgrima.exibirInformacoes();
        equipeUsaEsgrima.exibirInformacoes();

        System.out.println("\n=== Modalidades ===");
        volei.exibirInformacoes();
        tenisMesa.exibirInformacoes();
        esgrima.exibirInformacoes();

        System.out.println("\n=== Medalhas ===");
        medalhaOuroVolei.exibirInformacoes();
        medalhaPrataTenisMesa.exibirInformacoes();
        medalhaBronzeEsgrima.exibirInformacoes();

        System.out.println("\n=== Placares ===");
        placarVoleiFinal.exibirInformacoes();
        placarTenisMesaFinal.exibirInformacoes();
        */
		
		
		//Criando uma equipe
		Equipe e1 = new Equipe("Rússia");
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
			equipe.exibirInformacoes(); // Método da classe Atleta para exibir os dados
		}
    }

}
