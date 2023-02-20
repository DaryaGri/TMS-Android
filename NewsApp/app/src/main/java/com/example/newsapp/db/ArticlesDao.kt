package com.example.newsapp.db

import androidx.room.*
import com.example.newsapp.data.DbArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {
    @Query("SELECT * FROM DbArticles")
    fun getAll(): Flow<List<DbArticles>>

    @Query("SELECT * FROM DbArticles WHERE title LIKE :title LIMIT 1")
    suspend fun getByTitle(title: String): DbArticles

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: DbArticles)

    @Delete
    suspend fun delete(article: DbArticles)
}