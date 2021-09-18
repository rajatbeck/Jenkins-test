package com.carousellnews.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.carousellnews.core.BaseFragment
import com.carousellnews.databinding.NewsFragmentBinding
import com.carousellnews.extension.observe
import com.carousellnews.presentation.viewmodel.NewsUIModel
import com.carousellnews.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding,NewsViewModel>() {

    val TAG = "NEWS_FRAGMENT"
    companion object {

        fun newInstance() = NewsFragment()
    }

    override val viewModel: NewsViewModel by viewModels()

    override fun getViewBinding(): NewsFragmentBinding = NewsFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.newsList,::viewStateChange)
        viewModel.fetchData()
    }

    private fun viewStateChange(event: NewsUIModel){
        if(event.isRedelivered) return
        when(event){
            is NewsUIModel.Loading -> {}
            is NewsUIModel.Error -> {}
            is NewsUIModel.Success -> {
                Log.d(TAG, event.list.toString())
            }
        }
    }
}