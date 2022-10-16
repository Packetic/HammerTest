package com.example.hammertest.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hammertest.databinding.ItemBannerBinding

class BannerListAdapter(var listData: List<Drawable>):
    RecyclerView.Adapter<BannerListAdapter.BannerViewHolder>() {

    private lateinit var binding: ItemBannerBinding

    inner class BannerViewHolder(private val binding: ItemBannerBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Drawable) {
                binding.bannerImage.background = item
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        binding = ItemBannerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}