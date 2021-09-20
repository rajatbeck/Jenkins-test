package com.carousellnews.data.source

import com.carousellnews.data.fakes.FakeNews
import com.carousellnews.data.repository.NewsCache
import com.carousellnews.data.utils.BaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsCacheDataSourceTest:BaseTest() {

    @Mock
    lateinit var newsCache: NewsCache

    lateinit var underTest: NewsCacheDataSource

    @Before
    fun setUp(){
        underTest = NewsCacheDataSource(newsCache)
    }

    @Test
    fun `get news should return from local cache`(){
        dispatcher.runBlockingTest {
            //given
            Mockito.`when`(newsCache.getNews()) doReturn FakeNews.getNews()

            //When
            val news = underTest.getNews()

            //Then
            assertEquals(news.size,2)
            verify(newsCache, times(1)).getNews()
        }
    }


    @Test
    fun `get new should return error`(){
        dispatcher.runBlockingTest {
            //Given
            Mockito.`when`(newsCache.getNews()) doAnswer { throw IOException() }

            //When
            launch(exceptionHandler){
                underTest.getNews()
            }

            //then
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                CoreMatchers.instanceOf(IOException::class.java)
            )
            verify(newsCache, times(1)).getNews()
        }
    }

    @Test
    fun `save news passed news list should save in local cache`(){
        dispatcher.runBlockingTest {
            //Given
            val newsList = FakeNews.getNews()

            //When
            underTest.saveNews(newsList)

            //Then
            verify(newsCache, times(1)).saveNews(newsList)

        }
    }

    @Test
    fun `save news passed news list should return error failed to save`(){
        dispatcher.runBlockingTest {
            //Given
            val newsList = FakeNews.getNews()
            whenever(newsCache.saveNews(newsList)) doAnswer { throw IOException() }

            //When
            launch(exceptionHandler){
                underTest.saveNews(newsList)
            }

            //Then
            verify(newsCache, times(1)).saveNews(newsList)

        }
    }
}