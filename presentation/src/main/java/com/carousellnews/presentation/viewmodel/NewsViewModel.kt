package com.carousellnews.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.carousellnews.domain.models.News
import com.carousellnews.domain.usecase.GetNewsListUseCase
import com.carousellnews.presentation.utils.CoroutineContextProvider
import com.carousellnews.presentation.utils.ExceptionHandler
import com.carousellnews.presentation.utils.UiAwareLiveData
import com.carousellnews.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

sealed class NewsUIModel : UiAwareModel() {
    object Loading : NewsUIModel()
    data class Error(var error: String = "") : NewsUIModel()
    data class Success(val list: List<News>) : NewsUIModel()
}

@HiltViewModel
class NewsViewModel @Inject constructor(
    contextProvider:CoroutineContextProvider,
    private val newsListUseCase: GetNewsListUseCase
): BaseViewModel(contextProvider){

    private val _newsList = UiAwareLiveData<NewsUIModel>()
    val newsList: LiveData<NewsUIModel>
        get() = _newsList

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _newsList.postValue(NewsUIModel.Error(exception.message ?: "error"))
    }

    fun fetchData(){
        _newsList.postValue(NewsUIModel.Loading)
        launchCoroutineIO {
            getNewsList()
        }
    }

    private suspend fun getNewsList(){
        newsListUseCase(Unit).collect {
            _newsList.postValue(NewsUIModel.Success(it))
        }
    }
}