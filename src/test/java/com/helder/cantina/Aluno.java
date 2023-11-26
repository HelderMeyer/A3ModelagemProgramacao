package com.helder.cantina;

public class Aluno extends Usuario {

	private String alunoCurso;
	private double alunoSaldo;

	Aluno(int usuarioId, String usuarioNome, String usuarioSenha, String alunoCurso, double alunoSaldo) {
		super(usuarioId, usuarioNome, usuarioSenha);
		this.setAlunoCurso(alunoCurso);
		this.setAlunoSaldo(alunoSaldo);
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

	@Override
	public void criarUsuario() {
		Database.sqlCreate("INSERT INTO student VALUES (" + super.getUsuarioId() + ", '" + super.getUsuarioNome() + "', '"
				+ super.getUsuarioSenha() + "', '" + this.getAlunoCurso() + "'," + this.getAlunoSaldo() + ")");
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

	public static int getAlunoRaByRA(int alunoRA) {
		String resultado = Database.sqlRead("SELECT * FROM student WHERE aluno_RA = " + alunoRA, "aluno_RA");
		// Verifica se o resultado não é nulo e não está vazio
		if (resultado != null && !resultado.isEmpty()) {
			// Tenta converter a string para inteiro
			try {
				return Integer.parseInt(resultado);
			} catch (NumberFormatException e) {
				// Em caso de erro na conversão, pode tratar aqui
				// Por exemplo, lançar uma exceção ou retornar um valor padrão
				e.printStackTrace();
			}
		}
		// Retorna um valor padrão se não foi possível converter para inteiro
		return -1; // Pode retornar outro valor se for mais apropriado
	}

}
