package com.helder.cantinabackup;

public class Lanche {
	
	private int idLanche;
	private String nomeLanche;
	private String descricaoLanche;
	private double valorLanche;
	
	Lanche(int idLanche, String nomeLanche, String descricaoLanche, double valorLanche){
		this.idLanche = idLanche;
		this.nomeLanche = nomeLanche;
		this.descricaoLanche = descricaoLanche;
		this.valorLanche = valorLanche;
	}

	public int getIdLanche() {
		return idLanche;
	}

	public void setIdLanche(int idLanche) {
		this.idLanche = idLanche;
	}
	
	public String getNomeLanche() {
		return nomeLanche;
	}

	public void setNomeLanche(String nomeLanche) {
		this.nomeLanche = nomeLanche;
	}

	public String getDescricaoLanche() {
		return descricaoLanche;
	}

	public void setDescricaoLanche(String descricaoLanche) {
		this.descricaoLanche = descricaoLanche;
	}

	public double getValorLanche() {
		return valorLanche;
	}

	public void setValorLanche(double valorLanche) {
		this.valorLanche = valorLanche;
	}
}
