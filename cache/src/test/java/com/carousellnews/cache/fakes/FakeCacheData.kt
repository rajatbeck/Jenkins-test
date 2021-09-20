package com.carousellnews.cache.fakes

import com.carousellnews.cache.fakes.FakeValueFactory.randomInt
import com.carousellnews.cache.fakes.FakeValueFactory.randomLong
import com.carousellnews.cache.fakes.FakeValueFactory.randomString
import com.carousellnews.data.models.NewsEntity

object FakeCacheData {

     fun getFakeNews(size: Int, isRandom: Boolean): List<NewsEntity> {
        val newsList = mutableListOf<NewsEntity>()
        repeat(size) {
            newsList.add(getNews(isRandom))
        }
        return newsList
    }

    fun getNews(isRandom:Boolean):NewsEntity{
        return NewsEntity(
            id = randomString(),
            title = randomString(),
            description = randomString(),
            bannerUrl = randomString(),
            timeStamp = randomLong(),
            rank = randomInt()
        )
    }
}