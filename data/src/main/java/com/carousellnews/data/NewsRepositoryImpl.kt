package com.carousellnews.data

import com.carousellnews.data.mapper.NewsMapper
import com.carousellnews.data.source.NewsDataSourceFactory
import com.carousellnews.domain.models.News
import com.carousellnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val newsDataSourceFactory: NewsDataSourceFactory,
    private val newsMapper: NewsMapper
) : NewsRepository {

    override suspend fun getNews(): Flow<List<News>> = flow {
        val newsList = newsDataSourceFactory.getRemoteDataSource()
            .getNews()
            .map { newsEntity ->
                newsMapper.mapFromEntity(newsEntity)
            }
        emit(newsList)
    }
}