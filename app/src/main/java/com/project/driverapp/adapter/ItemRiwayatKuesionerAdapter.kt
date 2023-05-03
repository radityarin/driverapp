package com.project.driverapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.databinding.ItemRiwayatKuesionerBinding
import com.project.driverapp.utils.toTwoNumberBehindComma
import com.project.driverapp.utils.toggleAccordion

class ItemRiwayatKuesionerAdapter :
    RecyclerView.Adapter<ItemRiwayatKuesionerAdapter.ItemRiwayatKuesionerViewHolder>() {

    private val items = mutableListOf<Kuesioner>()
    private lateinit var itemStrategiKeuanganAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiKepuasanAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiPembelajaranAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiProsesBisnisAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiSosialAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiLingkunganAdapter: ItemStrategiAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRiwayatKuesionerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemRiwayatKuesionerViewHolder(
            ItemRiwayatKuesionerBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemRiwayatKuesionerViewHolder, position: Int) {
        val model = items[position]
        if (model != null) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<Kuesioner>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ItemRiwayatKuesionerViewHolder(private val binding: ItemRiwayatKuesionerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Kuesioner) {
            with(binding) {
                tvPerspektifNama.text = model.namaKuesioner
                tvSkor.text = model.skor.toTwoNumberBehindComma()
                tvSkorKategori.text = model.skorKategori

                tvKeuangan1.text = model.indikatorKeuangan[0].toTwoNumberBehindComma()
                tvKeuangan2.text = model.indikatorKeuangan[1].toTwoNumberBehindComma()
                tvKeuangan3.text = model.indikatorKeuangan[2].toTwoNumberBehindComma()

                tvPelanggan1.text = model.indikatorKepuasan[0].toTwoNumberBehindComma()
                tvPelanggan2.text = model.indikatorKepuasan[1].toTwoNumberBehindComma()

                tvPembelajaran1.text = model.indikatorPembelajaran[0].toTwoNumberBehindComma()
                tvPembelajaran2.text = model.indikatorPembelajaran[1].toTwoNumberBehindComma()
                tvPembelajaran3.text = model.indikatorPembelajaran[2].toTwoNumberBehindComma()

                tvProsesBisnis1.text = model.indikatorProsesBisnis[0].toTwoNumberBehindComma()
                tvProsesBisnis2.text = model.indikatorProsesBisnis[1].toTwoNumberBehindComma()

                tvSosial1.text = model.indikatorSosial[0].toTwoNumberBehindComma()

                tvLingkungan1.text = model.indikatorLingkungan[0].toTwoNumberBehindComma()
                tvLingkungan2.text = model.indikatorLingkungan[1].toTwoNumberBehindComma()

                tvPerspektifKeuangan.setOnClickListener {
                    toggleAccordion(tvPerspektifKeuangan, llPerspektifKeuangan)
                }
                tvPerspektifPelanggan.setOnClickListener {
                    toggleAccordion(tvPerspektifPelanggan, llPerspektifPelanggan)
                }
                tvPerspektifPembelajaran.setOnClickListener {
                    toggleAccordion(tvPerspektifPembelajaran, llPerspektifPembelajaran)
                }
                tvPerspektifProsesBisnis.setOnClickListener {
                    toggleAccordion(tvPerspektifProsesBisnis, llPerspektifProsesBisnis)
                }
                tvPerspektifSosial.setOnClickListener {
                    toggleAccordion(tvPerspektifSosial, llPerspektifSosial)
                }
                tvPerspektifLingkungan.setOnClickListener {
                    toggleAccordion(tvPerspektifLingkungan, llPerspektifLingkungan)
                }
                tvStrategi.setOnClickListener {
                    toggleAccordion(tvStrategi, llPerspektif)
                }

                itemStrategiKeuanganAdapter = ItemStrategiAdapter()
                itemStrategiKepuasanAdapter = ItemStrategiAdapter()
                itemStrategiPembelajaranAdapter = ItemStrategiAdapter()
                itemStrategiProsesBisnisAdapter = ItemStrategiAdapter()
                itemStrategiSosialAdapter = ItemStrategiAdapter()
                itemStrategiLingkunganAdapter = ItemStrategiAdapter()
                rvStrategiKeuangan.adapter = itemStrategiKeuanganAdapter
                rvStrategiKepuasanPelanggan.adapter = itemStrategiKepuasanAdapter
                rvStrategiPembelajaranDanPertumbuhan.adapter = itemStrategiPembelajaranAdapter
                rvStrategiProsesBisnisInternal.adapter = itemStrategiProsesBisnisAdapter
                rvStrategiSosial.adapter = itemStrategiSosialAdapter
                rvStrategiProsesLingkungan.adapter = itemStrategiLingkunganAdapter

                itemStrategiKeuanganAdapter.submitList(model.strategiKeuangan)
                itemStrategiKepuasanAdapter.submitList(model.strategiKepuasan)
                itemStrategiPembelajaranAdapter.submitList(model.strategiPembelajaran)
                itemStrategiProsesBisnisAdapter.submitList(model.strategiProsesBisnis)
                itemStrategiSosialAdapter.submitList(model.strategiSosial)
                itemStrategiLingkunganAdapter.submitList(model.strategiLingkungan)
            }
        }

    }


}