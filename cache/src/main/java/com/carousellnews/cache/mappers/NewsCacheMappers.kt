package com.carousellnews.cache.mappers

import com.carousellnews.cache.models.NewsCacheEntity
import com.carousellnews.data.models.NewsEntity

class NewsCacheMappers : CacheMapper<NewsCacheEntity, NewsEntity> {
    override fun mapFromCached(type: NewsCacheEntity): NewsEntity {
        return NewsEntity(
            type.id,
            type.title,
            type.description,
            type.bannerUrl,
            type.timeStamp,
            type.rank
        )
    }

    override fun mapToCached(type: NewsEntity): NewsCacheEntity {
        return NewsCacheEntity(
            type.id,
            type.title,
            type.description,
            type.bannerUrl,
            type.timeStamp, type.rank
        )
    }
}