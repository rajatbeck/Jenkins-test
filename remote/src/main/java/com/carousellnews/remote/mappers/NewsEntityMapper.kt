package com.carousellnews.remote.mappers

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.remote.models.NewsResponse

class NewsEntityMapper : EntityMapper<NewsResponse, NewsEntity> {
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