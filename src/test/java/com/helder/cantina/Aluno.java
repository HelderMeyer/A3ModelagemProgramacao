package com.helder.cantina;

public class Aluno {
	
	private int raAluno;
	private String senhaAluno;
	private String nomeAluno;
	private String cursoAluno;
	private double saldoAluno;
	
	Aluno(int raAluno, String senhaAluno, String nomeAluno, String cursoAluno, double saldoAluno){
		this.raAluno = raAluno;
		this.senhaAluno = senhaAluno;
		this.nomeAluno = nomeAluno;
		this.cursoAluno = cursoAluno;
		this.saldoAluno = saldoAluno;
	}
	
	public int getRaAluno() {
		return raAluno;
	}
	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}
	
	public String getSenhaAluno() {
		return senhaAluno;
	}
	public void setSenhaAluno(String senhaAluno) {
		this.senhaAluno = senhaAluno;
	}
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	
	public String getCursoAluno() {
		return cursoAluno;
	}
	public void setCursoAluno(String cursoAluno) {
		this.cursoAluno = cursoAluno;
	}
	
	public double getSaldoAluno() {
		return saldoAluno;
	}
	public void setSaldoAluno(double saldoAluno) {
		this.saldoAluno = saldoAluno;
	}
}
