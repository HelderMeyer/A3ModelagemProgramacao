package com.helder.cantina;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class CaixaDeTexto2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField raNovo;
    private JTextField nomeNovo; // Adicionando campo para o nome
    private JPasswordField senhaNova;
    private JButton cadastrar;
    private JLabel user, pass, userName;

    public CaixaDeTexto2() {
        super("Cantina Virtual - Tela de cadastro");

        setLayout(new FlowLayout());

        user = new JLabel("RA: ");
        add(user);

        raNovo = new JTextField(15);
        add(raNovo);

        userName = new JLabel("Nome:"); // Label para o nome
        add(userName);
        
        nomeNovo = new JTextField(15); // Caixa de texto para o nome
        add(nomeNovo);

        pass = new JLabel("Nova Senha:   ");
        add(pass);

        senhaNova = new JPasswordField(15);
        add(senhaNova);

        cadastrar = new JButton("Cadastrar");
        add(cadastrar);

        cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (evento.getSource() == cadastrar) {
                    String ra = raNovo.getText();
                    String nome = nomeNovo.getText(); // Obtendo o nome inserido
                    char[] passChars = senhaNova.getPassword();
                    String pass = new String(passChars);

                    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cantina.db")) {
                        String query = "INSERT INTO student VALUES(?, ?, ?)"; // Adicionando o campo do nome
                        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                            pstmt.setString(1, ra);
                            pstmt.setString(2, pass);
                            pstmt.setString(3, nome); // Inserindo o nome na query
                            pstmt.executeUpdate();
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    } finally {
                        Arrays.fill(passChars, ' '); // Limpar dados sensíveis da memória
                    }

                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    dispose(); // Fecha a janela de cadastro
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }
}
