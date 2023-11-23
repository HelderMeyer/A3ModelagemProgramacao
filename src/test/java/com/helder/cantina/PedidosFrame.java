package com.helder.cantina;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PedidosFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

    public PedidosFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

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
        	String meuUsuario = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + 1272117808, "aluno_nome");
        	String novoSaldoString = JOptionPane.showInputDialog("Digite o novo valor de saldo do aluno " + meuUsuario);
        	int novoSaldoAluno = Integer.parseInt(novoSaldoString);
        	Database.sqlUpdate("UPDATE student SET aluno_saldo = " + novoSaldoAluno + " WHERE aluno_RA = 1272117808");
        	JOptionPane.showMessageDialog(null, "O novo saldo de " + meuUsuario + " é R$" + Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + 1272117808, "aluno_saldo"));
        });
    }
}
