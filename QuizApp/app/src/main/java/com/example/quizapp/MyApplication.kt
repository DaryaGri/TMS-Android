package com.example.quizapp

import android.app.Application
import com.example.quizapp.model.Repo
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}