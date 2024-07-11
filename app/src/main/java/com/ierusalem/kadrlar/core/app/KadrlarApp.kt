package com.ierusalem.kadrlar.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KadrlarApp:Application() {

    override fun onCreate() {
        super.onCreate()
    }

}