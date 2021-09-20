package com.carousellnews.data.repository

import com.carousellnews.data.models.NewsEntity

interface NewsCache {
    suspend fun getNews():List<NewsEntity>
    suspend fun saveNews(list: List<NewsEntity>)
    suspend fun isCached(): Boolean
}