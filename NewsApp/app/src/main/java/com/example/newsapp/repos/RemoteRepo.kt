package com.example.newsapp.repos

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newsapp.data.Articles
import com.example.newsapp.network.ApiInterface
import com.example.newsapp.utils.Constants.PAGE_SIZE_KEY
import javax.inject.Inject

class RemoteRepo @Inject constructor(private val newsApi: ApiInterface) {

    fun getAllNews(country: String): LiveData<PagingData<Articles>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE_KEY,
                enablePlaceholders = false,
                initialLoadSize = 2),
            pagingSourceFactory = {NewsPagingSource(newsApi, country)},
            initialKey = 1
        ).liveData

    }
}