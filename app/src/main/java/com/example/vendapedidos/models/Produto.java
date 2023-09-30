package com.example.vendapedidos.models;

public class Produto {

    private int codigoInternoProduto;
    private String descricaoProduto;
    private double valorUnitarioProduto;

    public Produto() {
    }

    public Produto(int codigoInternoProduto, String descricaoProduto, double valorUnitarioProduto) {
        this.codigoInternoProduto = codigoInternoProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorUnitarioProduto = valorUnitarioProduto;
    }

    public int getCodigoInternoProduto() {
        return codigoInternoProduto;
    }

    public void setCodigoInternoProduto(int codigoInternoProduto) {
        this.codigoInternoProduto = codigoInternoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(double valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }
}
