package view;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

import model.Atleta;
import model.Equipe;
import model.Medalha;
import model.Placar;


public class Interface extends JFrame {

    private JPanel panel;

    public Interface() {
        setTitle("Sistema de Modalidades Esportivas");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel(new GridBagLayout()); // Usando GridBagLayout para centralizar
        panel.setBackground(new Color(240, 240, 240)); // Cor de fundo suave
        add(panel);

        criarComponentes();
    }

    private void criarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os elementos
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título centralizado
        JLabel titulo = new JLabel("Menu Principal");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(JLabel.CENTER); // Alinhando o título no centro
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        // Botões com as funcionalidades descritas
        JButton btnCriarModalidade = new JButton("Criar Modalidade");
        JButton btnExibirModalidade = new JButton("Exibir Modalidade por ID");
        JButton btnListarModalidades = new JButton("Listar Modalidades");
        JButton btnCriarEquipe = new JButton("Criar Equipe");
        JButton btnExibirEquipe = new JButton("Exibir Equipe por ID");
        JButton btnListarEquipes = new JButton("Listar Equipes");
        JButton btnCriarPlacar = new JButton("Criar Placar");
        JButton btnExibirPlacar = new JButton("Exibir Placar por Equipes");
        JButton btnListarPlacares = new JButton("Listar Placares");
        JButton btnSair = new JButton("Sair");

        // Adicionando ações para cada botão
        btnCriarModalidade.addActionListener(e -> criarModalidade());
        btnExibirModalidade.addActionListener(e -> exibirModalidadePorId());
        btnListarModalidades.addActionListener(e -> listarModalidades());
        btnCriarEquipe.addActionListener(e -> criarEquipe());
        btnExibirEquipe.addActionListener(e -> exibirEquipePorId());
        btnListarEquipes.addActionListener(e -> listarEquipes());
        btnCriarPlacar.addActionListener(e -> criarPlacar());
        btnExibirPlacar.addActionListener(e -> exibirPlacar());
        btnListarPlacares.addActionListener(e -> listarPlacares());
        btnSair.addActionListener(e -> System.exit(0));

        // Adicionando componentes ao painel com centralização
        gbc.gridy = 1;
        panel.add(btnCriarModalidade, gbc);

        gbc.gridy = 2;
        panel.add(btnExibirModalidade, gbc);

        gbc.gridy = 3;
        panel.add(btnListarModalidades, gbc);

        gbc.gridy = 4;
        panel.add(btnCriarEquipe, gbc);

        gbc.gridy = 5;
        panel.add(btnExibirEquipe, gbc);

        gbc.gridy = 6;
        panel.add(btnListarEquipes, gbc);

        gbc.gridy = 7;
        panel.add(btnCriarPlacar, gbc);

        gbc.gridy = 8;
        panel.add(btnExibirPlacar, gbc);

        gbc.gridy = 9;
        panel.add(btnListarPlacares, gbc);

        gbc.gridy = 10;
        panel.add(btnSair, gbc);
    }

    // Funções placeholders para cada ação
    private void criarModalidade() {
    	(new InterfaceModalidade()).criar();
    }

    private void exibirModalidadePorId() {
    	(new InterfaceModalidade()).buscar();
    }

    private void listarModalidades() {
    	(new InterfaceModalidade()).listar();
    }

    private void criarEquipe() {
        JOptionPane.showMessageDialog(this, "Funcionalidade 'Criar Equipe' ainda não implementada.");
    }

    private void exibirEquipePorId() {
        JOptionPane.showMessageDialog(this, "Funcionalidade 'Exibir Equipe por ID' ainda não implementada.");
    }

    private void listarEquipes() {
        //JOptionPane.showMessageDialog(this, "Funcionalidade 'Listar Equipes' ainda não implementada.");
    	ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
        for (Equipe equipe : equipes) {
            equipe.exibirInformacoes();
        }
    }

    private void criarPlacar() {
        JOptionPane.showMessageDialog(this, "Funcionalidade 'Criar Placar' ainda não implementada.");
    }

    private void exibirPlacar() {
        JOptionPane.showMessageDialog(this, "Funcionalidade 'Exibir Placar' ainda não implementada.");
    }

    private void listarPlacares() {
        //JOptionPane.showMessageDialog(this, "Funcionalidade 'Listar Placares' ainda não implementada.");
    	ArrayList<Placar> placares = Placar.listaTodosPlacares();
	        for (Placar placar : placares) {
	            placar.exibirInformacoes();
	        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }
}
