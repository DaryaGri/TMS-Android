
package com.example.android.codelabs.paging.data


import java.time.LocalDateTime

private val firstArticleCreatedTime = LocalDateTime.now()

/**
 * Repository class that mimics fetching [Article] instances from an asynchronous source.
 */
class ArticleRepository {
    fun articlePagingSource() = ArticlePagingSource()
}
