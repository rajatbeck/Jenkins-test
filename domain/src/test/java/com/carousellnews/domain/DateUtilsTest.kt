package com.carousellnews.domain

import com.carousellnews.domain.models.enums.Duration
import com.carousellnews.domain.utils.DateUtils
import com.carousellnews.domain.utils.DomainBaseTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.sql.Timestamp
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DateUtilsTest : DomainBaseTest() {

    private lateinit var underTest: DateUtils

    @Before
    fun setUp(){
        underTest = DateUtils()
    }


    @Test
    fun `get relative time data when timestamp for 10 seconds`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            val current_time = c.timeInMillis // Get the current time by timestamp

            val timestamp = Timestamp(current_time - (10 * 1000))
            val previousTimestamp: Long = timestamp.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.SECONDS.name, relativeDuration?.duration?.name)
            assertEquals(10, relativeDuration?.value)
        }
    }

    @Test
    fun `get relative time data when timestamp for 1 hour`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            val current_time = c.timeInMillis // Get the current time by timestamp

            val timestamp = Timestamp(current_time - 60 * 60 * 1000)
            val previousTimestamp: Long = timestamp.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.HOURS.name, relativeDuration?.duration?.name)
            assertEquals(1, relativeDuration?.value)
        }
    }


    @Test
    fun `get relative time data when timestamp for 1 mins`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            val current_time = c.timeInMillis // Get the current time by timestamp

            val timestamp = Timestamp(current_time - 60  * 1000)
            val previousTimestamp: Long = timestamp.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.MINUTES.name, relativeDuration?.duration?.name)
            assertEquals(1, relativeDuration?.value)
        }
    }


    @Test
    fun `get relative time data when timestamp for 2 day`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            val current_time = c.timeInMillis // Get the current time by timestamp

            val timestamp = Timestamp(current_time - 2 * 24 * 60 * 60 * 1000)
            val previousTimestamp: Long = timestamp.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.DAYS.name, relativeDuration?.duration?.name)
            assertEquals(2, relativeDuration?.value)
        }
    }


    @Test
    fun `get relative time data when timestamp for 3 week`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            val current_time = c.timeInMillis // Get the current time by timestamp

            val timestamp = Timestamp(current_time - 21 * 24 * 60 * 60 * 1000)
            val previousTimestamp: Long = timestamp.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.WEEK.name, relativeDuration?.duration?.name)
            assertEquals(3, relativeDuration?.value)
        }
    }


    @Test
    fun `get relative time data when timestamp for 4 months`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            c.set(Calendar.DATE,-120)
            val previousTimestamp: Long = c.time.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.MONTHS.name, relativeDuration?.duration?.name)
            assertEquals(4, relativeDuration?.value)
        }
    }

    @Test
    fun `get relative time data when timestamp for 1 year`(){
        dispatcher.runBlockingTest {
            //Given
            val c = Calendar.getInstance()
            c.set(Calendar.DATE,-400)
            val previousTimestamp: Long = c.time.time

            //When
            val relativeDuration = underTest.convertToReadableFormat(previousTimestamp)

            //Then
            assertEquals(Duration.YEAR.name, relativeDuration?.duration?.name)
            assertEquals(1, relativeDuration?.value)
        }
    }



}