package com.helder.cantina;

public class Funcionario extends Usuario {

    public Funcionario(int usuarioId, String usuarioNome, String usuarioSenha){
        super(usuarioId, usuarioNome, usuarioSenha);
    }
    
    @Override
	public void criarUsuario() {
		Database.sqlCreate("INSERT INTO student VALUES (" + super.getUsuarioId() + ", '" + super.getUsuarioNome() + "', '"
				+ super.getUsuarioSenha() + "')");
	}
    
}
