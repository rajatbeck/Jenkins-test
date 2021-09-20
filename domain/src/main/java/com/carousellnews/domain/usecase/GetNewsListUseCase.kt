package com.carousellnews.domain.usecase

import com.carousellnews.domain.models.News
import com.carousellnews.domain.models.enums.Sort
import com.carousellnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewsListUseCase
   @Inject constructor(
      private val newsRepository: NewsRepository
   )
    :BaseUseCase<Sort?, Flow<List<News>>> {

    override suspend fun invoke(params: Sort?): Flow<List<News>> {
        return newsRepository.getNews()
            .map {
                it.sortedWith(
                    when (params) {
                        Sort.RANK -> compareByDescending<News> { it.rank }.thenByDescending { it.timestamp }
                        else -> compareByDescending { it.timestamp }
                    }
                )
            }
    }

}