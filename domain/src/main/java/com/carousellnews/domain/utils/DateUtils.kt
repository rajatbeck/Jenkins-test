package com.carousellnews.domain.utils

import com.carousellnews.domain.models.TimeDuration
import com.carousellnews.domain.models.enums.Duration
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUtils @Inject constructor() {

    fun convertToReadableFormat(timestamp: Long): TimeDuration {
        val calendar = Calendar.getInstance()
        val currentTimestamp = calendar.timeInMillis
        val diff = currentTimestamp - timestamp
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = weeks / 4
        val year = months / 12
        var timeDuration = TimeDuration(Duration.SECONDS, 0)
        if (seconds > 0) {
            timeDuration = TimeDuration(Duration.SECONDS, seconds.toInt())
        }
        if (minutes > 0) {
            timeDuration = TimeDuration(Duration.MINUTES, minutes.toInt())
        }
        if (hours > 0) {
            timeDuration = TimeDuration(Duration.HOURS, hours.toInt())
        }
        if (days > 0) {
            timeDuration = TimeDuration(Duration.DAYS, days.toInt())
        }
        if (weeks > 0) {
            timeDuration = TimeDuration(Duration.WEEK, weeks.toInt())
        }
        if (months > 0) {
            timeDuration = TimeDuration(Duration.MONTHS, months.toInt())
        }
        if (year > 0) {
            timeDuration = TimeDuration(Duration.YEAR, year.toInt())
        }
        return timeDuration
    }

    fun convertTimeDurationToTimestamp(timeDuration: TimeDuration): Long {
        val calendar = Calendar.getInstance()
        when (timeDuration.duration) {
            Duration.SECONDS -> calendar.set(Calendar.SECOND, -timeDuration.value)
            Duration.MINUTES -> calendar.set(Calendar.MINUTE, -timeDuration.value)
            Duration.HOURS -> calendar.set(Calendar.HOUR, -timeDuration.value)
            Duration.DAYS -> calendar.set(Calendar.DATE, -timeDuration.value)
            Duration.WEEK -> calendar.set(Calendar.DATE, -timeDuration.value * 4)
            Duration.MONTHS -> calendar.set(Calendar.MONTH, -timeDuration.value)
            Duration.YEAR -> calendar.set(Calendar.YEAR, -timeDuration.value)
        }
        return calendar.timeInMillis
    }

}