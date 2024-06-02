package br.igor.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import br.igor.listadecompras.adapter.ItensAdapter
import br.igor.listadecompras.model.Item
import br.igor.listadecompras.viewmodel.ItemViewModel
import java.math.BigDecimal

class MainActivity : ComponentActivity() {

    private lateinit var etNome: EditText
    private lateinit var btAdicionar: Button
    private lateinit var rvProdutos: RecyclerView
    private lateinit var etPrecoUnitario: EditText
    private lateinit var etQuantidade: EditText
    val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAllComponents()
        addEventsHandler()
        viewModel.itensLiveData.observe(this) { items ->
            (rvProdutos.adapter as ItensAdapter).updateItens(items)
        }
    }

    private fun addEventsHandler() {
        btAdicionar.setOnClickListener {
            if (!validaInformacoes()) {
                return@setOnClickListener
            }
            val nomeProduto = etNome.text.toString()
            val quantidade = etQuantidade.text.toString().toInt()
            val precoUnit = BigDecimal(etPrecoUnitario.text.toString())
            viewModel.add(nomeProduto, quantidade, precoUnit)
            etNome.text.clear()
            etQuantidade.text.clear()
            etPrecoUnitario.text.clear()

        }
    }

    private fun validaInformacoes(): Boolean {
        if(etNome.text.isNullOrBlank()) {
            with(AlertDialog.Builder(this)) {
                title = "Campo inválido"
                setMessage("Insira o nome do produto")
                    .setPositiveButton("OK") { dialog, which ->
                        // Do something.
                    }
                show()
                return false
            }
        }

        if(etQuantidade.text.isNullOrBlank()) {
            with(AlertDialog.Builder(this)) {
                title = "Campo inválido"
                setMessage("Insira a quantidade")
                    .setPositiveButton("OK") { dialog, which ->
                        // Do something.
                    }
                show()
                return false
            }
        }

        if(etPrecoUnitario.text.isNullOrBlank()) {
            with(AlertDialog.Builder(this)) {
                title = "Campo inválido"
                setMessage("Insira o preço do produto")
                    .setPositiveButton("OK") { dialog, which ->
                        // Do something.
                    }
                show()
                return false
            }
        }
        return true
    }

    private fun setupAllComponents() {
        etNome = findViewById(R.id.et_nome)
        btAdicionar = findViewById(R.id.bt_adicionar)
        rvProdutos = findViewById(R.id.rv_produtos)
        etPrecoUnitario = findViewById(R.id.et_preco_unitario)
        etQuantidade = findViewById(R.id.et_quantidade)
        rvProdutos.adapter = ItensAdapter()
    }
}
