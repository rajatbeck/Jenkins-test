package com.carousellnews.domain.repository

import com.carousellnews.domain.models.News
import com.carousellnews.domain.models.enums.Sort
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(sort: Sort?): Flow<List<News>>
    suspend fun saveNews(list: List<News>)
}