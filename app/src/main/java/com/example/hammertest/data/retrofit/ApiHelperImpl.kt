package com.example.hammertest.data.retrofit

import com.example.hammertest.domain.interfaces.ApiHelper
import com.example.hammertest.domain.model.Meals
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllFood(): Response<Meals> {
        return apiService.getAllFood()
    }
}