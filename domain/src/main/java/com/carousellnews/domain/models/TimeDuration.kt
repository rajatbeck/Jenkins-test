package com.carousellnews.domain.models

import com.carousellnews.domain.models.enums.Duration

data class TimeDuration(
    val duration: Duration,
    val value: Int
)
