package com.carousellnews.domain.repository

import com.carousellnews.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(): Flow<News>
}