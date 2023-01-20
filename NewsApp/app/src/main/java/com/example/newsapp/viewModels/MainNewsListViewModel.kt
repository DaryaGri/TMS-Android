package com.example.newsapp.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.Articles
import com.example.newsapp.repos.RemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNewsListViewModel @Inject constructor(private val repo: RemoteRepo) : ViewModel() {

    var cityPref = MutableLiveData <String>("gb")
    val ifProgressBarVisible = MutableLiveData <Boolean>()

    fun getNewsList(): LiveData <PagingData<Articles>>{
        return cityPref.switchMap { country -> repo.getAllNews(country).cachedIn(viewModelScope)}
    }
}