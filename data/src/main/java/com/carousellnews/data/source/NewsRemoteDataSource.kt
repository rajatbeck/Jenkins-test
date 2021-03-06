package com.carousellnews.data.source

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.data.repository.NewsDataSource
import com.carousellnews.data.repository.NewsRemote
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsRemote: NewsRemote
) : NewsDataSource {

    override suspend fun getNews(): List<NewsEntity> {
        return newsRemote.getNews()
    }

    override suspend fun saveNews(list: List<NewsEntity>) {
        throw UnsupportedOperationException("Save news is not supported for RemoteDataSource.")
    }

    override suspend fun getCached(): Boolean {
        throw UnsupportedOperationException("get cached is not supported for RemoteDataSource.")
    }
}