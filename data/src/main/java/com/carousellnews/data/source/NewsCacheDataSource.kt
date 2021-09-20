package com.carousellnews.data.source

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.data.repository.NewsCache
import com.carousellnews.data.repository.NewsDataSource
import javax.inject.Inject

class NewsCacheDataSource @Inject constructor(
    private val newsCache: NewsCache
) : NewsDataSource {
    override suspend fun getNews(): List<NewsEntity> {
        return newsCache.getNews()
    }

    override suspend fun saveNews(list: List<NewsEntity>) {
        return newsCache.saveNews(list)
    }

    override suspend fun getCached(): Boolean {
        return newsCache.isCached()
    }

}