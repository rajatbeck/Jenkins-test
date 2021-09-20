package com.carousellnews.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carousellnews.cache.utils.NewsCacheConstant

@Entity(tableName = NewsCacheConstant.NEWS_TABLE_NAME)
data class NewsCacheEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val description: String?,
    @ColumnInfo(name = "banner_url") val bannerUrl: String?,
    @ColumnInfo(name = "time_created") val timeStamp: Long?,
    val rank: Int?
)