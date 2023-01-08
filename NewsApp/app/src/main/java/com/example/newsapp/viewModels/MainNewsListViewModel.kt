package com.example.newsapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsApiRoot
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.repos.RemoteRepo
import com.example.newsapp.utils.Constants.NETWORK_LOG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsListViewModel @Inject constructor(private val repo: RemoteRepo) : ViewModel() {

    val newsData = MutableLiveData<NewsApiRoot>()
    val dataForRecycler = MutableLiveData<ArrayList<NewsItemData>>()

    fun fetchNews() {
        viewModelScope.launch {
            val response = repo.getAllNews()
            if (response.isSuccessful) {
                newsData.postValue(response.body())
            } else {
                Log.d(
                    NETWORK_LOG,
                    "fetchNews: code: ${response.code()} message: ${response.message()} "
                )
            }
        }
    }

    fun updateDataForRecycler(newData: NewsApiRoot) {
        val newList = ArrayList<NewsItemData>()
        for (item in newData.articles) {
            val newItem = NewsItemData(
                imgSrc = item.urlToImage ?: "https://i.pinimg.com/736x/5d/7f/42/5d7f4209322f03a721835eeef811fb6a--sad-faces-visual-design.jpg",
                title = item.title ?: "",
                author = item.author ?: "",
                content = item.description ?: ""
            )
            newList.add(newItem)
        }
        dataForRecycler.postValue(newList)
    }
}