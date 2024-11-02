package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Modalidade;

public class InterfaceModalidade extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel erroLabel = new JLabel(""); // Exibir mensagens de erro
	
	public InterfaceModalidade() {}

    private void setPanel(String title, int with, int height) {
    	// Configurações da Janela
    	this.setTitle(title);
        this.setSize(with, height);
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setVisible(true);
    }
    
    private void setPanel(String title) {
    	setPanel(title, 400, 300);
    }
    
    public void criar() {
    	setPanel("Cadastro de Modalidade");

        JLabel lModalidade = new JLabel("Nome da Modalidade:");
    	JTextField iModalidade = new JTextField(20);
        JLabel lNumero = new JLabel("Número de Atletas:");
        JTextField iNumero = new JTextField(20);
        JButton botao = new JButton("Cadastrar");
        
        // Configuração do layout com GridBagLayout
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 0;
        c.add(lModalidade, gbc);
        c.add(iModalidade, gbc);

        gbc.gridy = 1;
        c.add(lNumero, gbc);
        c.add(iNumero, gbc);

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

                    // Cria uma nova modalidade
                    String nome = iModalidade.getText();
                    
                    if (nome.isEmpty()) {
                        throw new Exception("Nome da modalidade não pode ser vazio.");
                    }
                    
                    int numeroAtletas = Integer.parseInt(iNumero.getText());

                    Modalidade m = new Modalidade(nome, numeroAtletas);
                    m.inserir();
                    
                    JOptionPane.showMessageDialog(null, "Modalidade cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException nfe) {
                    erroLabel.setText("Número de atletas deve ser um valor numérico.");
                } catch (Exception ex) {
                    erroLabel.setText(ex.getMessage());
                }
            }
        });
    }

    public void buscar() {
        setPanel("Buscar Modalidade");

        JLabel lModalidade = new JLabel("ID da Modalidade:");
        JTextField iModalidade = new JTextField(20);
        JButton botao = new JButton("Buscar");

        // Configuração do layout com GridBagLayout
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Margens internas
        gbc.anchor = GridBagConstraints.WEST;

        // Campo para ID da Modalidade
        gbc.gridy = 0;
        c.add(lModalidade, gbc);
        c.add(iModalidade, gbc);

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

                    int id = Integer.parseInt(iModalidade.getText());
                    Modalidade modalidade = Modalidade.buscaModalidade(id);
                    
                    if (modalidade.getId() == 0) {
                    	throw new Exception("Nenhuma MODALIDADE encontrada com este ID");
                    }

                    GridBagConstraints resultGbc = new GridBagConstraints();
                    resultGbc.insets = new Insets(5, 5, 5, 5);
                    resultGbc.anchor = GridBagConstraints.WEST;

                    resultGbc.gridy = 0;
                    JTextField nome = new JTextField(modalidade.getModalidade(), 20);
                    nome.setEditable(false);
                    resultadoPanel.add(new JLabel("Nome da Modalidade:"), resultGbc);
                    resultadoPanel.add(nome, resultGbc);

                    resultGbc.gridy = 1;
                    JTextField numeroAtletas = new JTextField(Integer.toString(modalidade.getNumeroAtletas()), 20);
                    numeroAtletas.setEditable(false);
                    resultadoPanel.add(new JLabel("Número de Atletas:"), resultGbc);
                    resultadoPanel.add(numeroAtletas, resultGbc);

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


    public void listar() {
    	setPanel("Lista de Modalidades");
        
    	JTable tabela;
    	DefaultTableModel dados;

        // Cria o modelo da tabela
        dados = new DefaultTableModel(new String[]{"ID", "Modalidade", "Número de Atletas", "É Coletivo"}, 0);
        tabela = new JTable(dados);
        
        ArrayList<Modalidade> modalidades = Modalidade.listaTodasModalidades();
        for (Modalidade modalidade : modalidades) {
        	dados.addRow(new Object[]{
                modalidade.getId(),
                modalidade.getModalidade(),
                modalidade.getNumeroAtletas(),
                modalidade.isColetivo()
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
