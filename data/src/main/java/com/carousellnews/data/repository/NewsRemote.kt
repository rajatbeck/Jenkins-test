package com.carousellnews.data.repository

import com.carousellnews.data.models.NewsEntity

interface NewsRemote {
    suspend fun getNews(): List<NewsEntity>
}