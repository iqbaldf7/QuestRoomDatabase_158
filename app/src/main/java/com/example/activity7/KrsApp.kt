package com.example.activity7

import android.app.Application
import com.example.activity7.dependeciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)

    }
}