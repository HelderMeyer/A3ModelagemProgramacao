package com.helder.cantinabackup;

public class Cantina {
	
	private String cnpjCantina; 
	private String nomeCantina;
	
	Cantina(String cnpj, String nomeCantina){
		this.cnpjCantina = cnpj;
		this.nomeCantina = nomeCantina;
	}

	public String getCnpjCantina() {
		return cnpjCantina;
	}

	public void setCnpjCantina(String cnpjCantina) {
		this.cnpjCantina = cnpjCantina;
	}

	public String getNomeCantina() {
		return nomeCantina;
	}

	public void setNomeCantina(String nomeCantina) {
		this.nomeCantina = nomeCantina;
	}
	
}
