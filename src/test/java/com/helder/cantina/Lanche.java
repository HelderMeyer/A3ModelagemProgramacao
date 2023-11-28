package com.helder.cantina;

public class Lanche {
    
    private int ID;
    private String nome;
    private double valor;
    private String descricao;
    
    public Lanche (int ID, String nome, double valor, String descricao){
        this.ID = ID;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void criarLanche() {
		Database.sqlCreate("INSERT INTO lanche VALUES (" + this.getID() + ", '" + this.getNome() + "', "
				+ this.getValor() + ", '" + this.getDescricao() + "')");
	}

}
