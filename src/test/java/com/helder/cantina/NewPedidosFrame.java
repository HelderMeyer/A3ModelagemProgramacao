package com.helder.cantina;

import java.awt.EventQueue;
import java.awt.Font;
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
        // Lógica para preencher o JComboBox com os itens do banco de dados
        // Você precisará acessar o banco, executar uma consulta SQL e obter os resultados
        // Adicione os resultados ao JComboBox
        // Exemplo:
        // comboBox.addItem("Item do banco 1");
        // comboBox.addItem("Item do banco 2");
        // ...
    	String quantidadeString = Database.sqlRead("select count(*) as qtd from lanche", "qtd");
    	int quantidadeLanches = Integer.parseInt(quantidadeString);
    	for(int contador = 1; contador <= quantidadeLanches; contador++) {
    		comboBox.addItem(Database.sqlRead("SELECT * FROM lanche WHERE lan_ID = " + contador, "lan_nome"));
    	}
    }
    
    private void updateTotalValue(DefaultTableModel tableModel, JLabel lblTotal) {
        double total = 0.0;
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object value = tableModel.getValueAt(i, 1); // A coluna "Preço" está na posição 1
            if (value instanceof String) {
                String stringValue = (String) value;
                if (stringValue.startsWith("R$")) {
                    stringValue = stringValue.substring(2); // Removendo o "R$" para poder converter para double
                    stringValue = stringValue.replace(",", "."); // Ajustando a vírgula para ponto como separador decimal
                    total += Double.parseDouble(stringValue);
                }
            }
        }
        lblTotal.setText("Total = R$" + String.format("%.2f", total)); // Exibindo o total formatado na label
    }
    
    private void updateDatabase(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        String quantidadeString = Database.sqlRead("select count(ped_ID) as qtd from pedido;", "qtd");
        int quantidadePedidos = Integer.parseInt(quantidadeString);
        quantidadePedidos += 1;
        String quantidadePedLanString = Database.sqlRead("select count(pedlan_ID) as qtd from pedido_lanche;", "qtd");
        int quantidadePedLan = Integer.parseInt(quantidadePedLanString);
        quantidadePedLan += 1;
        Database.sqlCreate("INSERT INTO pedido (ped_ID, ped_alu_RA, ped_can_cnpj) VALUES ("+ (quantidadePedidos) + "," + userId + ",'" + Cantina.getCnpj()+"');");
        //System.out.println(quantidadePedidos);
        for (int i = 0; i < rowCount; i++) {
            //String precoItem = tableModel.getValueAt(i, 1).toString(); // Preço do lanche está na coluna 1
            String itemName = tableModel.getValueAt(i, 0).toString(); // Nome do lanche está na coluna 0
            String query = "SELECT lan_ID FROM lanche WHERE lan_nome = '" + itemName + "'";
            String idDoLanche = Database.sqlRead(query, "lan_ID");
            //Inserindo novo pedido para Mateus
            //INSERT INTO pedido (ped_ID, ped_alu_RA, ped_can_CNPJ)
            //VALUES (2, 1272117809, '40.368.914/0001-24');
             
            //Associando lanches ao pedido de Mateus na tabela pedido_lanche
            //INSERT INTO pedido_lanche (pedlan_ID, pedlan_ped_ID, pedlan_lan_ID)
            //VALUES (3, 2, 2), (4, 2, 4);
            
            
            //System.out.println(quantidadePedLan);
            
            Database.sqlCreate("INSERT INTO pedido_lanche (pedlan_ID, pedlan_ped_ID, pedlan_lan_ID) VALUES ("+(quantidadePedLan)+","+(quantidadePedidos)+","+idDoLanche+")");
            quantidadePedLan += 1;
        }

        // Após atualizar o banco de dados, você pode limpar a tabela de lanches
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

        JLabel lblSaldo = new JLabel(Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome") + ", seu saldo é de R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo") + ".");
        lblSaldo.setBackground(new Color(185, 255, 220));
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 15));

        // Lógica para preencher o select com itens do banco de dados
        
        JComboBox<String> selectItem = new JComboBox<>();
        preencherComboBox(selectItem); // Função para preencher o JComboBox
        // ...

        JButton addItemButton = new JButton("Adicionar item");
        JButton descriptionButton = new JButton("Descrição");

        JLabel lblTotal = new JLabel("Total = R$0.00");
        lblTotal.setFont(new Font("Arial", Font.PLAIN, 15));

        // Atribuindo o lblTotal local à variável de instância this.lblTotal
        this.lblTotal = lblTotal;

        JTable tabela = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome do Lanche");
        model.addColumn("Preço");

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
            String selectedItem = (String) selectItem.getSelectedItem();
            if (selectedItem != null) {
                // Lógica para exibir a descrição do item selecionado em um novo JFrame
                // Exemplo:
                // JFrame descricaoFrame = new JFrame();
                // descricaoFrame.setTitle("Descrição do Item");
                // JLabel descricaoLabel = new JLabel("Descrição: Detalhes do item selecionado");
                // descricaoFrame.add(descricaoLabel);
                // descricaoFrame.pack();
                // descricaoFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item antes de ver a descrição.");
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
                
                // Atualizando o valor total ao adicionar um item à tabela
                updateTotalValue(tableModel, lblTotal);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um item antes de adicionar à tabela.");
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
                updateDatabase(tableModel); // Passando lblTotal como parâmetro para a função
                JOptionPane.showMessageDialog(null, "Compra finalizada. Banco de dados atualizado!");
            } else {
                JOptionPane.showMessageDialog(null, "Adicione itens à tabela antes de finalizar a compra.");
            }
        });

    }
}
