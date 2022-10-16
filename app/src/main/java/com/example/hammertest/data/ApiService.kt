package com.example.hammertest.data

import com.example.hammertest.domain.MealModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("filter.php?c=Seafood")
    suspend fun getAllFood(): Response<List<MealModel>>
}