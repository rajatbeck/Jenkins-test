package com.carousellnews.remote.models

import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "id") val id: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "bannerUrl") val bannerUrl: String?,
    @Json(name = "timeStamp") val timeStamp: Long?,
    @Json(name = "rank") val rank: Int?
)