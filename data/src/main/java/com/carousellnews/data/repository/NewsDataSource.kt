package com.carousellnews.data.repository

import com.carousellnews.data.models.NewsEntity

interface NewsDataSource {

    //Remote and Cache
    suspend fun getNews(): List<NewsEntity>

    //cache
    suspend fun saveNews(list: List<NewsEntity>)
    suspend fun getCached(): Boolean
}