package com.carousellnews.data

import com.carousellnews.data.fakes.FakeNews
import com.carousellnews.data.mapper.NewsMapper
import com.carousellnews.data.repository.NewsDataSource
import com.carousellnews.data.source.NewsCacheDataSource
import com.carousellnews.data.source.NewsDataSourceFactory
import com.carousellnews.data.source.NewsRemoteDataSource
import com.carousellnews.data.utils.BaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest:BaseTest() {

    @Mock
    lateinit var dataSourceFactory: NewsDataSourceFactory

    @Mock
    lateinit var newsMapper: NewsMapper

    @Mock
    lateinit var dataSource: NewsDataSource

    lateinit var underTest: NewsRepositoryImpl


    @Before
    fun setUp() {
        underTest = NewsRepositoryImpl(dataSourceFactory, newsMapper)
    }

    @Test
    fun `get news with cache true should return news from local cache`(){
        dispatcher.runBlockingTest {
            //Givenr
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.getCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(dataSourceFactory.getDataStore(isCached).getNews()) doReturn FakeNews.getNews()

           //When
            val news = underTest.getNews().single()

            //Then
            verify(dataSource, times(0)).saveNews(any())
            assertEquals(news.size, 2)
            verify(dataSourceFactory, times(1)).getCacheDataSource()
            verify(dataSource, times(1)).getCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getNews()
            verify(newsMapper, times(2)).mapFromEntity(any())

        }
    }

    @Test
    fun `get news with cache false should return news from remote and save it in DB`() {
        dispatcher.runBlockingTest {
            // (Given)
            val isCached = false
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.getCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(dataSourceFactory.getDataStore(isCached).getNews()) doReturn FakeNews.getNews()

            //  (When)
            val news = underTest.getNews().single()

            //  (Then)
            assertEquals(news.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).getCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getNews()
            verify(newsMapper, times(2)).mapFromEntity(any())
            verify(dataSource, times(1)).saveNews(any())
            verify(newsMapper, times(2)).mapToEntity(anyOrNull())
        }
    }
}