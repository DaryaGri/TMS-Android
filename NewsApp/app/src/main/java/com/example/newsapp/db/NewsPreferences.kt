package com.example.newsapp.db

import android.content.Context
import com.example.newsapp.utils.Constants.PLACE_PREF_TAG
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPreferences @Inject constructor(@ApplicationContext context: Context) {

    val prefs = context.getSharedPreferences("NewsPrefs", Context.MODE_PRIVATE)

    fun getStoredTag(): String {
        return prefs.getString(PLACE_PREF_TAG, "gb")!!
    }

    fun setStoredTag(query: String) {
        prefs.edit().putString(PLACE_PREF_TAG, query).apply()
    }
}