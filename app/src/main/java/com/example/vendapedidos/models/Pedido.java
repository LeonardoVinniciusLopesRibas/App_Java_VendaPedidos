package com.example.vendapedidos.models;

public class Pedido {
    private int codigoPedido;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double valorUnitario;
    private boolean isAPrazo;
    private double vlTotal;

    public Pedido() {
    }

    public Pedido(int codigoPedido, Cliente cliente, Produto produto, int quantidade, double valorUnitario, boolean isAPrazo, double vlTotal) {
        this.codigoPedido = codigoPedido;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.isAPrazo = isAPrazo;
        this.vlTotal = vlTotal;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public boolean isAPrazo() {
        return isAPrazo;
    }

    public void setAPrazo(boolean APrazo) {
        isAPrazo = APrazo;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }
}
