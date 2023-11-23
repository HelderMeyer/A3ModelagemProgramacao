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
	
	public static String getAlunoNameByRA(int alunoRA) {
		String resultado = null;
		resultado = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + alunoRA, "aluno_nome");
		return resultado;
	}
	
	public static String getAlunoCursoByRA(int alunoRA) {
		String resultado = null;
		resultado = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + alunoRA, "aluno_curso");
		return resultado;
	}
	
	public static String getAlunoSaldoByRA(int alunoRA) {
		String resultado = null;
		resultado = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + alunoRA, "aluno_saldo");
		return resultado;
	}
	
	public static String getAlunoSenhaByRA(int alunoRA) {
		String resultado = null;
		resultado = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + alunoRA, "aluno_senha");
		return resultado;
	}

}
