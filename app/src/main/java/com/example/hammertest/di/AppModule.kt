package com.example.hammertest.di

import com.example.hammertest.data.retrofit.ApiHelperImpl
import com.example.hammertest.data.retrofit.ApiService
import com.example.hammertest.domain.interfaces.ApiHelper
import com.example.hammertest.domain.usecase.GetAllFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Все зависимости для внедрения в классы
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesBaseUrl(): String = "https://www.themealdb.com/api/json/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideGetAllFoodUseCase(apiHelper: ApiHelperImpl): GetAllFoodUseCase =
        GetAllFoodUseCase(apiHelper)
}