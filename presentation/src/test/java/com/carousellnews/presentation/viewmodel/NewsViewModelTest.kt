package com.carousellnews.presentation.viewmodel

import androidx.lifecycle.Observer
import com.carousellnews.domain.usecase.GetNewsListUseCase
import com.carousellnews.presentation.fake.FakeDataPresentation
import com.carousellnews.presentation.utils.PresentationBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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
            `when`(newsListUseCase.invoke(Unit)).thenReturn(flowOf(fakeNewsList))

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
            val fakeNewsList = FakeDataPresentation.getNews(0,false)

            `when`(newsListUseCase.invoke(Unit)).thenReturn(flowOf(fakeNewsList))

            //When
            underTest.fetchData()

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Success(fakeNewsList))


        }
    }

    @Test
    fun `fetch news should return an error from use case`(){
        dispatcher.test.runBlockingTest {
            //Given
            val errorMessage = "Internal server error"
            whenever(newsListUseCase.invoke(Unit)) doAnswer { throw IOException(errorMessage) }

            //When
            underTest.fetchData()

            //Then
            verify(observer).onChanged(NewsUIModel.Loading)
            verify(observer).onChanged(NewsUIModel.Error(errorMessage))

        }
    }

    @After
    fun tearDown(){
        underTest.onCleared()
    }
}