package com.example.roomwordsample

import android.app.Application
import com.example.roomwordsample.data.WordRepository
import com.example.roomwordsample.data.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { WordRoomDatabase.getDataBase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

}