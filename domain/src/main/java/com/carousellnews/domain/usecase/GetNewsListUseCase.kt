package com.carousellnews.domain.usecase

import com.carousellnews.domain.models.News
import com.carousellnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsListUseCase
   @Inject constructor(
      private val newsRepository: NewsRepository
   )
    :BaseUseCase<Unit, Flow<List<News>>> {

    override suspend fun invoke(params: Unit): Flow<List<News>> {
        return newsRepository.getNews()
    }

}