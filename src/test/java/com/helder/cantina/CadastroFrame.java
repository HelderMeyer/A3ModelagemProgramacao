package com.helder.cantina;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastroFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usuarioTextField;
    private JTextField nomeTextField;
    private JTextField cursoTextField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroFrame frame = new CadastroFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroFrame() {
        setTitle("Cantina Virtual - Tela de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 550);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel usuarioLabel = new JLabel("Usuário (RA)");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblCurso = new JLabel("Curso");
        lblCurso.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));

        usuarioTextField = new JTextField();
        usuarioTextField.setColumns(10);

        nomeTextField = new JTextField();
        nomeTextField.setColumns(10);

        cursoTextField = new JTextField();
        cursoTextField.setColumns(10);

        passwordField = new JPasswordField();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        contentPane.setLayout(gl_contentPane);

        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(98)
                        .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblSenha)
                                .addComponent(lblCurso)
                                .addComponent(lblNome)
                                .addComponent(usuarioLabel)
                                .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(cursoTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(nomeTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(usuarioTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(btnCadastrar))
                        .addContainerGap(98, Short.MAX_VALUE))
        );

        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(65)
                        .addComponent(usuarioLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usuarioTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addGap(11)
                        .addComponent(lblNome)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCurso)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cursoTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSenha)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(btnCadastrar)
                        .addContainerGap(66, Short.MAX_VALUE))
        );

        gl_contentPane.setAutoCreateGaps(true);
        gl_contentPane.setAutoCreateContainerGaps(true);

        pack();

        btnCadastrar.addActionListener(e -> {
            String usuarioString = usuarioTextField.getText();
            String nome = nomeTextField.getText();
            String curso = cursoTextField.getText();
            String senha = new String(passwordField.getPassword());

            if (!usuarioString.matches("[0-9]+") || usuarioString.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Usuário deve conter apenas números e sem espaços!");
            } else if (usuarioString.isEmpty() || nome.isEmpty() || curso.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            } else {
                int usuario = Integer.parseInt(usuarioString);
                // Verifica se o usuário já existe no banco de dados
                if (usuario == Aluno.getAlunoRaByRA(usuario)) {
                    JOptionPane.showMessageDialog(null, "Esse usuário já foi cadastrado!");
                } else {
                    // O usuário não existe, então podemos cadastrar
                    Aluno aluno = new Aluno(usuario, nome, senha, curso, 0);
                    aluno.criarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuário " + Aluno.getAlunoNameByRA(usuario) + " cadastrado com sucesso!");
                    dispose();
                }
            }
        });


        setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
