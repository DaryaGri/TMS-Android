package com.example.newsapp.utils

import com.example.newsapp.data.NewsItemData

object Constants {

    const val NETWORK_LOG = "Network log"

    val newsItemDataStub = arrayListOf<NewsItemData>(
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "1 European weather: Winter heat records smashed all over continent",
            "BBC News", "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "2 European weather: Winter heat records smashed all over continent",
            "BBC News", "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "3 European weather: Winter heat records smashed all over continent",
            "BBC News", "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "4 European weather: Winter heat records smashed all over continent",
            "BBC News", "From Spain to Latvia, national and regional records for January are broken across the continent."
        )
        )
}