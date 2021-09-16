package com.carousellnews.remote.repository

import com.carousellnews.remote.api.NewsService
import com.carousellnews.remote.fakes.FakeRemoteData
import com.carousellnews.remote.mappers.NewsEntityMapper
import com.carousellnews.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.hamcrest.MatcherAssert.assertThat
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest : RemoteBaseTest(){

    @Mock
    private lateinit var service: NewsService

    @Mock
    private lateinit var mapper: NewsEntityMapper

    lateinit var underTest: NewsRepositoryImpl

    @Before
    fun setUp(){
        underTest = NewsRepositoryImpl(service, mapper)
    }

    @Test
    fun `get news should return a response with list size 7 from remote server`(){
        dispatcher.runBlockingTest {
            //Given
            val response = FakeRemoteData.getResponse(7)
            `when`(service.getNews()) doReturn response

            //When
            val news = underTest.getNews()

            //Then
            assertEquals(news.size, 7)
            verify(mapper, times(7)).mapFromModel(any())
        }
    }

    @Test
    fun `get news should return a response with empty news list from remote server`(){
        dispatcher.runBlockingTest {
            //Given
            val response = FakeRemoteData.getResponse(0)
            `when`(service.getNews()) doReturn response

            //When
            val news = underTest.getNews()

            //Then
            assertEquals(news.size, 0)
            verify(mapper, times(0)).mapFromModel(any())
        }
    }

    @Test
    fun `get news should return error from remote server`(){
        dispatcher.runBlockingTest {

            //Given
            whenever(service.getNews()) doAnswer  { throw IOException() }

            //When
            launch(exceptionHandler) {
                underTest.getNews()
            }

            //Then
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(service, times(1)).getNews()
            }
    }
}