package com.example.newsapp.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.DbArticles
import com.example.newsapp.db.ArticlesDao

@Database(entities = [DbArticles::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}