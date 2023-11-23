package com.helder.cantina;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PedidosFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static int userId;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PedidosFrame frame = new PedidosFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void executar(int userId) {
        PedidosFrame.userId = userId;
        PedidosFrame.main(null);
    }

    public PedidosFrame() {
    	setTitle("Cantina Virtual - Tela de Pedidos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(176, 224, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel usuarioLabel = new JLabel("Cantina Virtual - Tela de Pedidos");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton logarButton = new JButton("Novo pedido");
        logarButton.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblWelcome = new JLabel("Olá, " + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome"));
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblSaldo = new JLabel("Seu saldo é de R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo") + ".");
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnCadastrar = new JButton("Consultar pedidos");
        btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 20));

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        contentPane.setLayout(gl_contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap(98, Short.MAX_VALUE)
                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(usuarioLabel)
                        .addComponent(lblWelcome)
                        .addComponent(lblSaldo)
                        .addComponent(logarButton)
                        .addComponent(btnCadastrar))
                    .addContainerGap(98, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(30)
                    .addComponent(usuarioLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblWelcome)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblSaldo)
                    .addGap(25)
                    .addComponent(logarButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnCadastrar)
                    .addContainerGap(113, Short.MAX_VALUE))
        );

        // Restante do seu código...

        logarButton.addActionListener(e -> {

        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuOpcoes = new JMenu("Opções");
        menuBar.add(menuOpcoes);

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuOpcoes.add(menuItemSair);
        menuItemSair.addActionListener(e -> {
            // Adicione aqui a lógica para sair do sistema
            LoginFrame.main(null);
            this.dispose();
        });

        JMenuItem menuItemSaldo = new JMenuItem("Saldo");
        menuOpcoes.add(menuItemSaldo);
        menuItemSaldo.addActionListener(e -> {
            // Adicione aqui a lógica para exibir o saldo
            // ou redirecionar para a página de saldo
            String meuUsuario = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome");
            String novoSaldoString = JOptionPane.showInputDialog("Digite o novo valor de saldo do aluno " + meuUsuario);
            int novoSaldoAluno = Integer.parseInt(novoSaldoString);
            Database.sqlUpdate("UPDATE student SET aluno_saldo = " + novoSaldoAluno + " WHERE aluno_RA = " + userId);
            JOptionPane.showMessageDialog(null, "O novo saldo de " + meuUsuario + " é R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo"));
            this.dispose();
            PedidosFrame.main(null);
        });
        
        logarButton.addActionListener(e -> {
            NewPedidosFrame.executar(userId);
        });
    }

}
