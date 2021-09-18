package com.carousellnews.remote.mappers

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.remote.models.NewsResponse
import javax.inject.Inject

class NewsEntityMapper @Inject constructor(): EntityMapper<NewsResponse, NewsEntity> {
    override fun mapFromModel(model: NewsResponse): NewsEntity {
        return NewsEntity(
            model.id,
            model.title,
            model.description,
            model.bannerUrl,
            model.timeStamp,
            model.rank
        )
    }
}