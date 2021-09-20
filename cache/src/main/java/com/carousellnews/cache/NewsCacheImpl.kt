package com.carousellnews.cache

import com.carousellnews.cache.dao.NewsDao
import com.carousellnews.cache.mappers.NewsCacheMappers
import com.carousellnews.data.models.NewsEntity
import com.carousellnews.data.repository.NewsCache
import javax.inject.Inject

class NewsCacheImpl
    @Inject constructor(
        private val newsDao: NewsDao,
        private val mappers: NewsCacheMappers
    ) :NewsCache {

    override suspend fun getNews(): List<NewsEntity> {
        return newsDao.getNews().map {
            mappers.mapFromCached(it)
        }
    }

    override suspend fun saveNews(list: List<NewsEntity>) {
        return newsDao.addNews(
            *list.map {
                mappers.mapToCached(it)
            }.toTypedArray()
        )
    }

    override suspend fun isCached(): Boolean {
        return newsDao.getNews().isNotEmpty()
    }
}