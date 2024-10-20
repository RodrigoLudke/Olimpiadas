package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Modalidade;

public class InterfaceModalidade extends JFrame {
    private JButton botao;
    private JTextField iModalidade = new JTextField(20);
    private JTextField iNumero = new JTextField(20);
    private JLabel lModalidade = new JLabel("Nome da Modalidade:");
    private JLabel lNumero = new JLabel("Número de Atletas:");
    private JLabel erroLabel = new JLabel(""); // Exibir mensagens de erro

    public InterfaceModalidade() {
        
    }

    public void inserir() {
        botao = new JButton("Cadastrar");

        // Configuração do layout com GridBagLayout
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Nome da Modalidade
        gbc.gridy = 0;
        c.add(lModalidade, gbc);
        c.add(iModalidade, gbc);

        // Número de Atletas
        gbc.gridy = 1;
        c.add(lNumero, gbc);
        c.add(iNumero, gbc);

        // Botão de Cadastrar
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
                } catch (NumberFormatException nfe) {
                    erroLabel.setText("Número de atletas deve ser um valor numérico.");
                } catch (Exception ex) {
                    erroLabel.setText(ex.getMessage());
                }
            }
        });

        // Configurações da Janela
        this.setTitle("Cadastro de Modalidade");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
