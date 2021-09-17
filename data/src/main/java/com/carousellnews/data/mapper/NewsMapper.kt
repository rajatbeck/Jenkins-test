package com.carousellnews.data.mapper

import com.carousellnews.data.models.NewsEntity
import com.carousellnews.domain.models.News
import com.carousellnews.domain.utils.DateUtils
import javax.inject.Inject

class NewsMapper @Inject constructor(
    private val dateUtils: DateUtils
): Mapper<NewsEntity, News> {

    override fun mapFromEntity(type: NewsEntity): News {
        return News(
            type.id,
            type.title,
            type.description,
            type.bannerUrl,
            dateUtils.convertToReadableFormat(type.timeStamp),
            type.rank
        )
    }


    override fun mapToEntity(type: News): NewsEntity {
        return NewsEntity(
            type.id,
            type.title,
            type.description,
            type.bannerUrl,
            dateUtils.convertTimeDurationToTimestamp(type.timeDuration),
            type.rank
        )
    }
}