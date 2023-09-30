package com.example.vendapedidos;

import static com.example.vendapedidos.R.id.btSalvar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendapedidos.models.Produto;

public class CadastrarProdutoActivity extends AppCompatActivity {

    private EditText edCodigo;
    private EditText edDescricao;
    private EditText edValorUnitario;
    private Button btSalvar;
    private TextView tvProdutosCadastrados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        edCodigo = findViewById(R.id.edCodigo);
        edDescricao = findViewById(R.id.edDescricao);
        edValorUnitario = findViewById(R.id.edValorUnitario);
        btSalvar = findViewById(R.id.btSalvar);
        tvProdutosCadastrados = findViewById(R.id.tvProdutosCadastrados);

        atualizarListaProdutos();


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarProduto();
            }
        });

    }


    private void salvarProduto() {
        if(edCodigo.getText().toString().isEmpty()){
            edCodigo.setError("O código interno do produto, é obrigatório!");
            return;
        }
        if(Integer.parseInt(edCodigo.getText().toString())<=0){
            edCodigo.setError("Valor inválido");
            return;
        }
        if(edDescricao.getText().toString().isEmpty()){
            edDescricao.setError("Informe a descrição/nome do item!");
            return;
        }
        if(edValorUnitario.getText().toString().isEmpty()){
            edValorUnitario.setError("Informe o valor unitário do produto!");
            return;
        }
        if(Double.parseDouble(edValorUnitario.getText().toString())<=0){
            edValorUnitario.setError("Valor inválido");
            return;
        }
        Produto produto = new Produto();
        produto.setCodigoInternoProduto(Integer.parseInt(edCodigo.getText().toString()));
        produto.setDescricaoProduto(edDescricao.getText().toString());
        produto.setValorUnitarioProduto(Double.parseDouble(edValorUnitario.getText().toString()));

        Controller.getInstance().salvarProduto(produto);

        Toast.makeText(CadastrarProdutoActivity.this,
                "Pedido Cadastrado com Sucesso!!",
                Toast.LENGTH_LONG).show();

        this.finish();
    }

    private void atualizarListaProdutos(){
        String texto = "";
        for (Produto produto : Controller.getInstance().retornarProdutos()) {
            texto += "Código Interno: "+
                    produto.getCodigoInternoProduto()+"\nNome: "+
                    produto.getDescricaoProduto()+
                    "\nValor: R$ "+produto.getValorUnitarioProduto()+
                    "\n\n";
        }
        tvProdutosCadastrados.setText(texto);
    }

}