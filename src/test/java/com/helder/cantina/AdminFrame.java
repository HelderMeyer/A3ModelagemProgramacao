package com.helder.cantina;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int funcionarioId;
	private static DefaultTableModel model;
	private int linhaSelecionada = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AdminFrame frame = new AdminFrame();
				rodarTabela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void executar(int funcionarioId) {
		AdminFrame.main(null);
		AdminFrame.funcionarioId = funcionarioId;
	}

	public static void rodarTabela() {
		Connection connection = null;
		String query = "SELECT lan_ID, lan_nome, lan_valor, lan_descricao FROM lanche";
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Limpa o modelo da tabela antes de adicionar novos dados
			model.setRowCount(0);

			while (resultSet.next()) {
				int id = resultSet.getInt("lan_ID"); // Obt�m o ID do produto
				String nome = resultSet.getString("lan_nome");
				double valor = resultSet.getDouble("lan_valor");
				String descricao = resultSet.getString("lan_descricao");
				model.addRow(new Object[] { id, nome, valor, descricao }); // Adiciona o ID � tabela
			}

		} catch (SQLException e) {
			// Tratamento de exce��o
			System.err.println(e.getMessage());
		} finally {
			// Fechamento de conex�o
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static void atualizarTabela() {
		Connection connection = null;
		String query = "SELECT lan_ID, lan_nome, lan_valor, lan_descricao FROM lanche";
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Limpa o modelo da tabela antes de adicionar novos dados
			model.setRowCount(0);

			while (resultSet.next()) {
				int id = resultSet.getInt("lan_ID"); // Obt�m o ID do produto
				String nome = resultSet.getString("lan_nome");
				double valor = resultSet.getDouble("lan_valor");
				String descricao = resultSet.getString("lan_descricao");
				model.addRow(new Object[] { id, nome, valor, descricao });
			}

		} catch (SQLException e) {
			// Tratamento de exce��o
			System.err.println(e.getMessage());
		} finally {
			// Fechamento de conex�o
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private boolean checkIfIDExists(int lanID) {
		String query = "SELECT COUNT(*) AS count FROM lanche WHERE lan_ID = " + lanID;
		String count = Database.sqlRead(query, "count");
		int countInt = Integer.parseInt(count);
		return countInt > 0;
	}

	public AdminFrame() {
		setTitle("Portal dos Funcion�rios - Cadastrar Lanches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// T�tulo "Adicionar novos lanches" centralizado, fonte Arial, tamanho 25
		JLabel lblTitle = new JLabel("Adicionar novos lanches");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitle.setBounds(150, 20, 300, 30);
		contentPane.add(lblTitle);

		JLabel lblWelcome = new JLabel("Ol�, " + Database
				.sqlRead("SELECT * FROM funcionario WHERE funcionario_id = " + funcionarioId, "funcionario_nome"));
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblWelcome.setBounds(150, 60, 300, 30); // Ajuste da posi��o abaixo do t�tulo
		contentPane.add(lblWelcome);

		JLabel lblIdProduto = new JLabel("ID do Produto:");
		lblIdProduto.setBounds(50, 106, 100, 20); // Posi��o do label para o ID do produto
		contentPane.add(lblIdProduto);

		JTextField txtIdProduto = new JTextField();
		txtIdProduto.setBounds(140, 106, 80, 20); // Posi��o do input para o ID do produto
		contentPane.add(txtIdProduto);

		// Labels e Inputs
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(50, 146, 80, 20);
		contentPane.add(lblNome);

		JTextField txtNome = new JTextField();
		txtNome.setBounds(140, 146, 150, 20);
		contentPane.add(txtNome);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(50, 186, 80, 20);
		contentPane.add(lblValor);

		JTextField txtValor = new JTextField();
		txtValor.setBounds(140, 186, 80, 20);
		contentPane.add(txtValor);

		JLabel lblDescricao = new JLabel("Descri��o:");
		lblDescricao.setBounds(50, 226, 80, 20);
		contentPane.add(lblDescricao);

		JTextField txtDescricao = new JTextField();
		txtDescricao.setBounds(140, 226, 300, 20);
		contentPane.add(txtDescricao);

		// Bot�es
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(50, 266, 100, 30);
		contentPane.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(180, 266, 100, 30);
		contentPane.add(btnEditar);

		JButton btnApagar = new JButton("Apagar");
		btnApagar.setBounds(310, 266, 100, 30);
		contentPane.add(btnApagar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(440, 266, 100, 30);
		contentPane.add(btnSalvar);

		String[] colunas = { "#ID", "Nome do Lanche", "Pre�o do Lanche", "Descri��o do Lanche" };
		model = new DefaultTableModel(colunas, 0); // 0 linhas inicialmente
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 326, 602, 219);
		contentPane.add(scrollPane);

		btnAdicionar.addActionListener(e -> {
		    String id = txtIdProduto.getText();
		    String nome = txtNome.getText();
		    String valorStr = txtValor.getText();
		    String descricao = txtDescricao.getText();

		    // Verifica se todos os campos est�o preenchidos
		    if (!id.isEmpty() && !nome.isEmpty() && !valorStr.isEmpty() && !descricao.isEmpty()) {
		        try {
		            int lanID = Integer.parseInt(id);

		            // Verifica se o lan_ID � um n�mero inteiro positivo
		            if (lanID > 0) {
		                // Verifica se o valor � um n�mero inteiro positivo e n�o cont�m ponto ou v�rgula
		                if (!valorStr.contains(".") && !valorStr.contains(",")) {
		                    double valor = Double.parseDouble(valorStr);

		                    // Verifica se o valor � um n�mero inteiro positivo
		                    if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
		                        // Verifica se o lan_ID j� existe no banco de dados
		                        boolean idExists = checkIfIDExists(lanID);

		                        if (!idExists) {
		                            //
		                            /*String insertQuery = ("INSERT INTO lanche (lan_ID, lan_nome, lan_valor, lan_descricao) VALUES ("
		                                    + lanID + ", '" + nome + "', " + valor + ", '" + descricao + "')");*/
		                            //Database.sqlCreate(insertQuery);
		                            Lanche lanche = new Lanche(lanID,nome,valor,descricao);
		                            lanche.criarLanche();
		                            atualizarTabela();
		                            // Limpa os campos de entrada ap�s adicionar um novo item
		                            txtIdProduto.setText("");
		                            txtNome.setText("");
		                            txtValor.setText("");
		                            txtDescricao.setText("");
		                        } else {
		                            JOptionPane.showMessageDialog(null, "O lan_ID = " + id + " j� existe no banco de dados.");
		                        }
		                    } else {
		                        JOptionPane.showMessageDialog(null, "O valor do lanche deve ser um n�mero inteiro positivo.");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "O valor do lanche n�o pode conter '.' ou ','.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "O lan_ID deve ser um n�mero inteiro positivo.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Por favor, insira um lan_ID num�rico v�lido e um valor num�rico v�lido.");
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
		    }
		});

		btnEditar.addActionListener(e -> {
			linhaSelecionada = table.getSelectedRow();
			if (linhaSelecionada != -1) { // Verifica se h� uma linha selecionada
				int id = (int) model.getValueAt(linhaSelecionada, 0); // Obt�m o ID do produto
				String nome = model.getValueAt(linhaSelecionada, 1).toString();
				String valor = model.getValueAt(linhaSelecionada, 2).toString();
				String descricao = model.getValueAt(linhaSelecionada, 3).toString();

				// Preencher os campos de texto com os dados do item selecionado
				txtIdProduto.setText(String.valueOf(id)); // Define o ID no campo de ID do Produto
				txtNome.setText(nome);
				txtValor.setText(valor);
				txtDescricao.setText(descricao);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item para editar.");
			}
		});

		// No m�todo do bot�o "Salvar"
		btnSalvar.addActionListener(e -> {
			if (linhaSelecionada != -1) { // Verifica se um item foi selecionado para edi��o
				String nome = txtNome.getText();
				String valorStr = txtValor.getText();
				String descricao = txtDescricao.getText();

				if (!nome.isEmpty() && !valorStr.isEmpty() && !descricao.isEmpty()) {
					try {
						double valor = Double.parseDouble(valorStr);
						int id = Integer.parseInt(txtIdProduto.getText()); // Obt�m o ID do campo txtIdProduto

						// Verifica se o ID est� sendo alterado
						if (id != (int) model.getValueAt(linhaSelecionada, 0)) {
							JOptionPane.showMessageDialog(null, "N�o � poss�vel alterar o ID do pedido.");
						} else {
							// Atualiza o item no banco de dados
							String updateQuery = "UPDATE lanche SET lan_nome = '" + nome + "', lan_valor = " + valor
									+ ", lan_descricao = '" + descricao + "' WHERE lan_ID = " + id;
							Database.sqlUpdate(updateQuery);

							// Atualiza a tabela com os novos valores
							model.setValueAt(nome, linhaSelecionada, 1); // Atualiza o nome do lanche na tabela
							model.setValueAt(valor, linhaSelecionada, 2); // Atualiza o pre�o do lanche na tabela
							model.setValueAt(descricao, linhaSelecionada, 3); // Atualiza a descri��o do lanche na
																				// tabela

							linhaSelecionada = -1; // Limpa a vari�vel de linha selecionada ap�s a edi��o
							// Limpa os campos de entrada ap�s salvar as altera��es
							txtIdProduto.setText(""); // Limpa o campo do ID do Produto
							txtNome.setText("");
							txtValor.setText("");
							txtDescricao.setText("");
							atualizarTabela();
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor, insira um valor num�rico v�lido.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um item para editar.");
			}
		});

		btnApagar.addActionListener(e -> {
			int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar este item?",
					"Confirma��o", JOptionPane.YES_NO_OPTION);
			if (confirmacao == JOptionPane.YES_OPTION) {
				int linhaSelecionada = table.getSelectedRow();

				if (linhaSelecionada != -1) { // Verifica se h� uma linha selecionada
					int lanID = (int) model.getValueAt(linhaSelecionada, 0); // Obt�m o lan_ID da linha selecionada

					// Remove o item do banco de dados pelo lan_ID
					String deleteQuery = "DELETE FROM lanche WHERE lan_ID = " + lanID;
					Database.sqlDelete(deleteQuery);

					// Remove a linha da tabela
					model.removeRow(linhaSelecionada);
					atualizarTabela();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item para apagar.");
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuOpcoes = new JMenu("Op��es");
		menuBar.add(menuOpcoes);

		JMenuItem menuItemSair = new JMenuItem("Voltar");
		menuOpcoes.add(menuItemSair);
		menuItemSair.addActionListener(e -> {
			// Adicione aqui a l�gica para sair do sistema
			dispose();
		});
		// ... (restante do c�digo)

	}
}
