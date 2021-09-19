package com.carousellnews.remote.models

import com.squareup.moshi.Json

data class NewsResponse(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "banner_url") val bannerUrl: String?,
    @field:Json(name = "time_created") val timeStamp: Long?,
    @field:Json(name = "rank") val rank: Int?
)