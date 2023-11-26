package com.helder.cantina;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuFuncionariosFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int funcionarioId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFuncionariosFrame frame = new MenuFuncionariosFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void executar(int funcioarioId) {
		MenuFuncionariosFrame.funcionarioId = funcioarioId;
		MenuFuncionariosFrame.main(null);
	}
	
	/**
	 * Create the frame.
	 */
	public MenuFuncionariosFrame() {
		setTitle("Menu Funcionários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblWelcome = new JLabel("Olá, " + Database
				.sqlRead("SELECT * FROM funcionario WHERE funcionario_id = " + funcionarioId, "funcionario_nome"));
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblWelcome.setBounds(132, 59, 169, 30); // Ajuste da posição abaixo do título
		contentPane.add(lblWelcome);

        JLabel lblTitulo = new JLabel("Menu Funcionários");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setBounds(103, 30, 228, 30); // Posicionamento do JLabel
        contentPane.add(lblTitulo);

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setFont(new Font("Arial", Font.PLAIN, 12));
        btnProdutos.setBounds(156, 100, 117, 29);
        contentPane.add(btnProdutos);
        
        btnProdutos.addActionListener(e -> {
        	AdminFrame.executar(funcionarioId);
        });
        
        JButton btnUsuarios = new JButton("Usuários");
        btnUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
        btnUsuarios.setBounds(156, 150, 117, 29);
        contentPane.add(btnUsuarios);
        
        btnUsuarios.addActionListener(e -> {
        	ConsultaUsuariosFrame.executar(funcionarioId);
        });
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuOpcoes = new JMenu("Opções");
        menuBar.add(menuOpcoes);
        
        JMenuItem menuItemSair = new JMenuItem("Sair");
        menuOpcoes.add(menuItemSair);
        menuItemSair.addActionListener(e -> {
        	FuncionariosFrame.main(null);
        	dispose();
        });
	}

}
