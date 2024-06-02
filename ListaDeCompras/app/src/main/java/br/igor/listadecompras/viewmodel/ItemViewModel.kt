package br.igor.listadecompras.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.igor.listadecompras.model.Item
import java.math.BigDecimal

class ItemViewModel : ViewModel() {
    private val itens = mutableListOf<Item>()
    val itensLiveData = MutableLiveData<List<Item>>()


    fun add(nome: String, quantidade: Int, preco: BigDecimal) {
        itens.add(
            Item(
                name = nome,
                quantidade = quantidade,
                preco = preco,
                onRemove = ::remove
            )
        )
        itensLiveData.value = itens
    }

    private fun remove(item: Item) {
        itens.remove(item)
        itensLiveData.value = itens
    }
}