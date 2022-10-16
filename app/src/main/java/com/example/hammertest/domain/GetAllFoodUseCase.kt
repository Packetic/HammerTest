package com.example.hammertest.domain

import retrofit2.Response

class GetAllFoodUseCase(private val apiHelper: ApiHelper) {
    suspend fun getAllFood(): Response<List<MealModel>> = apiHelper.getAllFood()
}