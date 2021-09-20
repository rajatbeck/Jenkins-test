package com.carousellnews.di

import android.content.Context
import com.carousellnews.cache.NewsCacheImpl
import com.carousellnews.cache.dao.NewsDao
import com.carousellnews.cache.database.NewsDatabase
import com.carousellnews.data.repository.NewsCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providesNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }

    @Provides
    @Singleton
    fun provideCharacterCache(newCache: NewsCacheImpl): NewsCache {
        return newCache
    }
}