package com.helder.cantina;

public class Salgado{
    private String igredientes;

    public Salgado(int ID, String nome, double valor, String igredientes){
        this.igredientes = igredientes;
    }

    public String getIgredientes() {
        return igredientes;
    }

    public void setIgredientes(String igredientes) {
        this.igredientes = igredientes;
    }
}