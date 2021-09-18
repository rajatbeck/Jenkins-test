package com.carousellnews

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CarousellApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}