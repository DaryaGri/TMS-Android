package com.example.newsapp.network

import com.example.newsapp.data.NewsApiRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String,
        @Query("apiKey") key: String = "6c2b2bf20a0b41058318f6d9ac964786") : Response<NewsApiRoot>


}