package com.carousellnews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarousellApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}