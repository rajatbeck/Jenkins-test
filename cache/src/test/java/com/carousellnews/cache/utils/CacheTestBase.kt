package com.carousellnews.cache.utils

import android.content.Context
import androidx.room.Room
import com.carousellnews.cache.dao.NewsDao
import com.carousellnews.cache.database.NewsDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

@ExperimentalCoroutinesApi
abstract class CacheBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    val exceptionHandler = TestCoroutineExceptionHandler()
    private lateinit var database: NewsDatabase
    protected lateinit var newsDao: NewsDao
    protected lateinit var context: Context

    @Before
    open fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
            .allowMainThreadQueries().build()
        newsDao = database.newsDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.runBlockingTest {
            database.clearAllTables()
        }
        database.close()
    }
}