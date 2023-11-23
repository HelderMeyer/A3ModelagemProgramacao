package com.helder.cantinabackup;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		CaixaDeTexto texfield = new CaixaDeTexto();

		texfield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		texfield.setSize(600, 150);
		texfield.setVisible(true);
	}

}
