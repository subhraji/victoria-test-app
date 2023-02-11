package com.example.testapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        lateinit var application: Application
        fun getInstance(): Application {
            return application
        }
    }
}