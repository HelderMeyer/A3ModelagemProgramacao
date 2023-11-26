package com.helder.cantina;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.sql.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FuncionariosFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FuncionariosFrame frame = new FuncionariosFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FuncionariosFrame() {
		setTitle("Portal dos Funcionários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel usuarioLabel = new JLabel("Usuário do Funcionário");
		usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton logarButton = new JButton("Logar");
		logarButton.setFont(new Font("Arial", Font.PLAIN, 20));

		usuarioTextField = new JTextField();
		usuarioTextField.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));

		passwordField = new JPasswordField();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(104, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSenha)
						.addComponent(usuarioLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordField)
							.addComponent(usuarioTextField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
							.addComponent(logarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCadastrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(usuarioLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usuarioTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSenha)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(logarButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCadastrar)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

		// ... (código anterior)

		// ... (código anterior)

		logarButton.addActionListener(e -> {
			String usuarioString = usuarioTextField.getText();
			
			// Lógica de autenticação - Aqui você pode adicionar a lógica de autenticação
			// com o banco de dados
			Connection connection = null;
			
			if (usuarioString.matches(".*[a-zA-Z].*")) {
		        // Se contém letras, notifica o usuário
		        JOptionPane.showMessageDialog(null, "O campo de funcionário não pode conter letras!");
		        return; // Encerra a execução do método
		    }else if(usuarioString.trim().isEmpty()) {
		    	JOptionPane.showMessageDialog(null, "O campo de funcionário não pode conter espaços ou estar vazio!");
		    }else {
		    	int usuario = Integer.parseInt(usuarioString);
		    	try {
					// Estabelece a conexão com o banco de dados
					connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30); // Define um limite de tempo para as consultas

					// Consulta se o usuário existe no banco de dados
					ResultSet rs = statement
							.executeQuery("SELECT funcionaro_nome, funcionario_senha FROM student WHERE funcionario_id = " + usuario);

					if (!rs.next()) {
						// Se o usuário não existir, avise ao usuário
						JOptionPane.showMessageDialog(null, "Usuário não existe!");
					} else {
						// Se o usuário existe, solicita a senha
						char[] enteredPassword = passwordField.getPassword();
						String senha = new String(enteredPassword);

						// Verifica se a senha está correta
						String senhaDoBanco = rs.getString("funcionario_senha");
						if (!senha.equals(senhaDoBanco)) {
							// Se a senha estiver incorreta, avise ao usuário
							JOptionPane.showMessageDialog(null, "Senha incorreta!");
						} else {
							// Se o usuário e a senha estiverem corretos, loga e mostra o nome do aluno
							String nomeDoFuncionario = rs.getString("funcionario_nome");
							JOptionPane.showMessageDialog(null, "Bem-vindo, " + nomeDoFuncionario);
							this.dispose();
							AdminFrame.executar(usuario); // LEVAR PARA A OUTRA TELA
						}
					}
				} catch (SQLException ex) {
					// Em caso de erro no banco de dados
					System.err.println(ex.getMessage());
				} finally {
					try {
						if (connection != null)
							connection.close(); // Fecha a conexão com o banco de dados
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				}
		    }
		});
		
		btnCadastrar.addActionListener(e -> {
			CadastroFuncionarioFrame.main(null);
		});
		
		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuOpcoes = new JMenu("Opções");
        menuBar.add(menuOpcoes);
        
        JMenuItem buttonAlunos = new JMenuItem("Alunos");
        menuOpcoes.add(buttonAlunos);
        
        buttonAlunos.addActionListener(e -> {
        	FuncionariosFrame.main(null);
        	dispose();
        });
        
        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuOpcoes.add(menuItemSair);
        menuItemSair.addActionListener(e -> {
            // Adicione aqui a lógica para sair do sistema
        	System.exit(0);
        });

	}
}
