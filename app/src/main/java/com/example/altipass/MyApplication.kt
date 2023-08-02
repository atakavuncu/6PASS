package com.example.altipass

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        appContext = applicationContext
    }

    companion object {
        lateinit var instance: MyApplication
        lateinit var appContext: Context
    }
}