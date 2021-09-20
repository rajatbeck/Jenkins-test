package com.carousellnews.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import com.carousellnews.R
import com.carousellnews.core.BaseFragment
import com.carousellnews.databinding.NewsFragmentBinding
import com.carousellnews.domain.models.enums.Sort
import com.carousellnews.extension.makeGone
import com.carousellnews.extension.makeVisible
import com.carousellnews.extension.observe
import com.carousellnews.extension.showSnackBar
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
                setHasOptionsMenu(false)
                showSnackBar(binding.root, event.error)
            }
            is NewsUIModel.Success -> {
                setHasOptionsMenu(true)
                binding.progressCircular.makeGone()
                adapter.list = event.list
            }
            is NewsUIModel.Empty -> {
                binding.progressCircular.makeGone()
                setHasOptionsMenu(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuInflater = requireActivity().menuInflater
        menuInflater.inflate(R.menu.news_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        when (viewModel.sortType) {
            Sort.RANK -> {
                menu.getItem(1).isChecked = true
                menu.getItem(0).isChecked = false
            }
            else -> {
                menu.getItem(0).isChecked = true
                menu.getItem(1).isChecked = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.recent -> {
                viewModel.fetchData(Sort.DATE)
            }
            else ->{
                viewModel.fetchData(Sort.RANK)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}