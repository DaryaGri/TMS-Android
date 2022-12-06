package com.example.quizapp

import android.app.Application
import com.example.quizapp.model.Repo

class MyApplication : Application() {

    lateinit var repo: Repo

    override fun onCreate() {
        super.onCreate()

        repo = Repo()
    }
}