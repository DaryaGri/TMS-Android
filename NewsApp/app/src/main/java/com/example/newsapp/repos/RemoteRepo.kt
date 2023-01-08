package com.example.newsapp.repos

import com.example.newsapp.network.ApiInterface
import javax.inject.Inject

class RemoteRepo(private val newsApi: ApiInterface) {

    suspend fun getAllNews(country: String = "gb") = newsApi.getAllNews(country)
}