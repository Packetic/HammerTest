package com.example.hammertest.domain.model

data class Meals (
    val meals: List<MealModel>
)

data class MealModel(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)