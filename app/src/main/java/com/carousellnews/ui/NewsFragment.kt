package com.carousellnews.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.carousellnews.core.BaseFragment
import com.carousellnews.databinding.NewsFragmentBinding
import com.carousellnews.extension.makeGone
import com.carousellnews.extension.makeVisible
import com.carousellnews.extension.observe
import com.carousellnews.presentation.viewmodel.NewsUIModel
import com.carousellnews.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding,NewsViewModel>() {

    val TAG = "NEWS_FRAGMENT"
    companion object {

        fun newInstance() = NewsFragment()
    }

    @Inject
    lateinit var adapter: NewsAdapter

    override val viewModel: NewsViewModel by viewModels()

    override fun getViewBinding(): NewsFragmentBinding = NewsFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe(viewModel.newsList,::viewStateChange)
        viewModel.fetchData()
    }

    private fun initView(){
        binding.rvList.adapter = adapter
    }

    private fun viewStateChange(event: NewsUIModel){
        if(event.isRedelivered) return
        when(event){
            is NewsUIModel.Loading -> {
                binding.progressCircular.makeVisible()
            }
            is NewsUIModel.Error -> {
                binding.progressCircular.makeGone()
            }
            is NewsUIModel.Success -> {
                binding.progressCircular.makeGone()
                adapter.list = event.list
            }
        }
    }
}