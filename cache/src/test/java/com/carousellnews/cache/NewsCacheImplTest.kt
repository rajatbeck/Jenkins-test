package com.carousellnews.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carousellnews.cache.fakes.FakeCacheData
import com.carousellnews.cache.mappers.NewsCacheMappers
import com.carousellnews.cache.utils.CacheBaseTest
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class NewsCacheImplTest: CacheBaseTest() {


    private val newsCacheMappers: NewsCacheMappers = NewsCacheMappers()
    private lateinit var underTest: NewsCacheImpl

    @Before
    override fun setup() {
        super.setup()
        underTest = NewsCacheImpl(newsDao, newsCacheMappers)
    }

    @Test
    fun `get News should return success from Room cache`(){
      dispatcher.runBlockingTest {
          //Given
          val newsEntity = FakeCacheData.getFakeNews(6, true)
          underTest.saveNews(newsEntity)

          //When
          val news = newsDao.getNews()

          //then
          assertEquals(news.size,6)
      }
    }

    @Test
    fun `get news should return success news from local room cache with empty list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) no arrange

            // Act (When)
            val news = underTest.getNews()

            // Assert (Then)
            assertTrue(news.isEmpty())
        }

    @Test
    fun `is cached should return success true`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val newsEntity = FakeCacheData.getFakeNews(1, true)
            underTest.saveNews(newsEntity)

            // Act (When)
            val isCached = underTest.isCached()

            // Assert (Then)
            assertTrue(isCached)
        }

    @Test
    fun `is cached should return success false`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            // Act (When)
            val isCached = underTest.isCached()

            // Assert (Then)
            assertFalse(isCached)
        }

}