package com.example.newsapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Articles
import com.example.newsapp.data.DbArticles
import com.example.newsapp.repos.LocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteNewsViewModel @Inject constructor(private val localRepo: LocalRepo) : ViewModel() {

    fun deleteArticle(articles: Articles) {
        val articleToSearch = DbArticles(
            source = articles.source?.name ?: "",
            author = articles.author,
            title = articles.title,
            description = articles.description,
            url = articles.url,
            urlToImage = articles.urlToImage
        )

        viewModelScope.launch {
            val elementToDelete = localRepo.getByTitle(articleToSearch)
            localRepo.delete(elementToDelete)
        }
    }

    suspend fun getAll(): Flow<List<DbArticles>> {
        return localRepo.getAll()
    }
}