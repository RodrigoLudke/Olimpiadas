package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Atleta;
import model.Equipe;
import model.Medalha;
import model.Modalidade;
import model.Placar;

public class IntefaceConsole {
	
	public static void main(String[] args) {	
		/*
        // Criando medalhas - Sem comunicação com o banco (Até agora)
        Medalha medalhaOuroVolei = new Medalha(volei, equipeBrasil, "Ouro");
        Medalha medalhaPrataTenisMesa = new Medalha(tenisMesa, equipeChinaTenisMesa, "Prata");
        Medalha medalhaBronzeEsgrima = new Medalha(esgrima, equipeRussiaEsgrima, "Bronze");
		*/
		
		Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha

            try {
                switch (opcao) {
                    case 1:
                        criarModalidade(scanner);
                        break;
                    case 2:
                        exibirModalidadePorId(scanner);
                        break;
                    case 3:
                        listarModalidades();
                        break;
                    case 4:
                        criarEquipe(scanner);
                        break;
                    case 5:
                        exibirEquipePorId(scanner);
                        break;
                    case 6:
                        listarEquipes();
                        break;
                    case 7:
                        criarPlacar(scanner);
                        break;
                    case 8:
                        exibirPlacar(scanner);
                        break;
                    case 9:
                        listarPlacares();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        } while (opcao != 0);

        scanner.close();
    }
	
	private static void exibirMenu() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Criar Modalidade");
        System.out.println("2. Exibir Modalidade por ID");
        System.out.println("3. Listar Todas as Modalidades");
        System.out.println("4. Criar Equipe");
        System.out.println("5. Exibir Equipe por ID");
        System.out.println("6. Listar Todas as Equipes");
        System.out.println("7. Criar Placar");
        System.out.println("8. Exibir Placar por Equipes");
        System.out.println("9. Listar Todos os Placares");
        System.out.println("0. Sair");
    }

    // Função 1: Criar Modalidade
    private static void criarModalidade(Scanner scanner) throws Exception {
        System.out.print("Nome da modalidade: ");
        String nome = scanner.nextLine();
        System.out.print("Número de jogadores: ");
        int numJogadores = scanner.nextInt();

        Modalidade modalidade = new Modalidade(nome, numJogadores);
        modalidade.inserir();
        System.out.println("Modalidade criada com sucesso!");
    }

    // Função 2: Exibir Modalidade por ID
    private static void exibirModalidadePorId(Scanner scanner) throws Exception {
        System.out.print("ID da modalidade: ");
        int id = scanner.nextInt();

        Modalidade modalidade = Modalidade.buscaModalidade(id);
        modalidade.exibirInformacoes();
    }

    // Função 3: Listar todas as Modalidades
    private static void listarModalidades() throws Exception {
        ArrayList<Modalidade> modalidades = Modalidade.listaTodasModalidades();
        for (Modalidade modalidade : modalidades) {
            modalidade.exibirInformacoes();
        }
    }

    // Função 4: Criar Equipe
    private static void criarEquipe(Scanner scanner) {
        try {
            // Listar modalidades disponíveis
            ArrayList<Modalidade> modalidades = Modalidade.listaTodasModalidades();
            System.out.println("Modalidades disponíveis:");
            for (Modalidade m : modalidades) {
                System.out.println("ID: " + m.getId() + " | Nome: " + m.getModalidade());
            }

            System.out.print("Escolha o ID da Modalidade: ");
            int idModalidade = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha
            
            // Buscar a modalidade escolhida
            Modalidade modalidade = modalidades.stream()
                .filter(m -> m.getId() == idModalidade)
                .findFirst()
                .orElseThrow(() -> new Exception("Modalidade com ID " + idModalidade + " não encontrada."));

            System.out.print("Informe o país: ");
            String nomeEquipe = scanner.nextLine();

            // Criar a equipe
            Equipe novaEquipe = new Equipe(nomeEquipe, modalidade);

            for (int i = 0; i < modalidade.getNumeroAtletas(); i++) {
                System.out.print("Informe o nome do Atleta " + (i + 1) + ": ");
                String nomeAtleta = scanner.nextLine();
                Atleta atleta = new Atleta(nomeAtleta); // 0 pois o ID será gerado no banco
                novaEquipe.adicionarAtleta(atleta);
            }

            novaEquipe.inserir();
            System.out.println("Equipe criada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Função 5: Exibir Equipe por ID
    private static void exibirEquipePorId(Scanner scanner) throws Exception {
        System.out.print("ID da equipe: ");
        int id = scanner.nextInt();

        Equipe equipe = Equipe.buscaEquipe(id);
        equipe.exibirInformacoes();
    }

    // Função 6: Listar todas as Equipes
    private static void listarEquipes() throws Exception {
        ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
        for (Equipe equipe : equipes) {
            equipe.exibirInformacoes();
        }
    }

    // Função 7: Criar Placar
    private static void criarPlacar(Scanner scanner) {
	    try {
	        // Listar equipes e suas modalidades
	        ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
	        System.out.println("Equipes disponíveis:");
	        for (Equipe equipe : equipes) {
	            Modalidade modalidade = Modalidade.buscaModalidade(equipe.getModalidade().getId());
	            System.out.println("ID: " + equipe.getId() + " | Nome: " + equipe.getPais() + 
	                               " | Modalidade: " + modalidade.getModalidade());
	        }
	
	        System.out.print("Escolha o ID da Equipe 1: ");
	        int idEquipe1 = scanner.nextInt();
	        scanner.nextLine();  // Consumir quebra de linha
	
	        System.out.print("Escolha o ID da Equipe 2: ");
	        int idEquipe2 = scanner.nextInt();
	        scanner.nextLine();  // Consumir quebra de linha
	
	        // Verificar se as equipes são da mesma modalidade
	        Equipe equipe1 = Equipe.buscaEquipe(idEquipe1);
	        Equipe equipe2 = Equipe.buscaEquipe(idEquipe2);
	
	        if (equipe1.getModalidade().getId() != equipe2.getModalidade().getId()) {
	            throw new Exception("As equipes escolhidas não são da mesma modalidade!");
	        }
	
	        System.out.print("Informe o placar da Equipe 1: ");
	        int placarEquipe1 = scanner.nextInt();
	        System.out.print("Informe o placar da Equipe 2: ");
	        int placarEquipe2 = scanner.nextInt();
	        scanner.nextLine();  // Consumir quebra de linha
	
	        // Criar e inserir o placar
	        Placar placar = new Placar(equipe1, equipe2, placarEquipe1, placarEquipe2);
	        placar.inserir();
	
	        System.out.println("Placar inserido com sucesso!");
	    } catch (Exception e) {
	        System.err.println("Erro ao criar placar: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

    // Função 8: Exibir Placar
    private static void exibirPlacar(Scanner scanner) throws Exception {
        try {
            // Listar equipes disponíveis e suas modalidades
            ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
            if (equipes.isEmpty()) {
                System.out.println("Nenhuma equipe cadastrada.");
                return;
            }

            System.out.println("Equipes disponíveis:");
            for (Equipe equipe : equipes) {
                Modalidade modalidade = Modalidade.buscaModalidade(equipe.getModalidade().getId());
                System.out.println("ID: " + equipe.getId() + " | Nome: " + equipe.getPais() + 
                                   " | Modalidade: " + modalidade.getModalidade());
            }

            // Solicitar IDs das equipes
            System.out.print("ID da primeira equipe: ");
            int idEquipe1 = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha

            System.out.print("ID da segunda equipe: ");
            int idEquipe2 = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha

            // Buscar as equipes e validar
            Equipe equipe1 = Equipe.buscaEquipe(idEquipe1);
            Equipe equipe2 = Equipe.buscaEquipe(idEquipe2);

            if (equipe1 == null || equipe2 == null) {
                throw new Exception("Uma ou ambas as equipes não foram encontradas.");
            }

            if (equipe1.getModalidade().getId() != equipe2.getModalidade().getId()) {
                throw new Exception("As equipes escolhidas não pertencem à mesma modalidade.");
            }

            // Buscar e exibir o placar
            Placar placar = Placar.buscaPlacar(equipe1, equipe2);
            if (placar != null) {
                placar.exibirInformacoes();
            } else {
                System.out.println("Placar não encontrado para as equipes informadas.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao exibir o placar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Função 9: Listar todos os Placares
    private static void listarPlacares() throws Exception {
        ArrayList<Placar> placares = Placar.listaTodosPlacares();
        for (Placar placar : placares) {
            placar.exibirInformacoes();
        }
    }


}
