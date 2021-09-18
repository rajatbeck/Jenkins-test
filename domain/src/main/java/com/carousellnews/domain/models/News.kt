package com.carousellnews.domain.models

data class News(
    val id: String?,
    val title: String?,
    val description: String?,
    val bannerUrl: String?,
    val relativeTime: RelativeTime?,
    val rank: Int?
)
