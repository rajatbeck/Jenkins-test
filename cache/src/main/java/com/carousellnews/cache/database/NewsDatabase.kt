package com.carousellnews.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carousellnews.cache.dao.NewsDao
import com.carousellnews.cache.models.NewsCacheEntity
import com.carousellnews.cache.utils.NewsCacheConstant
import javax.inject.Inject

@Database(
    entities = [NewsCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            NewsCacheConstant.DB_NAME
        ).build()
    }
}