package com.carousellnews.presentation.fake

import com.carousellnews.domain.models.News
import com.carousellnews.presentation.fake.FakeValueFactory.randomInt
import com.carousellnews.presentation.fake.FakeValueFactory.randomLong
import com.carousellnews.presentation.fake.FakeValueFactory.randomRelativePeriod
import com.carousellnews.presentation.fake.FakeValueFactory.randomString

object FakeDataPresentation {

    fun getNews(
        size: Int,
        isRandomId: Boolean = true
    ): List<News> {
        val list = mutableListOf<News>()
        repeat(size) {
            list.add(createNews(isRandomId))
        }
        return list
    }

    private fun createNews(isRandomId: Boolean): News {
        return News(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomLong(),
            randomRelativePeriod(),
            randomInt()
        )
    }

}