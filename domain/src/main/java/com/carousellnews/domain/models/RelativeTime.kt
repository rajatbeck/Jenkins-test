package com.carousellnews.domain.models

import com.carousellnews.domain.models.enums.Duration

data class RelativeTime(
    val duration: Duration,
    val value: Int
)
