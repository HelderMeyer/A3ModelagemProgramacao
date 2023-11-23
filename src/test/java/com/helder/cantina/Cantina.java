package com.helder.cantina;

public class Cantina {
	private static String cnpj = "40.368.914/0001-24";

	public Cantina(){
		
	}
	
	public static String getCnpj() {
		return cnpj;
	}

	public static void setCnpj(String cnpj) {
		Cantina.cnpj = cnpj;
	}
}
