package com.example.hammertest.domain.interfaces

import com.example.hammertest.domain.model.Meals
import retrofit2.Response

// Интерфейс для распаковки по юзкейсам, реализация лежит в Data. Таким образом domain не зависит
// от data
interface ApiHelper {
    suspend fun getAllFood(): Response<Meals>
}