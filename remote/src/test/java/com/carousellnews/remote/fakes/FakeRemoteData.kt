package com.carousellnews.remote.fakes

import com.carousellnews.remote.fakes.FakeValueFactory.randomInt
import com.carousellnews.remote.fakes.FakeValueFactory.randomLong
import com.carousellnews.remote.fakes.FakeValueFactory.randomString
import com.carousellnews.remote.models.NewsResponse

object FakeRemoteData {

    fun getResponse(size: Int, isRandom: Boolean = true): List<NewsResponse> {
        return getFakeNews(size, isRandom)
    }


    private fun getFakeNews(size: Int, isRandom: Boolean): List<NewsResponse> {
        val newsList = mutableListOf<NewsResponse>()
        repeat(size) {
            newsList.add(getNews(isRandom))
        }
        return newsList
    }

    fun getNews(isRandom:Boolean):NewsResponse{
        return NewsResponse(
            id = randomString(),
            title = randomString(),
            description = randomString(),
            bannerUrl = randomString(),
            timeStamp = randomLong(),
            rank = randomInt()
        )
    }
}