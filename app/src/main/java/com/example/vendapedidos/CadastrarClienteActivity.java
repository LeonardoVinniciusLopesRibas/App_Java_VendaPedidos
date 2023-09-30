package com.example.vendapedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendapedidos.models.Cliente;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edCpf;
    private Button btSalvar;
    private TextView tvClienteCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        btSalvar = findViewById(R.id.btSalvar);
        tvClienteCadastrados = findViewById(R.id.tvClienteCadastrados);

        atualizarListaCliente();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { salvarCliente();}
        });


    }

    private void salvarCliente(){

        if(edNome.getText().toString().isEmpty()){
            edNome.setError("O nome do cliente é obrigatório!");
            return;
        }

        if(edCpf.getText().toString().isEmpty()){
            edCpf.setError("O CPF do cliente é obrigatório!");
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setCpf(edCpf.getText().toString());
        cliente.setNome(edNome.getText().toString());

        Controller.getInstance().salvarCliente(cliente);

        Toast.makeText(CadastrarClienteActivity.this,
                "Cliente Cadastrado com Sucesso!!",
                Toast.LENGTH_LONG).show();

        this.finish();

    }

    private void atualizarListaCliente(){
        String texto = "";
        for (Cliente cliente : Controller.getInstance().retornarCliente()){
            texto += "Nome do Cliente: "+cliente.getNome()+"\n CPF: "+
                    cliente.getCpf()+"\n\n";
        }
        tvClienteCadastrados.setText(texto);
    }
}