package com.example.newsapp.utils

import com.example.newsapp.data.NewsItemData

object Constants {

    const val NETWORK_LOG = "Network log"
    const val START_PAGE_KEY = 1
    const val PAGE_SIZE_KEY = 15
    const val PLACE_PREF_TAG = "com.example.newsapp.utils.user.chosen.place"


    // Mocking data if RecyclerView works fine from UI point.
    val newsItemDataStub = arrayListOf<NewsItemData>(
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "1 European weather: Winter heat records smashed all over continent",
            "BBC News",
            "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "2 European weather: Winter heat records smashed all over continent",
            "BBC News",
            "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "3 European weather: Winter heat records smashed all over continent",
            "BBC News",
            "From Spain to Latvia, national and regional records for January are broken across the continent."
        ),
        NewsItemData(
            "https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg",
            "4 European weather: Winter heat records smashed all over continent",
            "BBC News",
            "From Spain to Latvia, national and regional records for January are broken across the continent."
        )
    )
}