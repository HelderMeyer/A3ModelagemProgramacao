package com.helder.cantina;

public class Funcionario extends Usuario {

    public Funcionario(int usuarioId, String usuarioNome, String usuarioSenha){
        super(usuarioId, usuarioNome, usuarioSenha);
    }
    
    @Override
	public void criarUsuario() {
		Database.sqlCreate("INSERT INTO funcionario VALUES (" + super.getUsuarioId() + ", '" + super.getUsuarioNome() + "', '"
				+ super.getUsuarioSenha() + "')");
	}
    
    public static String getFuncionarioNameById(int usuario) {
		String resultado = null;
		resultado = Database.sqlRead("SELECT * FROM funcionario WHERE funcionario_id = " + usuario, "funcionario_nome");
		return resultado;
	}
    
}
