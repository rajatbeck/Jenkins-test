package com.carousellnews.data.source

import com.carousellnews.data.utils.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsDataSourceFactoryTest:BaseTest() {


    @Mock
    lateinit var dataSourceCache: NewsCacheDataSource

    @Mock
    lateinit var dataSourceRemote: NewsRemoteDataSource

    lateinit var underTest: NewsDataSourceFactory

    @Before
    fun setUp(){
        underTest = NewsDataSourceFactory(dataSourceRemote,dataSourceCache)
    }

    @Test
    fun `get data store from cache should return from cache data source`(){
        dispatcher.runBlockingTest {
            //given
            val isCached = true
            //when
            val dataSource = underTest.getDataStore(isCached)
            //Then
            assertThat(dataSource, instanceOf(NewsCacheDataSource::class.java))

        }
    }

    @Test
    fun `get data store from should return data from remote data source`(){
        dispatcher.runBlockingTest {
            //given
            val isCached = false
            //when
            val dataSource = underTest.getDataStore(isCached)
            //Then
            assertThat(dataSource, instanceOf(NewsRemoteDataSource::class.java))

        }
    }
}