package com.carousellnews.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.carousellnews.domain.models.News
import com.carousellnews.domain.models.enums.Sort
import com.carousellnews.domain.usecase.GetNewsListUseCase
import com.carousellnews.presentation.utils.CoroutineContextProvider
import com.carousellnews.presentation.utils.ExceptionHandler
import com.carousellnews.presentation.utils.UiAwareLiveData
import com.carousellnews.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

sealed class NewsUIModel : UiAwareModel() {
    object Loading : NewsUIModel()
    data class Error(var error: String = "") : NewsUIModel()
    data class Success(val list: List<News>) : NewsUIModel()
    object Empty : NewsUIModel()
}

@HiltViewModel
class NewsViewModel @Inject constructor(
    contextProvider:CoroutineContextProvider,
    private val newsListUseCase: GetNewsListUseCase
): BaseViewModel(contextProvider){

    private val _newsList = UiAwareLiveData<NewsUIModel>()
    val newsList: LiveData<NewsUIModel>
        get() = _newsList

    var sortType: Sort? = null

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _newsList.postValue(NewsUIModel.Error(exception.message ?: "error"))
    }

    fun fetchData(sort: Sort = Sort.DATE) {
        if (sort == this.sortType)
            return
        this.sortType = sort
        _newsList.postValue(NewsUIModel.Loading)
        launchCoroutineIO {
                getNewsList(sort)
        }
    }

    private suspend fun getNewsList(sort: Sort) {
        newsListUseCase(sort).collect {
            if(it.isEmpty()){
                _newsList.postValue(NewsUIModel.Empty)
            }else {
                _newsList.postValue(NewsUIModel.Success(it))
            }
        }
    }
}