package com.example.hammertest.domain

import retrofit2.Response

interface ApiHelper {
    suspend fun getAllFood(): Response<List<MealModel>>
}