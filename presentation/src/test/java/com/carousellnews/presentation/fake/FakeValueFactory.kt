package com.carousellnews.presentation.fake

import com.carousellnews.domain.models.RelativeTime
import com.carousellnews.domain.models.enums.Duration
import java.util.UUID
import kotlin.random.Random

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    fun randomRelativePeriod(): RelativeTime {
        return when(Random.nextInt(0,7)){
           0 -> RelativeTime(Duration.SECONDS, Random.nextInt(0, 60))
           1 -> RelativeTime(Duration.MINUTES, Random.nextInt(0,60))
           2 -> RelativeTime(Duration.HOURS, Random.nextInt(0,24))
           3 -> RelativeTime(Duration.DAYS, Random.nextInt(0, 7))
           4 -> RelativeTime(Duration.WEEK, Random.nextInt(0,4))
           5 -> RelativeTime(Duration.MONTHS, Random.nextInt(0,12))
           6 -> RelativeTime(Duration.YEAR, Random.nextInt(0,20))
           else -> RelativeTime(Duration.SECONDS, 0)
       }
    }


}