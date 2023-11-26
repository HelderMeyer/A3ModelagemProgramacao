package com.helder.cantina;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ConsultaUsuariosFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private static int funcionarioId;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConsultaUsuariosFrame frame = new ConsultaUsuariosFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public static void executar(int funcionarioId) {
    	ConsultaUsuariosFrame.funcionarioId = funcionarioId;
    	ConsultaUsuariosFrame.main(null);
    }

    public ConsultaUsuariosFrame() {
        setTitle("Portal dos Funcionários - Consulta Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título "Usuários cadastrados"
        JLabel lblUsuariosCadastrados = new JLabel("Usuários cadastrados");
        lblUsuariosCadastrados.setFont(new Font("Arial", Font.BOLD, 25));
        lblUsuariosCadastrados.setBounds(186, 11, 261, 30);
        contentPane.add(lblUsuariosCadastrados);
        
        JLabel lblWelcome = new JLabel("Olá, " + Database
				.sqlRead("SELECT * FROM funcionario WHERE funcionario_id = " + funcionarioId, "funcionario_nome"));
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblWelcome.setBounds(222, 40, 189, 30); // Ajuste da posição abaixo do título
		contentPane.add(lblWelcome);

        // Label "Alunos"
        JLabel lblAlunos = new JLabel("Alunos");
        lblAlunos.setFont(new Font("Arial", Font.PLAIN, 20));
        lblAlunos.setBounds(10, 89, 100, 20);
        contentPane.add(lblAlunos);

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
        
        // Tabela com 5 colunas: RA, Nome, Curso, Saldo e Senha
        String[] colunas = {"RA", "Nome", "Curso", "Saldo", "Senha"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 119, 600, 200);
        contentPane.add(scrollPane);

        // Preenchimento da tabela com dados do banco de dados
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db")) {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT aluno_RA, aluno_nome, aluno_curso, aluno_saldo, aluno_senha FROM student");

            while (rs.next()) {
                String ra = rs.getString("aluno_RA");
                String nome = rs.getString("aluno_nome");
                String curso = rs.getString("aluno_curso");
                double saldo = rs.getDouble("aluno_saldo");
                String senha = rs.getString("aluno_senha");

                model.addRow(new Object[]{ra, nome, curso, saldo, senha});
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
}


