package br.igor.listadecompras.model

import java.math.BigDecimal

data class Item(
    val name: String,
    val quantidade: Int,
    val preco: BigDecimal,
    val onRemove: (Item) -> Unit
) {
}