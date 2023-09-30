package com.example.vendapedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vendapedidos.models.Cliente;
import com.example.vendapedidos.models.Pedido;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrarCliente;
    private Button btCadastrarProduto;
    private Button btCadastrarPedido;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btCadastrarPedido = findViewById(R.id.btCadastrarPedido);
        btCadastrarProduto = findViewById(R.id.btCadastrarProduto);


        btCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {abrirActivity(CadastrarProdutoActivity.class);
            }
        });

        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {abrirActivity(CadastrarClienteActivity.class);
            }
        });

        btCadastrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {abrirActivity(CadastrarPedidoActivity.class);
            }
        });


    }
    private void abrirActivity (Class<?> activity){
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }


}