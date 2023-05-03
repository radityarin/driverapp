package com.project.driverapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.driverapp.databinding.ItemOnboardingBinding
import com.project.driverapp.domain.model.OnBoarding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val items = mutableListOf<OnBoarding?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val model = items[position]
        if (model != null) {
            holder.bind(model)
        }
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<OnBoarding?>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: OnBoarding) {
            with(binding) {
                tvTitleOnBoarding.text = model.title
                tvContentOnBoarding.text = model.content
                Glide.with(itemView.context)
                    .load(model.drawableId)
                    .into(ivDrawableOnBoarding)
            }
        }

    }


}