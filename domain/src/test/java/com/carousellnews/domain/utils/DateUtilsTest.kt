package com.carousellnews.domain.utils

import com.carousellnews.domain.models.RelativeTime
import com.carousellnews.domain.models.enums.Duration
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DateUtilsTest : DomainBaseTest() {

    private lateinit var underTest: DateUtils

    @Before
    fun setUp(){
        underTest = DateUtils()
    }

    @Test
    fun `get relative time data when timestamp entered`(){

        //When
        val relativeDuration = underTest.convertToReadableFormat(1629484518)

        //Then
        assertEquals(Duration.MONTHS.name, relativeDuration?.duration?.name)
        assertEquals(1, relativeDuration?.value)
    }
}