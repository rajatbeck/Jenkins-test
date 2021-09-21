package com.carousellnews.presentation.viewmodel

import androidx.lifecycle.Observer
import com.carousellnews.domain.models.enums.Sort
import com.carousellnews.domain.usecase.GetNewsListUseCase
import com.carousellnews.presentation.fake.FakeDataPresentation
import com.carousellnews.presentation.utils.PresentationBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest:PresentationBaseTest() {

    @Mock
    private lateinit var newsListUseCase: GetNewsListUseCase

    @Mock
    private lateinit var observer: Observer<NewsUIModel>

    private lateinit var underTest: NewsViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        underTest = NewsViewModel(dispatcher, newsListUseCase)
        underTest.newsList.observeForever(observer)
    }

    @Test
    fun `fetch news should return news list from use case`(){
        dispatcher.test.runBlockingTest {
            //Given
            val fakeNewsList = FakeDataPresentation.getNews(6, isRandomId = true)
            `when`(newsListUseCase.invoke(Sort.DATE)).thenReturn(flowOf(fakeNewsList))

            //When
            underTest.fetchData()

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Success(fakeNewsList))

        }
    }

    @Test
    fun `fetch news should return empty news list from use case`(){
        dispatcher.test.runBlockingTest {
            //Given
            val sort = null
            underTest.sortType = null
            val fakeNewsList = FakeDataPresentation.getNews(0,false)

            `when`(newsListUseCase.invoke(Sort.DATE)).thenReturn(flowOf(fakeNewsList))

            //When
            underTest.fetchData()

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Empty)


        }
    }

    @Test
    fun `fetch news should return an error from use case`(){
        dispatcher.test.runBlockingTest {
            //Given
            underTest.sortType = null
            val errorMessage = "Internal server error"
            whenever(newsListUseCase.invoke(Sort.DATE)) doAnswer { throw IOException(errorMessage) }

            //When
            underTest.fetchData()

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Error(errorMessage))

        }
    }

    @Test
    fun `fetch news should return success when sort type is RANK`(){
        dispatcher.test.runBlockingTest {
            underTest.sortType = Sort.DATE
            val fakeNewsList = FakeDataPresentation.getNews(6, isRandomId = true)
            `when`(newsListUseCase.invoke(Sort.RANK)).thenReturn(flowOf(fakeNewsList))

            //When
            underTest.fetchData(Sort.RANK)

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Success(fakeNewsList))
            assertEquals(underTest.sortType,Sort.RANK)
        }
    }

    @Test
    fun `fetch news should return nothing when sort type is same as previous type`(){
        dispatcher.test.runBlockingTest {
            underTest.sortType = Sort.DATE
            val fakeNewsList = FakeDataPresentation.getNews(6, isRandomId = true)

            //When
            underTest.fetchData(Sort.DATE)

            //Then
            assertEquals(underTest.sortType,Sort.DATE)
        }
    }

    @Test
    fun `fetch news should return success and sort type should be DATE if sort is NULL`(){
        dispatcher.test.runBlockingTest {
            underTest.sortType = null
            val fakeNewsList = FakeDataPresentation.getNews(6, isRandomId = true)
            `when`(newsListUseCase.invoke(Sort.DATE)).thenReturn(flowOf(fakeNewsList))

            //When
            underTest.fetchData()

            //Then
            assertEquals(underTest.sortType,Sort.DATE)
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Success(fakeNewsList))
        }
    }

    @After
    fun tearDown(){
        underTest.onCleared()
    }
}