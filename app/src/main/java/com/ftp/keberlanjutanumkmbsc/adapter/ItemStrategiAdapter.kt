package com.ftp.keberlanjutanumkmbsc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftp.keberlanjutanumkmbsc.data.logic.Strategi
import com.ftp.keberlanjutanumkmbsc.databinding.ItemStrategiBinding

class ItemStrategiAdapter :
    RecyclerView.Adapter<ItemStrategiAdapter.ItemStrategiViewHolder>() {

    private val items = mutableListOf<Strategi>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemStrategiViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemStrategiViewHolder(
            ItemStrategiBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemStrategiViewHolder, position: Int) {
        val model = items[position]
        if (model != null) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<String>) {
        val listData = mutableListOf<Strategi>()
        data.forEachIndexed { index, s ->
            listData.add(
                Strategi(
                    number = (index + 1).toString(),
                    strategi = s
                )
            )
        }
        items.clear()
        items.addAll(listData)
        notifyDataSetChanged()
    }

    inner class ItemStrategiViewHolder(private val binding: ItemStrategiBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Strategi) {
            with(binding) {
                val number = model.number + "."
                tvNumber.text = number
                tvStrategi.text = model.strategi
            }
        }

    }


}