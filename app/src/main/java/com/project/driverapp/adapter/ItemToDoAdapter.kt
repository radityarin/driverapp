package com.project.driverapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.driverapp.data.logic.ToDo
import com.project.driverapp.data.logic.ToDoStatus
import com.project.driverapp.databinding.ItemToDoListBinding
import com.project.driverapp.utils.hideView
import com.project.driverapp.utils.showView

class ItemToDoAdapter(val onItemClick: (ToDo) -> Unit) :
    RecyclerView.Adapter<ItemToDoAdapter.ItemToDoViewHolder>() {

    private val items = mutableListOf<ToDo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemToDoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemToDoViewHolder(
            ItemToDoListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemToDoViewHolder, position: Int) {
        val model = items[position]
        if (model != null) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<ToDo>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    fun updateStatus(id: Int, status: ToDoStatus) {
        items.find { it.id == id }?.status = status
        notifyDataSetChanged()
    }

    inner class ItemToDoViewHolder(private val binding: ItemToDoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ToDo) {
            with(binding) {
                val fromDestination = model.from + " - " + model.destination
                tvFromDestination.text = fromDestination
                tvStartDate.text = model.startDate
                tvEndDate.text = model.endDate
                when (model.status) {
                    ToDoStatus.ACTIVE -> {
                        tvEnabled.text = "Terima"
                    }
                    ToDoStatus.NON_ACTIVE -> {
                        tvEnabled.text = "Terima"
                        tvEnabled.hideView()
                        tvDisabled.showView()
                    }
                    ToDoStatus.ONGOING -> {
                        tvEnabled.text = "Berlangsung"
                    }
                    ToDoStatus.FINISH -> {
                        tvEnabled.hideView()
                        tvDisabled.showView()
                        tvDisabled.text = "Selesai"
                    }
                }
                binding.root.setOnClickListener {
                    onItemClick.invoke(model)
                }
            }
        }

    }


}