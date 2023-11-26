package com.helder.cantina;

public abstract class Usuario {

	private int usuarioId;
	private String usuarioNome;
	private String usuarioSenha;

	public Usuario(int usuarioId, String usuarioNome, String usuarioSenha) {
		this.setUsuarioId(usuarioId);
		this.setUsuarioNome(usuarioNome);
		this.setUsuarioSenha(usuarioSenha);
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public String getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(String usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}

	public void criarUsuario() {
		Database.sqlCreate("INSERT INTO student VALUES (" + this.getUsuarioId() + ", '" + this.getUsuarioNome() + "', '"
				+ this.getUsuarioSenha() + "', 'Usuário', 0)");
	}
	
}
