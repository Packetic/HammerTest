package com.example.hammertest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hammertest.databinding.ItemFoodBinding
import com.example.hammertest.domain.MealModel
import com.example.hammertest.ui.callbacks.FoodItemDiffCallback

class FoodListAdapter() :
    ListAdapter<MealModel, FoodListAdapter.FoodViewHolder>(FoodItemDiffCallback()) {

    inner class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(receivedItem: MealModel) {
            binding.apply {
                foodHeader.text = receivedItem.mealName
                loadImage(receivedItem.mealImage)
            }
        }

        private fun loadImage(image: String?) {
            Glide.with(itemView.context)
                .load(image)
                .fitCenter()
                .into(binding.foodImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}