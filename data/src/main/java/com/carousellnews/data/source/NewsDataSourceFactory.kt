package com.carousellnews.data.source

import com.carousellnews.data.repository.NewsCache
import com.carousellnews.data.repository.NewsDataSource
import javax.inject.Inject

class NewsDataSourceFactory @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val cacheDataSource: NewsCacheDataSource
) {

    open suspend fun getDataStore(isCached: Boolean): NewsDataSource {
        return if (isCached) {
            return getCacheDataSource()
        } else {
            getRemoteDataSource()
        }
    }

    fun getRemoteDataSource(): NewsDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): NewsDataSource {
        return cacheDataSource
    }
}