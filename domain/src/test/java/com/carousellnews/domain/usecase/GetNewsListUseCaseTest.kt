package com.carousellnews.domain.usecase

import com.carousellnews.domain.fakes.FakeData
import com.carousellnews.domain.repository.NewsRepository
import com.carousellnews.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetNewsListUseCaseTest : DomainBaseTest(){

    @Mock
    lateinit var newsRepository: NewsRepository

    lateinit var underTest: GetNewsListUseCase

    @Before
    fun setUp(){
        underTest = GetNewsListUseCase(newsRepository)
    }

    @Test
    fun `get News should return success result with news list`(){
      dispatcher.runBlockingTest {
          //Given
          whenever(newsRepository.getNews()) doReturn FakeData.getNews()

          //When
          val newsList = underTest.invoke(Unit).single()

          //Then
          assertEquals(newsList.size,3)
          verify(newsRepository, times(1)).getNews()

      }
    }

    @Test
    fun `get News should return error result with exception`(){
        dispatcher.runBlockingTest {
            //Given
            whenever(newsRepository.getNews()) doAnswer { throw IOException() }

            //When
            launch(exceptionHandler){
                underTest(Unit).single()
            }

            //then
            assertThat(exceptionHandler.uncaughtExceptions.first(),instanceOf(IOException::class.java))
            verify(newsRepository, times(1)).getNews()
        }
    }
}