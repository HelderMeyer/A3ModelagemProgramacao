package com.helder.cantina;

public abstract class Lanche {
    
    private int ID;
    private String nome;
    private double valor;        
    
    public Lanche (int ID, String nome, double valor){

        this.ID = ID;
        this.nome = nome;
        this.valor = valor;

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

}
