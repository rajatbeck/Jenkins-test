package com.carousellnews.data.source

import javax.inject.Inject

class NewsDataSourceFactory @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) {

    fun getRemoteDataSource(): NewsRemoteDataSource {
        return remoteDataSource
    }
}