package com.project.driverapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.data.logic.KuesionerByDate
import com.project.driverapp.databinding.ItemRiwayatBinding
import com.project.driverapp.utils.toggleAccordion

class RiwayatAdapter : RecyclerView.Adapter<RiwayatAdapter.OnBoardingViewHolder>() {

    private val items = mutableListOf<KuesionerByDate>()
    private lateinit var itemRiwayatKuesionerAdapter: ItemRiwayatKuesionerAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OnBoardingViewHolder(ItemRiwayatBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val model = items[position]
        if (model != null) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    fun submitLists(data: List<Kuesioner>) {
        val listTanggal = data.map { it.tanggal }.distinct()
        val listKuesioner = mutableListOf<KuesionerByDate>()
        listTanggal.forEach { tanggal ->
            val kuesionerByDate = data.filter {
                it.tanggal == tanggal
            }
            listKuesioner.add(
                KuesionerByDate(
                    tanggal = tanggal,
                    listKuesioner = kuesionerByDate
                )
            )
        }

        items.clear()
        items.addAll(listKuesioner)
        notifyDataSetChanged()
    }

    inner class OnBoardingViewHolder(private val binding: ItemRiwayatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: KuesionerByDate) {
            with(binding) {
                itemRiwayatKuesionerAdapter = ItemRiwayatKuesionerAdapter()
                rvListKuesionerPerTanggal.adapter = itemRiwayatKuesionerAdapter
                tvRiwayatTanggal.text = model.tanggal
                itemRiwayatKuesionerAdapter.submitList(model.listKuesioner)
                tvRiwayatTanggal.setOnClickListener {
                    toggleAccordion(tvRiwayatTanggal, rvListKuesionerPerTanggal)
                }
            }
        }

    }


}