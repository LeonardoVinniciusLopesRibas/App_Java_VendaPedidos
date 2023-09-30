package com.example.vendapedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vendapedidos.models.Cliente;
import com.example.vendapedidos.models.Pedido;
import com.example.vendapedidos.models.Produto;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CadastrarPedidoActivity extends AppCompatActivity {

    private TextView tvSeqNumeroPedido;
    private Spinner spCliente;
    private Spinner spProduto;
    private EditText edQtdProdutos;
    private EditText edVlUniProdutos;
    private Button btAddProdutoPedido;
    private TextView tvListaDisciplinas;
    private RadioGroup rgFormaPagamento;
    private RadioButton rbAVista;
    private RadioButton rbAPrazo;
    private TextView tvAVista;
    private TextView tvAPrazo;
    private LinearLayout layoutParcelas;
    private EditText edQuantidadeParcelas;
    private Button btSalvarPedido;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Produto> listaProdutos;
    private int posicaoClienteSelecionado;
    private int posicaoProdutoSelecionado = -1;
    private int quantidade;
    private double valorUnitario;
    private TextView tvPedido;
    private double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pedido);

        tvSeqNumeroPedido = findViewById(R.id.tvSeqNumeroPedido);
        spCliente = findViewById(R.id.spCliente);
        spProduto = findViewById(R.id.spProduto);
        edQtdProdutos = findViewById(R.id.edQtdProdutos);
        edVlUniProdutos = findViewById(R.id.edVlUniProdutos);
        btAddProdutoPedido = findViewById(R.id.btAddProdutoPedido);
        tvListaDisciplinas = findViewById(R.id.tvListaDisciplinas);
        rgFormaPagamento = findViewById(R.id.rgFormaPagamento);
        rbAVista = findViewById(R.id.rbAVista);
        rbAPrazo = findViewById(R.id.rbAPrazo);
        tvAVista = findViewById(R.id.tvAVista);
        tvAPrazo = findViewById(R.id.tvAPrazo);
        layoutParcelas = findViewById(R.id.layoutParcelas);
        edQuantidadeParcelas = findViewById(R.id.edQuantidadeParcelas);
        btSalvarPedido = findViewById(R.id.btSalvarPedido);
        tvPedido = findViewById(R.id.tvPedido);

        listaClientes = Controller.getInstance().retornarCliente();
        listaProdutos = Controller.getInstance().retornarProdutos();

        carregarClientes();
        carregarProdutos();
        atualizarListaPedido();

        spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if (posicao > 0) {
                    posicaoClienteSelecionado = posicao;
                } else {
                    posicaoClienteSelecionado = -1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                posicaoClienteSelecionado = -1;
            }
        });

        spProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                posicaoProdutoSelecionado = posicao;
                if (posicao >= 0) {
                    if (posicao > 0) {
                        edVlUniProdutos.setText(String.valueOf(listaProdutos.get(posicao - 1).getValorUnitarioProduto()));
                    } else {
                        edVlUniProdutos.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                posicaoProdutoSelecionado = -1;
            }
        });


        btAddProdutoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarProdutoAoPedido();
            }
        });

        rgFormaPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                atualizarValoresFormaPagamento();
            }
        });

        btSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPedido();
            }
        });

        edQuantidadeParcelas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!edQuantidadeParcelas.getText().toString().isEmpty()) {
                        int quantidadeParcelas = Integer.parseInt(edQuantidadeParcelas.getText().toString());

                        double total = calcularValorTotal();
                        double valorParcela = (total + (total * 0.05)) / quantidadeParcelas;
                        //valorParcela = Math.round(valorParcela * 100.0) / 100.0;

                        String textoParcelas = "Valor por parcela (" +
                                quantidadeParcelas + " parcelas): R$ " + valorParcela;

                        tvAPrazo.setText(textoParcelas);
                        layoutParcelas.setVisibility(View.VISIBLE);
                    } else {
                        tvAPrazo.setText("Informe a quantidade de parcelas.");
                        layoutParcelas.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        tvSeqNumeroPedido.setText("N°: "+ Controller.getInstance().getNumeroPedido());


    }

    private void carregarClientes() {
        listaClientes = Controller.getInstance().retornarCliente();
        String[] nomesClientes = new String[listaClientes.size() + 1];
        nomesClientes[0] = "Selecione um cliente";
        for (int i = 0; i < listaClientes.size(); i++) {
            nomesClientes[i + 1] = listaClientes.get(i).getNome();
        }

        ArrayAdapter<String> adapterClientes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomesClientes);
        adapterClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCliente.setAdapter(adapterClientes);
    }

    private void carregarProdutos() {
        listaProdutos = Controller.getInstance().retornarProdutos();
        String[] nomesProdutos = new String[listaProdutos.size() + 1];
        nomesProdutos[0] = "Selecione um produto";
        for (int i = 0; i < listaProdutos.size(); i++) {
            nomesProdutos[i + 1] = listaProdutos.get(i).getDescricaoProduto();
        }

        ArrayAdapter<String> adapterProdutos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomesProdutos);
        adapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduto.setAdapter(adapterProdutos);

    }

    private void adicionarProdutoAoPedido() {
        if (posicaoClienteSelecionado <= 0) {
            Toast.makeText(this, "Selecione um cliente válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (posicaoProdutoSelecionado <= 0) {
            Toast.makeText(this, "Selecione um produto válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edQtdProdutos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe a quantidade de produtos.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edVlUniProdutos.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe o valor unitário.", Toast.LENGTH_SHORT).show();
            return;
        }

        quantidade = Integer.parseInt(edQtdProdutos.getText().toString());
        valorUnitario = Double.parseDouble(edVlUniProdutos.getText().toString());
        double valorTotal = quantidade * valorUnitario;

        String produtoSelecionado = spProduto.getSelectedItem().toString();

        String textoPedido = "Produto: " + produtoSelecionado +
                "\nQuantidade: " + quantidade +
                "\nValor Unitário: R$ " + valorUnitario +
                "\nValor Total: R$ " + valorTotal + "\n\n";

        tvListaDisciplinas.append(textoPedido);

        posicaoProdutoSelecionado = -1;
        spProduto.setSelection(0);
        edQtdProdutos.getText().clear();
        edVlUniProdutos.getText().clear();
    }


    private void atualizarValoresFormaPagamento() {
        double total = calcularValorTotal();

        if (rbAVista.isChecked()) {
            tvAVista.setText("Valor: R$ " + (total - (total * 0.05)) + " (à vista)");
            tvAPrazo.setText("");
            layoutParcelas.setVisibility(View.GONE);
        } else if (rbAPrazo.isChecked()) {
            tvAVista.setText("");

            if (!edQuantidadeParcelas.getText().toString().isEmpty()) {
                int quantidadeParcelas = Integer.parseInt(edQuantidadeParcelas.getText().toString());

                double valorParcela = total / quantidadeParcelas;

                valorParcela = Math.round((valorParcela * 0.05));

                String textoParcelas = "Valor por parcela ("
                        + quantidadeParcelas + " parcelas): R$ " + valorParcela;

                tvAPrazo.setText(textoParcelas);
                layoutParcelas.setVisibility(View.VISIBLE);
            } else {
                tvAPrazo.setText("Informe a quantidade de parcelas.");
                layoutParcelas.setVisibility(View.VISIBLE);
            }
        }
    }



    private double calcularValorTotal() {

        total += quantidade * valorUnitario;
        return total;

    }


    private void salvarPedido() {
        if (posicaoClienteSelecionado <= 0) {
            Toast.makeText(this, "Selecione um cliente válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rbAPrazo.isChecked() && edQuantidadeParcelas.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe a quantidade de parcelas para pagamento a prazo.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(Controller.getInstance().getNumeroPedido());
        pedido.setCliente(listaClientes.get(posicaoClienteSelecionado-1));

        Controller.getInstance().salvarPedido(pedido);

        Toast.makeText(CadastrarPedidoActivity.this,
                "Pedido Cadastrado com Sucesso!!",
                Toast.LENGTH_LONG).show();
        this.finish();
    }

    public void atualizarListaPedido(){
        String texto = "";
        for (Pedido pedido : Controller.getInstance().retornarPedido()){
            texto += "Código do Pedido: "+pedido.getCodigoPedido()+
                    "\nCliente: "+pedido.getCliente().getNome();
        }
        tvPedido.setText(texto);
    }



}
