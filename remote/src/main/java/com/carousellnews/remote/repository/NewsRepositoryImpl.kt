package com.carousellnews.remote.repository

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.data.repository.NewsRemote
import com.carousellnews.remote.api.NewsService
import com.carousellnews.remote.mappers.NewsEntityMapper
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService,
    private val newsEntityMappers: NewsEntityMapper

):NewsRemote {

    override suspend fun getNews(): List<NewsEntity> {
       return service.getNews().map {
           newsEntityMappers.mapFromModel(it)
        }
    }
}