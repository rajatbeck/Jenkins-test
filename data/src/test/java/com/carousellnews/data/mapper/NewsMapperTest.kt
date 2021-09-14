package com.carousellnews.data.mapper

import com.carousellnews.data.fakes.FakeNews
import com.carousellnews.data.utils.BaseTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsMapperTest : BaseTest() {

    lateinit var underTest: NewsMapper

    @Before
    fun setUp() {
        underTest = NewsMapper()
    }

    @Test
    fun `map news entity to should return to converted news`(){
        dispatcher.runBlockingTest {
            //Given
            val newsFake = FakeNews.getNews().get(0)

            //When
            val newsMapper = underTest.mapFromEntity(newsFake)

            //Then
            assertEquals(newsMapper.id,"121")


        }
    }

}