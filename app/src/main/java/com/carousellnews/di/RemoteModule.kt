package com.carousellnews.di

import com.carousellnews.BuildConfig
import com.carousellnews.data.repository.NewsRemote
import com.carousellnews.remote.api.NewsService
import com.carousellnews.remote.api.ServiceFactory
import com.carousellnews.remote.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesNewsService():NewsService{
        return ServiceFactory.create(BuildConfig.DEBUG,BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideNewsRemote(newsRepositoryImpl: NewsRepositoryImpl): NewsRemote {
        return newsRepositoryImpl
    }


}