package br.igor.listadecompras.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.igor.listadecompras.R
import br.igor.listadecompras.model.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal
import java.text.DecimalFormat

class ItensAdapter : RecyclerView.Adapter<ItensAdapter.ItemViewHolder>() {

    private var itens = listOf<Item>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeItem = itemView.findViewById<TextView>(R.id.tv_nome_item)
        val itemPosition = itemView.findViewById<TextView>(R.id.tv_position_item)
        val quantidade = itemView.findViewById<TextView>(R.id.tv_quantidade)
        val precoUnitario = itemView.findViewById<TextView>(R.id.tv_preco_unitario)
        val precoTotal = itemView.findViewById<TextView>(R.id.tv_preco_total)
        val fabExclude = itemView.findViewById<FloatingActionButton>(R.id.fab_remove)
        fun bind(item: Item, position: Int) {
            fabExclude.setOnClickListener {
                item.onRemove
            }
            val precoTotal = item.preco.multiply(BigDecimal(item.quantidade))
            val decimalFormat = DecimalFormat("0.00")
            itemPosition.text = position.toString()
            nomeItem.text = item.name
            quantidade.text = item.quantidade.toString()
            precoUnitario.text =  "R$ ${decimalFormat.format(item.preco).replace(".", ",")}"
            this.precoTotal.text = "R$ ${decimalFormat.format(precoTotal).replace(".", ",")}"
        }

    }

    //Essa funcao eh responsavel por criar uma instancia do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    //Funcao Responsavel por retornar quantidade de itens presentes
    override fun getItemCount(): Int = itens.size

    //Funcao responsavel por fazer ligacao entre as informacoes que estao
    // na lista e os elementos do viewHolder
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itens[position]
        holder.bind(
            item,
            position
        )
    }

    fun updateItens(itens: List<Item>) {
        this.itens = itens
        notifyDataSetChanged()
    }

}