package com.example.newsapp.repos

import com.example.newsapp.data.DbArticles
import com.example.newsapp.db.ArticlesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepo @Inject constructor(var dbDao: ArticlesDao) {

    fun getAll(): Flow<List<DbArticles>> {
        return dbDao.getAll()
    }

    suspend fun insertArticle(article: DbArticles) {
        dbDao.insertArticle(article)
    }

    suspend fun delete(article: DbArticles) {
        dbDao.delete(article)
    }

    suspend fun getByTitle(article: DbArticles): DbArticles {
        return dbDao.getByTitle(article.title!!)
    }

}