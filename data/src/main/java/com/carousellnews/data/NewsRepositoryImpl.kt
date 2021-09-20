package com.carousellnews.data

import com.carousellnews.data.mapper.NewsMapper
import com.carousellnews.data.source.NewsDataSourceFactory
import com.carousellnews.domain.models.News
import com.carousellnews.domain.models.enums.Sort
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
        val isCached = newsDataSourceFactory.getCacheDataSource().getCached()
        val newsList = newsDataSourceFactory.getDataStore(isCached).getNews()
            .map { newsEntity ->
                newsMapper.mapFromEntity(newsEntity)
            }
        if(isCached.not()){
            saveNews(newsList)
        }
        emit(newsList)
    }

    override suspend fun saveNews(list: List<News>) {
        val newsEntities = list.map {news->
            newsMapper.mapToEntity(news)
        }
        newsDataSourceFactory.getCacheDataSource().saveNews(newsEntities)
    }


}