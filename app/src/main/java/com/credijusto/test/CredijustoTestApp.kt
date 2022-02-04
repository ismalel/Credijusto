package com.credijusto.test

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class CredijustoTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
