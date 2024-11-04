package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Equipe;
import model.Modalidade;

public class InterfaceEquipe extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel erroLabel = new JLabel(""); // Exibir mensagens de erro

    public InterfaceEquipe() {}

    private void setPanel(String title, int width, int height) {
        // Configurações da Janela
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setVisible(true);
    }

    private void setPanel(String title) {
        setPanel(title, 400, 300);
    }

    public void criarEquipe() {
        setPanel("Cadastro de Equipe");

        JLabel lpais = new JLabel("Nome do País:");
        JTextField ipais = new JTextField(20);
        JLabel lidModalidade = new JLabel("ID da Modalidade:");
        JTextField iidModalidade = new JTextField(20);
        JButton botao = new JButton("Cadastrar");

        // Configuração do layout com GridBagLayout
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 0;
        c.add(lpais, gbc);
        c.add(ipais, gbc);

        gbc.gridy = 1;
        c.add(lidModalidade, gbc);
        c.add(iidModalidade, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza o botão
        c.add(botao, gbc);

        // Label para Erros
        gbc.gridy = 3;
        erroLabel.setForeground(Color.RED); // Texto em vermelho
        c.add(erroLabel, gbc);

        // Ação do botão
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Limpa a mensagem de erro antes de cada tentativa
                    erroLabel.setText("");

                    // Cria uma nova equipe
                    String nomePais = ipais.getText();

                    if (nomePais.isEmpty()) {
                        throw new Exception("País não pode ser vazio.");
                    }

                    int numeroModalidade = Integer.parseInt(iidModalidade.getText());
                    Modalidade modalidade = Modalidade.buscaModalidade(numeroModalidade);
                   /*
                    if (modalidade == null) {
                        throw new Exception("Modalidade com ID " + numeroModalidade + " não encontrada.");
                    }

                    */
                    System.out.println("Modalidade encontrada: ID = " + modalidade.getId());

                    Equipe equipe = new Equipe(nomePais, modalidade);
                    equipe.inserir();

                    JOptionPane.showMessageDialog(null, "Equipe cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException nfe) {
                    erroLabel.setText("Id Modalidade deve ser um valor numérico.");
                } catch (Exception ex) {
                    erroLabel.setText(ex.getMessage());
                }
            }
        });
    }

    public void exibirEquipePorId() {
        setPanel("Buscar Equipe");

        JLabel lEquipe = new JLabel("ID da Equipe:");
        JTextField iEquipe = new JTextField(20);
        JButton botao = new JButton("Buscar");

        // Configuração do layout com GridBagLayout
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Margens internas
        gbc.anchor = GridBagConstraints.WEST;

        // Campo para ID da Equipe
        gbc.gridy = 0;
        c.add(lEquipe, gbc);
        c.add(iEquipe, gbc);

        // Botão de Buscar
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;  // Centralizar botão
        c.add(botao, gbc);

        // Label para erros
        gbc.gridy = 2;
        erroLabel.setForeground(Color.RED);
        c.add(erroLabel, gbc);

        // Adiciona uma área para exibir os resultados dinamicamente
        JPanel resultadoPanel = new JPanel(new GridBagLayout());
        gbc.gridy = 3;
        c.add(resultadoPanel, gbc);

        // Ação do botão Buscar
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    erroLabel.setText("");
                    resultadoPanel.removeAll();

                    int id = Integer.parseInt(iEquipe.getText());
                    Equipe equipe = Equipe.buscaEquipe(id);

                    if (equipe.getId() == 0) {
                        throw new Exception("Nenhuma EQUIPE encontrada com este ID");
                    }

                    GridBagConstraints resultGbc = new GridBagConstraints();
                    resultGbc.insets = new Insets(5, 5, 5, 5);
                    resultGbc.anchor = GridBagConstraints.WEST;

                    resultGbc.gridy = 0;
                    JTextField nome = new JTextField(equipe.getPais(), 20);
                    nome.setEditable(false);
                    resultadoPanel.add(new JLabel("País da Equipe:"), resultGbc);
                    resultadoPanel.add(nome, resultGbc);

                    resultGbc.gridy = 1;
                    JTextField numeroJogadores = new JTextField(Integer.toString(equipe.getModalidade().getId()), 20);
                    numeroJogadores.setEditable(false);
                    resultadoPanel.add(new JLabel("ID da Modalidade:"), resultGbc);
                    resultadoPanel.add(numeroJogadores, resultGbc);

                    // Atualizar o layout com os novos componentes
                    resultadoPanel.revalidate();
                    resultadoPanel.repaint();

                } catch (NumberFormatException nfe) {
                    erroLabel.setText("ID deve ser um valor numérico.");
                } catch (Exception ex) {
                    erroLabel.setText(ex.getMessage());
                }
            }
        });
    }

    public void listarEquipes() {
        setPanel("Lista de Equipes");

        JTable tabela;
        DefaultTableModel dados;

        // Cria o modelo da tabela
        dados = new DefaultTableModel(new String[]{"ID", "País da Equipe", "ID Modalidade"}, 0);
        tabela = new JTable(dados);

        ArrayList<Equipe> equipes = Equipe.listaTodasEquipes();
        for (Equipe equipe : equipes) {
            dados.addRow(new Object[]{
                    equipe.getId(),
                    equipe.getPais(),
                    equipe.getModalidade().getId(),
            });
        }

        tabela.setAutoCreateRowSorter(true);
        tabela.setFillsViewportHeight(true);

        // Adiciona a tabela dentro de um JScrollPane (para rolagem)
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.getContentPane().add(scrollPane);
        add(scrollPane, BorderLayout.CENTER);
    }
}
