package com.example.vendapedidos;

import com.example.vendapedidos.models.Cliente;
import com.example.vendapedidos.models.Pedido;
import com.example.vendapedidos.models.Produto;

import java.util.ArrayList;

public class Controller {

    private static Controller instancia;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Pedido> listaPedido;
    private int numeroPedido;

    public static Controller getInstance(){
        if(instancia == null) {
            return instancia = new Controller();
        }else {
            return instancia;
        }
    }

    private Controller(){
        listaProdutos = new ArrayList<>();
        listaCliente = new ArrayList<>();
        listaPedido = new ArrayList<>();
        numeroPedido = 1;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void salvarProduto(Produto produto) { listaProdutos.add(produto); }
    public ArrayList<Produto> retornarProdutos() {
        return listaProdutos;
    }


    public void salvarCliente(Cliente cliente) { listaCliente.add(cliente); }
    public ArrayList<Cliente> retornarCliente() {
        return listaCliente;
    }

    public void salvarPedido(Pedido pedido) { listaPedido.add(pedido); }
    public ArrayList<Pedido> retornarPedido(){ return listaPedido;}

}
