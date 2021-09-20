package com.carousellnews.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carousellnews.cache.models.NewsCacheEntity
import com.carousellnews.cache.utils.NewsCacheConstant.NEWS_TABLE_NAME

@Dao
interface NewsDao {

    @Query("SELECT * FROM $NEWS_TABLE_NAME")
    fun getNews(): List<NewsCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(newsCacheEntity: NewsCacheEntity)
}