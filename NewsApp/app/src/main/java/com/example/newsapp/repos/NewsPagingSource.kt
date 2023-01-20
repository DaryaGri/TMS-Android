package com.example.newsapp.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.Articles
import com.example.newsapp.network.ApiInterface
import com.example.newsapp.utils.Constants.START_PAGE_KEY

class NewsPagingSource(private val apiInterface: ApiInterface, private val country: String) :
    PagingSource<Int, Articles>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        return try {
            val position = params.key ?: START_PAGE_KEY
            val response = apiInterface.getAllNews(country, position)

            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Articles>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}