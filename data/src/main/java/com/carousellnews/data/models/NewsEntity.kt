package com.carousellnews.data.models

class NewsEntity(
    val id: String,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val timeStamp: Long,
    val rank: Int
)