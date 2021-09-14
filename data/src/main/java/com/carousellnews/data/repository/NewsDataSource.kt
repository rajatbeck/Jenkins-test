package com.carousellnews.data.repository

import com.carousellnews.data.models.NewsEntity

interface NewsDataSource {
    suspend fun getNews(): List<NewsEntity>
}