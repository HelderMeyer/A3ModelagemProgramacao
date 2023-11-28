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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class NewPedidosFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int userId;
    private JLabel lblTotal;
    private double precoTotal;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewPedidosFrame frame = new NewPedidosFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void executar(int userId) {
    	NewPedidosFrame.userId = userId;
    	NewPedidosFrame.main(null);
    }
    
    private void preencherComboBox(JComboBox<String> comboBox) {
        // Limpa o JComboBox antes de adicionar novos itens
        comboBox.removeAllItems();

        // L�gica para preencher o JComboBox com os itens do banco de dados
        // Voc� precisar� acessar o banco, executar uma consulta SQL e obter os resultados
        // Adicione os resultados ao JComboBox

        // Exemplo gen�rico de como preencher o JComboBox com os IDs dos lanches
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Conex�o com o banco de dados
            connection = DriverManager.getConnection("jdbc:sqlite:databasecantina.db");
            statement = connection.createStatement();

            // Consulta SQL para obter todos os IDs dos lanches
            String query = "SELECT lan_nome FROM lanche";
            resultSet = statement.executeQuery(query);

            // Adiciona os IDs ao JComboBox
            while (resultSet.next()) {
                String id = resultSet.getString("lan_nome");
                comboBox.addItem(id);
            }
        } catch (SQLException e) {
            // Tratamento de exce��o
            e.printStackTrace();
        } finally {
            // Fechamento de recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    private void updateTotalValue(DefaultTableModel tableModel, JLabel lblTotal) {
        double total = 0.0;
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object value = tableModel.getValueAt(i, 1); // A coluna "Pre�o" est� na posi��o 1
            if (value instanceof String) {
                String stringValue = (String) value;
                if (stringValue.startsWith("R$")) {
                    stringValue = stringValue.substring(2); // Removendo o "R$" para poder converter para double
                    stringValue = stringValue.replace(",", "."); // Ajustando a v�rgula para ponto como separador decimal
                    total += Double.parseDouble(stringValue);
                    precoTotal = total;
                }
            }
        }
        
        lblTotal.setText("Total = R$" + String.format("%.2f", total)); // Exibindo o total formatado na label
    }
    
    private void updateDatabase(DefaultTableModel tableModel) {
    	String quantidadeStringPedido = Database.sqlRead("SELECT MAX(ped_ID) as max_ped_ID FROM pedido;", "max_ped_ID");
    	int quantidadePedidos = Integer.parseInt(quantidadeStringPedido);
    		quantidadePedidos += 1;

    	String quantidadeStringPedLan = Database.sqlRead("SELECT MAX(pedlan_ID) as max_pedlan_ID FROM pedido_lanche;", "max_pedlan_ID");
    	int quantidadePedLan = Integer.parseInt(quantidadeStringPedLan);
    	quantidadePedLan += 1; //tinha outro desse na linha de baixo. Se der ruim, voc� sabe que tem que devolver essa parte.
        int rowCount = tableModel.getRowCount();
        Database.sqlCreate("INSERT INTO pedido (ped_ID, ped_alu_RA, ped_can_cnpj) VALUES ("+ (quantidadePedidos) + "," + userId + ",'" + Cantina.getCnpj()+"');");
        //System.out.println(quantidadePedidos);
        for (int i = 0; i < rowCount; i++) {
            //String precoItem = tableModel.getValueAt(i, 1).toString(); // Pre�o do lanche est� na coluna 1
            String itemName = tableModel.getValueAt(i, 0).toString(); // Nome do lanche est� na coluna 0
            String query = "SELECT lan_ID FROM lanche WHERE lan_nome = '" + itemName + "'";
            String idDoLancheString = Database.sqlRead(query, "lan_ID");
            int idDoLanche = Integer.parseInt(idDoLancheString);
            //Inserindo novo pedido para Mateus
            //INSERT INTO pedido (ped_ID, ped_alu_RA, ped_can_CNPJ)
            //VALUES (2, 1272117809, '40.368.914/0001-24');
             
            //Associando lanches ao pedido de Mateus na tabela pedido_lanche
            //INSERT INTO pedido_lanche (pedlan_ID, pedlan_ped_ID, pedlan_lan_ID)
            //VALUES (3, 2, 2), (4, 2, 4);
            
            
            //System.out.println(quantidadePedLan);
            Pedido.createPedido(quantidadePedLan, quantidadePedidos, idDoLanche);
            quantidadePedLan += 1;
        }

        // Ap�s atualizar o banco de dados, voc� pode limpar a tabela de lanches
        String saldoString = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo");
        double saldoDouble = Double.parseDouble(saldoString);
        double novoSaldo = saldoDouble - precoTotal;
        Database.sqlUpdate("UPDATE student SET aluno_saldo = " + novoSaldo + " WHERE aluno_RA = " + userId);
        tableModel.setRowCount(0);
        this.lblTotal.setText("Total = R$0.00"); // Atualizar o total para zero
    }
    
    public NewPedidosFrame() {
    	setTitle("Cantina Virtual - Novo pedido");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 451);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblSaldo = new JLabel(Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome") + ", seu saldo � de R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo") + ".");
        lblSaldo.setBackground(new Color(185, 255, 220));
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 15));

        // L�gica para preencher o select com itens do banco de dados
        
        JComboBox<String> selectItem = new JComboBox<>();
        preencherComboBox(selectItem); // Fun��o para preencher o JComboBox
        // ...

        JButton addItemButton = new JButton("Adicionar item");
        JButton descriptionButton = new JButton("Descri��o");

        JLabel lblTotal = new JLabel("Total = R$0.00");
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 15));

        // Atribuindo o lblTotal local � vari�vel de inst�ncia this.lblTotal
        this.lblTotal = lblTotal;

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome do Lanche");
        model.addColumn("Pre�o");

        tabela.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tabela);
        
        JButton btnRemoverItem = new JButton("Remover item");
        
        JButton btnFinalizarCompra = new JButton("Finalizar compra");

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(lblSaldo)
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        					.addGroup(gl_contentPane.createSequentialGroup()
        						.addComponent(descriptionButton)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(addItemButton)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(btnRemoverItem, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
        					.addComponent(selectItem, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(lblTotal)
        				.addComponent(btnFinalizarCompra, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(14, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(41)
        			.addComponent(lblSaldo)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(selectItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(descriptionButton)
        				.addComponent(addItemButton)
        				.addComponent(btnRemoverItem))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        			.addGap(66)
        			.addComponent(lblTotal)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnFinalizarCompra)
        			.addContainerGap(57, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        
        descriptionButton.addActionListener(e -> {
            Object selectedItem = selectItem.getSelectedItem();
            if (selectedItem != null) {
                String itemName = (String) selectedItem;
                String query = "SELECT * FROM lanche WHERE lan_nome = '" + itemName + "'";
                String idDoLanche = Database.sqlRead(query, "lan_ID");
                String nomeDoLanche = Database.sqlRead(query, "lan_nome");
                String valorDoLanche = "R$" + Database.sqlRead(query, "lan_valor");
                String descricaoDoLanche = Database.sqlRead(query, "lan_descricao");
                
                String message = "ID do Lanche: " + idDoLanche + "\n" +
                                 "Nome do Lanche: " + nomeDoLanche + "\n" +
                                 "Valor do Lanche: " + valorDoLanche + "\n" +
                                 "Descri��o do Lanche: " + descricaoDoLanche;
                
                JOptionPane.showMessageDialog(null, message, "Detalhes do Lanche", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item antes de ver a descri��o.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });


        
        addItemButton.addActionListener(e -> {
            Object selectedItem = selectItem.getSelectedItem();
            if (selectedItem != null) {
                String itemName = (String) selectedItem;
                String precoItem = Database.sqlRead("SELECT * FROM lanche WHERE lan_nome = '" + itemName + "'", "lan_valor");
                String newPrecoItem = "R$" + precoItem;
                DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                tableModel.addRow(new Object[]{itemName, newPrecoItem});
                
                // Atualizando o valor total ao adicionar um item � tabela
                updateTotalValue(tableModel, lblTotal);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item antes de adicionar � tabela.");
            }
        });

        btnRemoverItem.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                tableModel.removeRow(selectedRow);
                updateTotalValue(tableModel, lblTotal); // Atualizando o valor total ao remover um item da tabela
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item na tabela para remover.");
            }
        });
        
        btnFinalizarCompra.addActionListener(e -> {
            DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
            if (tableModel.getRowCount() > 0) {
                // Calculando o valor total da compra
                double totalCompra = precoTotal;
                
                // Obtendo o saldo dispon�vel do usu�rio
                String saldoString = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo");
                double saldoDisponivel = Double.parseDouble(saldoString);
                
                // Verificando se o usu�rio possui saldo suficiente para a compra
                if (totalCompra > saldoDisponivel) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar a compra.");
                } else {
                    // O usu�rio possui saldo suficiente, ent�o podemos prosseguir com a atualiza��o do banco de dados
                    updateDatabase(tableModel); // Passando lblTotal como par�metro para a fun��o
                    JOptionPane.showMessageDialog(null, "Compra finalizada. Banco de dados atualizado!");
                    this.dispose();
                    PedidosFrame.atualizarSaldo();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Adicione itens � tabela antes de finalizar a compra.");
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


    }
}
