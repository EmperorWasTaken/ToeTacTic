package com.example.toetactic

import android.app.Application

class App:Application() {

    companion object{
        lateinit var context: App private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}