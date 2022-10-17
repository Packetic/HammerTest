package com.example.hammertest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
// Базовый класс приложения
class HammerTest : Application() {

    companion object {
        private lateinit var instance: HammerTest
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}