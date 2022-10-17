package com.example.hammertest.ui.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.hammertest.domain.model.MealModel

class FoodItemDiffCallback : DiffUtil.ItemCallback<MealModel>() {
    override fun areItemsTheSame(
        oldItem: MealModel,
        newItem: MealModel
    ): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealModel, newItem: MealModel): Boolean {
        return oldItem == newItem
    }
}