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
    private static JLabel lblSaldo;

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
    
    public void atualizarSaldo(JLabel lblSaldo) {
        String saldoString = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo");
        double saldo = Double.parseDouble(saldoString);
        lblSaldo.setText("Seu saldo é de R$" + String.format("%.2f", saldo) + ".");
    }
    
    public static void atualizarSaldo() {
        String saldoString = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo");
        double saldo = Double.parseDouble(saldoString);
        lblSaldo.setText("Seu saldo é de R$" + String.format("%.2f", saldo) + ".");
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

        lblSaldo = new JLabel("Seu saldo é de R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_saldo") + ".");
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnConsultar = new JButton("Consultar pedidos");
        btnConsultar.setFont(new Font("Arial", Font.PLAIN, 20));

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
                        .addComponent(btnConsultar))
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
                    .addComponent(btnConsultar)
                    .addContainerGap(113, Short.MAX_VALUE))
        );

        logarButton.addActionListener(e -> {

        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuOpcoes = new JMenu("Opções");
        menuBar.add(menuOpcoes);

        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuOpcoes.add(menuItemSair);
        menuItemSair.addActionListener(e -> {
            LoginFrame.main(null);
            this.dispose();
        });

        JMenuItem menuItemSaldo = new JMenuItem("Saldo");
        menuOpcoes.add(menuItemSaldo);
        menuItemSaldo.addActionListener(e -> {
            String meuUsuario = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + userId, "aluno_nome");
            String novoSaldoString = JOptionPane.showInputDialog("Digite o novo valor de saldo do aluno " + meuUsuario);

            // Verifica se o campo está vazio ou se contém apenas espaços em branco
            if (novoSaldoString.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
                return;
            }

            // Verifica se o valor inserido possui casas decimais (vírgula ou ponto)
            if (novoSaldoString.contains(".") || novoSaldoString.contains(",")) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor inteiro (sem casas decimais).");
                return;
            }

            try {
                // Convertendo o valor para inteiro
                int novoSaldoAluno = Integer.parseInt(novoSaldoString);

                // Verifica se o valor é negativo (se for, exibe uma mensagem de erro)
                if (novoSaldoAluno < 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor positivo.");
                    return;
                }

                // Atualizando o banco de dados com o valor
                Database.sqlUpdate("UPDATE student SET aluno_saldo = '" + novoSaldoAluno + "' WHERE aluno_RA = " + userId);

                // Mostrando o saldo
                JOptionPane.showMessageDialog(null, "O novo saldo de " + meuUsuario + " é R$" + novoSaldoAluno);
                atualizarSaldo();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido (sem casas decimais).");
            }
        });


        logarButton.addActionListener(e -> {
            NewPedidosFrame.executar(userId);
        });
        
        btnConsultar.addActionListener(e -> {
            ConsultaFrame.executar(userId);
        });
    }

}
