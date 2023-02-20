package com.example.newsapp.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.Articles
import com.example.newsapp.data.DbArticles
import com.example.newsapp.db.NewsPreferences
import com.example.newsapp.repos.LocalRepo
import com.example.newsapp.repos.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsListViewModel @Inject constructor(
    private val remoteRepo: RemoteRepo,
    private val localRepo: LocalRepo,
    private val prefs: NewsPreferences
) : ViewModel() {

    var cityPref = MutableLiveData<String>("gb")
    val ifProgressBarVisible = MutableLiveData<Boolean>()

    fun getNewsList(): LiveData<PagingData<Articles>> {
        return cityPref.switchMap { country ->
            remoteRepo.getAllNews(country).cachedIn(viewModelScope)
        }
    }

    fun insertArticle(articles: Articles) {

        val articleToInsert = DbArticles(
            source = articles.source?.name ?: "",
            author = articles.author,
            title = articles.title,
            description = articles.description,
            url = articles.url,
            urlToImage = articles.urlToImage
        )

        viewModelScope.launch {
            localRepo.insertArticle(articleToInsert)
        }
    }

    fun loadSettings() {
        cityPref.value = prefs.getStoredTag()
    }

    fun setPrefs(region: String) {
        prefs.setStoredTag(region)
        cityPref.value = region
    }
}