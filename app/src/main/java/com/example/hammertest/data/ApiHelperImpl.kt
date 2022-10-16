package com.example.hammertest.data

import com.example.hammertest.domain.ApiHelper
import com.example.hammertest.domain.MealModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllFood(): Response<List<MealModel>> {
        return apiService.getAllFood()
    }
}