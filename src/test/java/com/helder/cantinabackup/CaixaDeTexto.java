package com.helder.cantinabackup;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class CaixaDeTexto extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField usuario;
    private JPasswordField senha;
    private JButton login, cadastrar;
    private JLabel user, pass;

    public CaixaDeTexto() {
        super("Cantina Virtual - Tela de Login");
        setLayout(new FlowLayout());

        user = new JLabel("Usuário: ");
        add(user);

        usuario = new JTextField(15);
        add(usuario);

        pass = new JLabel("Senha:   ");
        add(pass);

        senha = new JPasswordField(15);
        add(senha);

        login = new JButton("Entrar");
        add(login);

        cadastrar = new JButton("Cadastrar");
        add(cadastrar);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (evento.getSource() == login) {
                    String user = usuario.getText();
                    char[] passChars = senha.getPassword();
                    String pass = new String(passChars);

                    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cantina.db")) {
                        String query = "SELECT aluno_senha, aluno_nome FROM student WHERE aluno_RA = ?";
                        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                            pstmt.setString(1, user);
                            ResultSet resultSet = pstmt.executeQuery();
                            if (resultSet.next()) {
                                String retrievedPass = resultSet.getString("aluno_senha");
                                String retrievedName = resultSet.getString("aluno_nome");
                                if (pass.equals(retrievedPass)) {
                                    JOptionPane.showMessageDialog(null, "Seja bem-vindo, " + retrievedName);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Senha errada! Estude Java!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println(e.getMessage());
                    } finally {
                        Arrays.fill(passChars, ' '); // Limpar dados sensíveis da memória
                    }
                }
            }
        });

        cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                if (evento.getSource() == cadastrar) {
                    CaixaDeTexto2 cadastro = new CaixaDeTexto2();
                    cadastro.setSize(200, 200);
                    cadastro.setVisible(true);
                }
            }
        });

       
    }

    public static void main(String[] args) {
        new CaixaDeTexto();
    }
}
