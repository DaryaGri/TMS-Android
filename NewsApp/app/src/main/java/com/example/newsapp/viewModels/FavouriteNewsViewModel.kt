package com.example.newsapp.viewModels

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.DbArticles
import com.example.newsapp.repos.LocalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteNewsViewModel @Inject constructor(private val repo: LocalRepo) : ViewModel() {

    fun doStuff() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertArticle(
                DbArticles(
                    "BBC",
                    "Bob",
                    "Cat stuck on the tree",
                    "Cat had stuck on thr tree but firefighters saved it",
                    "https://www.oakhurstvet.com/blog/wp-content/uploads/2020/10/Oakhurst_iStock-1155329213-1.jpg",
                    "https://www.oakhurstvet.com/blog/wp-content/uploads/2020/10/Oakhurst_iStock-1155329213-1.jpg",
                    0
                )
            )
        }
    }
}