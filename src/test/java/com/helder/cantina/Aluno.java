package com.helder.cantina;

public class Aluno {

	private int alunoRa;
	private String alunoNome;
	private String alunoSenha;
	private String alunoCurso;
	private double alunoSaldo;
	//private String alunoPedidos;

	Aluno(int alunoRa, String alunoNome, String alunoSenha, String alunoCurso, double alunoSaldo) {
		this.setAlunoRa(alunoRa);
		this.setAlunoNome(alunoNome);
		this.setAlunoSenha(alunoSenha);
		this.setAlunoCurso(alunoCurso);
		this.setAlunoSaldo(alunoSaldo);
		this.createAluno();
	}

	public int getAlunoRa() {
		return alunoRa;
	}

	public void setAlunoRa(int alunoRa) {
		this.alunoRa = alunoRa;
	}

	public String getAlunoNome() {
		return alunoNome;
	}

	public void setAlunoNome(String alunoNome) {
		this.alunoNome = alunoNome;
	}

	public String getAlunoSenha() {
		return alunoSenha;
	}

	public void setAlunoSenha(String alunoSenha) {
		this.alunoSenha = alunoSenha;
	}

	public String getAlunoCurso() {
		return alunoCurso;
	}

	public void setAlunoCurso(String alunoCurso) {
		this.alunoCurso = alunoCurso;
	}

	public double getAlunoSaldo() {
		return alunoSaldo;
	}

	public void setAlunoSaldo(double alunoSaldo) {
		this.alunoSaldo = alunoSaldo;
	}

	public void createAluno() {
		Database.sqlCreate("INSERT INTO student VALUES (" + this.getAlunoRa() + ", '" + this.getAlunoNome() + "', '" + this.getAlunoSenha() + "', '" + this.getAlunoCurso() + "', " + this.getAlunoSaldo() + ")");
	}

}
