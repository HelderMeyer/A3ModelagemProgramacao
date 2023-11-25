package com.helder.cantina;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

//... (imports)

public class ConsultaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int userId;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ConsultaFrame frame = new ConsultaFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void executar(int userId) {
		ConsultaFrame.userId = userId;
		ConsultaFrame.main(null);
	}

	public ConsultaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Cantina Virtual - Consulta de Pedidos");

		JLabel lblTitle = new JLabel("Cantina Virtual - Consultar pedidos de "
				+ Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome"));
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));

		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Pedidos");
		model.addColumn("Lanche");
		model.addColumn("Total");
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);

		JButton btnVoltar = new JButton("Voltar");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING,
												gl_contentPane.createSequentialGroup()
														.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 92,
																GroupLayout.PREFERRED_SIZE)
														.addGap(269))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 604,
														Short.MAX_VALUE)
												.addContainerGap()))
								.addGroup(Alignment.TRAILING,
										gl_contentPane.createSequentialGroup().addComponent(lblTitle).addGap(124)))));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblTitle)
								.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		btnVoltar.addActionListener(e -> {
			dispose(); // Fecha esta janela de consulta
			// Coloque aqui a lógica para abrir a janela anterior ou fazer outra operação ao
			// voltar
		});

		// Lógica para preencher a tabela com os pedidos, lanches e valores totais
		preencherTabela(model);
	}

	private void preencherTabela(DefaultTableModel model) {
		try {
			// Consulta ao banco para obter os pedidos do usuário com o ID userId
			
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 seconds

			ResultSet rs = statement.executeQuery(
					"SELECT p.ped_ID, GROUP_CONCAT(l.lan_nome) as lan_nomes, SUM(l.lan_valor) as lan_valor_total FROM pedido p JOIN pedido_lanche pl ON p.ped_ID = pl.pedlan_ped_ID JOIN lanche l ON pl.pedlan_lan_ID = l.lan_ID WHERE p.ped_alu_RA ="+ userId +" GROUP BY p.ped_ID");
			
			// rs.getString("" + getString + "");
			// System.out.println(rs.getString("" + getString + ""));
			while (rs.next()) {
				// read the result set
				model.addRow(new Object[] { "#" + rs.getString("ped_ID"), rs.getString("lan_nomes"), "R$" + rs.getString("lan_valor_total") + ",00" });
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
