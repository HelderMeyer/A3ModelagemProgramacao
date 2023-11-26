package com.helder.cantina;

public class Funcionario extends Usuario {

    private String usuarioCargo;

    public Funcionario(int usuarioId, String usuarioNome, String usuarioSenha, String usuarioCargo){
        super(usuarioId, usuarioNome, usuarioSenha);
        this.usuarioCargo = usuarioCargo;
    }

    public String getUsuarioCargo() {
        return usuarioCargo;
    }

    public void setUsuarioCargo(String usuarioCargo) {
        this.usuarioCargo = usuarioCargo;
    }
}
