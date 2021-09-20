package com.carousellnews.data.source

import com.carousellnews.data.fakes.FakeNews
import com.carousellnews.data.repository.NewsRemote
import com.carousellnews.data.utils.BaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.instanceOf
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
class NewsRemoteDataSourceTest:BaseTest() {

    @Mock
    private lateinit var newsRemote: NewsRemote

    lateinit var underTest: NewsRemoteDataSource

    @Before
    fun setUp(){
        underTest = NewsRemoteDataSource(newsRemote)
    }

    @Test
    fun `get news should return from remote`(){
        dispatcher.runBlockingTest {
            //Given
            Mockito.`when`(newsRemote.getNews()) doReturn FakeNews.getNews()

            //When
            val news = underTest.getNews()

            //Then
            assertEquals(news.size,2)
            verify(newsRemote, times(1)).getNews()

        }
    }

    @Test
    fun `get news should return error`(){
        dispatcher.runBlockingTest {
            //Given
            Mockito.`when`(newsRemote.getNews()) doAnswer { throw IOException() }

            //When
            launch(exceptionHandler){
                underTest.getNews()
            }

            //then
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),instanceOf(IOException::class.java)
            )
            verify(newsRemote, times(1)).getNews()
        }
    }
}