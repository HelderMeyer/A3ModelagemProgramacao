package com.helder.cantinabackup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Janela extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnCabecalho;
	private JPanel pnRodape;
	private JPanel pnEsquerda;
	private JPanel pnDireita;
	private JPanel pnCentro;
	private JButton btn01;
    private JButton btn02;
    private JButton btn03;
    private JButton btn04;
    private JButton btn05;
    private JButton btn06;


	Janela() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(10,40);
		this.setTitle("Cantina Virtual");
		this.setLayout(new BorderLayout());
		this.iniciarComponentes();
		this.pack();
		this.setVisible(true);
	}

	public void iniciarComponentes() {
		this.pnCabecalho = new JPanel();
		this.pnRodape = new JPanel();
		this.pnEsquerda = new JPanel();
		this.pnDireita = new JPanel();
		this.pnCentro = new JPanel();
		
		btn01 = new JButton("Botão 01");
        btn02 = new JButton("Botão 02");
        btn03 = new JButton("Botão 03");
        btn04 = new JButton("Botão 04");
        btn05 = new JButton("Botão 05");
        btn06 = new JButton("Botão 06");
        
        btn01.setBounds(20, 50, 90, 30);  // (x,y, largura, altura)
        btn02.setBounds(130, 50, 90, 30);  // (x,y, largura, altura)        

        btn03.setBounds(30, 90, 90, 30);  // (x,y, largura, altura)
        btn04.setBounds(140, 90, 90, 30);  // (x,y, largura, altura)        

        btn05.setBounds(40, 130, 90, 30);  // (x,y, largura, altura)
        btn06.setBounds(150, 130, 90, 30);  // (x,y, largura, altura)        
        
        this.add(btn01);
        this.add(btn02);
        this.add(btn03);
        this.add(btn04);
        this.add(btn05);
        this.add(btn06);

        
        btn01.addActionListener(this);
        btn02.addActionListener(this);   


		/*this.pnCabecalho.setBackground(Color.red);
		this.pnRodape.setBackground(Color.blue);
		this.pnEsquerda.setBackground(Color.yellow);
		this.pnDireita.setBackground(Color.gray);
		this.pnCentro.setBackground(Color.green);*/

		this.add(pnCabecalho, BorderLayout.PAGE_START);
		this.add(pnRodape, BorderLayout.PAGE_END);
		this.add(pnEsquerda, BorderLayout.LINE_START);
		this.add(pnDireita, BorderLayout.LINE_END);
		this.add(pnCentro, BorderLayout.CENTER);
		
		this.iniciarCabecalho();
		this.iniciarRodape();
		this.iniciarCentro();
		
		
	}

	public void iniciarCabecalho() {

		this.pnCabecalho.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.pnCabecalho.add(new JLabel("Matrícula/RA"));
		this.pnCabecalho.add(new JTextField(10));

		this.pnCabecalho.add(new JLabel("Senha"));
		this.pnCabecalho.add(new JPasswordField(25));
	}

	public void iniciarRodape() {

		this.pnRodape.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.pnRodape.add(new JButton("Botão 04"));
		this.pnRodape.add(new JButton("Botão 05"));
	}

	public void iniciarCentro() {

		this.pnCentro.setLayout(new GridLayout(3, 2));

		this.pnCentro.add(new JLabel("Matricula"));
		this.pnCentro.add(new JTextField());

		this.pnCentro.add(new JLabel("Nome"));
		this.pnCentro.add(new JTextField());

		this.pnCentro.add(new JLabel("Idade"));
		this.pnCentro.add(new JTextField());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    JButton b = (JButton)o;
	    String nome = b.getText();
	        
	    System.out.println("o Clique do mouse foi acionado por : " + nome + " da     classe " + o.getClass());    

	}


}
