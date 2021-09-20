package com.carousellnews.domain.utils

import com.carousellnews.domain.models.RelativeTime
import com.carousellnews.domain.models.enums.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DateUtils @Inject constructor() {

    fun convertToReadableFormat(_timestamp: Long?): RelativeTime? {
        if (_timestamp == null)
            return null
        val currentTime = Calendar.getInstance().timeInMillis
        var timestamp = _timestamp
        if (_timestamp < 1000000000000L) {
            timestamp *= 1000
        }
        val earlierTime = Calendar.getInstance()
        earlierTime.timeInMillis = timestamp
        val diff = currentTime - earlierTime.timeInMillis
        val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val days = TimeUnit.MILLISECONDS.toDays(diff)
        val timeDuration: RelativeTime
        when {
            seconds < 60 -> {
                timeDuration = RelativeTime(Duration.SECONDS, seconds.toInt())
            }
            minutes < 60 -> {
                timeDuration = RelativeTime(Duration.MINUTES, minutes.toInt())
            }
            hours < 24 -> {
                timeDuration = RelativeTime(Duration.HOURS, hours.toInt())
            }
            days >= 7 -> {
                timeDuration = when {
                    days >= 365 -> {
                        val year = days / 365
                        RelativeTime(Duration.YEAR, year.toInt())
                    }
                    days >= 30 -> {
                        val month = days / 30
                        RelativeTime(Duration.MONTHS, month.toInt())
                    }
                    else -> {
                        val week = days / 7
                        RelativeTime(Duration.WEEK, week.toInt())
                    }
                }
            }
            else -> {
                timeDuration = RelativeTime(Duration.DAYS, days.toInt())
            }
        }
        return timeDuration
    }


}