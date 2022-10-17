package com.example.hammertest.data.retrofit

import com.example.hammertest.domain.model.Meals
import retrofit2.Response
import retrofit2.http.GET

// Интерфейс для общения с сетью через ретрофит
interface ApiService {
    @GET("1/filter.php?c=Seafood")
    suspend fun getAllFood(): Response<Meals>
}