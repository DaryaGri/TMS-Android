package com.example.newsapp.db

import androidx.room.*
import com.example.newsapp.data.DbArticles

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM DbArticles")
    suspend fun getAll(): List <DbArticles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: DbArticles)

    @Delete
    suspend fun delete(article: DbArticles)
}