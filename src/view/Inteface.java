package view;

import java.util.ArrayList;
import java.util.List;

import model.Atleta;
import model.Equipe;
import model.Medalha;
import model.Modalidade;
import model.Placar;

public interface Inteface {
	
	public static void main(String[] args) {
        // Criando atletas
        Atleta atleta1 = new Atleta(1, "Bruno Rezende");
        Atleta atleta2 = new Atleta(2, "Leal");
        Atleta atleta3 = new Atleta(3, "Wallace Souza");

        Atleta atleta4 = new Atleta(4, "Hugo Calderano");
        Atleta atleta5 = new Atleta(5, "Ma Long");

        Atleta atleta6 = new Atleta(6, "Inna Deriglazova");
        Atleta atleta7 = new Atleta(7, "Lee Kiefer");

        // Criando lista de atletas para a equipe de vôlei (Brasil)
        List<Atleta> atletasBrasil = new ArrayList<>();
        atletasBrasil.add(atleta1);
        atletasBrasil.add(atleta2);
        atletasBrasil.add(atleta3);

        // Criando lista de atletas para a equipe de esgrima (Rússia)
        List<Atleta> atletasRussia = new ArrayList<>();
        atletasRussia.add(atleta6);

        // Criando equipes
        Equipe equipeBrasil = new Equipe(1, "Brasil", atletasBrasil);
        Equipe equipeChinaTenisMesa = new Equipe(2, "China", List.of(atleta5)); // Equipe de um jogador no Tênis de Mesa
        Equipe equipeBrasilTenisMesa = new Equipe(3, "Brasil", List.of(atleta4));
        Equipe equipeRussiaEsgrima = new Equipe(4, "Rússia", atletasRussia);
        Equipe equipeUsaEsgrima = new Equipe(5, "Estados Unidos", List.of(atleta7));

        // Criando modalidades
        Modalidade volei = new Modalidade(1, "Vôlei", true);
        Modalidade tenisMesa = new Modalidade(2, "Tênis de Mesa", false);
        Modalidade esgrima = new Modalidade(3, "Esgrima", false);

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
    }

}
