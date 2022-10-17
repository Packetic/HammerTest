package com.example.hammertest.domain.usecase

import com.example.hammertest.domain.model.Meals
import com.example.hammertest.domain.interfaces.ApiHelper
import retrofit2.Response

// Юзкейс для передачи во ViewModel, нужен для соблюдения Single Responsibility
class GetAllFoodUseCase(private val apiHelper: ApiHelper) {
    suspend fun getAllFood(): Response<Meals> = apiHelper.getAllFood()
}