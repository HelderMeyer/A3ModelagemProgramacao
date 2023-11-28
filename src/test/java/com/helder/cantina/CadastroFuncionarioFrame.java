package com.helder.cantina;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CadastroFuncionarioFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usuarioTextField;
    private JTextField nomeTextField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	CadastroFuncionarioFrame frame = new CadastroFuncionarioFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroFuncionarioFrame() {
        setTitle("Portal dos Funcionários - Novo cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 550);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel usuarioLabel = new JLabel("Funcionário (Identificação)");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));

        usuarioTextField = new JTextField();
        usuarioTextField.setColumns(10);

        nomeTextField = new JTextField();
        nomeTextField.setColumns(10);

        passwordField = new JPasswordField();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.CENTER)
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addGap(98)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblSenha)
        						.addComponent(btnCadastrar)
        						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        					.addContainerGap(98, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNome)
        						.addComponent(usuarioLabel)
        						.addComponent(nomeTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        						.addComponent(usuarioTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
        					.addContainerGap(98, Short.MAX_VALUE))))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(65)
        			.addComponent(usuarioLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(usuarioTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addComponent(lblNome)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lblSenha)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGap(20)
        			.addComponent(btnCadastrar)
        			.addContainerGap(135, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);

        gl_contentPane.setAutoCreateGaps(true);
        gl_contentPane.setAutoCreateContainerGaps(true);

        pack();

        btnCadastrar.addActionListener(e -> {
            String usuarioString = usuarioTextField.getText();
            String nome = nomeTextField.getText();
            String senha = new String(passwordField.getPassword());

            if (!usuarioString.matches("[0-9]+") || usuarioString.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Funcionário ID deve conter apenas números e sem espaços!");
            } else if (usuarioString.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            } else {
                int usuario = Integer.parseInt(usuarioString);
                // Verifica se o usuário já existe no banco de dados
                if (usuario == Aluno.getAlunoRaByRA(usuario)) {
                    JOptionPane.showMessageDialog(null, "Esse funcionário já foi cadastrado!");
                } else {
                    // O usuário não existe, então podemos cadastrar
                	Funcionario funcionario = new Funcionario(usuario, nome, senha);
                	funcionario.criarUsuario();
                    JOptionPane.showMessageDialog(null, "Funcionário " + Funcionario.getFuncionarioNameById(usuario) + " cadastrado com sucesso!");
                    dispose();
                }
            }
        });


        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuOpcoes = new JMenu("Opções");
		menuBar.add(menuOpcoes);

		JMenuItem menuItemSair = new JMenuItem("Voltar");
		menuOpcoes.add(menuItemSair);
		menuItemSair.addActionListener(e -> {
			// Adicione aqui a lógica para sair do sistema
			dispose();
		});
        
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
