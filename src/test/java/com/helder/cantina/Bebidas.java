package com.helder.cantina;

public class Bebidas extends Lanche{
    private String sabor;

    public Bebidas(int ID, String nome, double valor, String sabor){
        super(ID, nome, valor);
        this.sabor = sabor;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
