package com.helder.cantina;

import javax.swing.JOptionPane;

public class Cadastro {

	public void Cadastrar() {
		String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno: ");
		String raAlunoString = JOptionPane.showInputDialog("Digite o RA do aluno: ");
		int raAluno = Integer.parseInt(raAlunoString);
		String cursoAluno = JOptionPane.showInputDialog("Digite o curso do aluno: ");
		String senhaAluno = JOptionPane.showInputDialog("Digite o senha do aluno: ");
		Aluno aluno = new Aluno(raAluno, nomeAluno, senhaAluno, cursoAluno, 0);
		aluno.createAluno();
		System.out.println("Aluno cadastrado com sucesso!");
	}

}
