package com.carousellnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.carousellnews.BR
import com.carousellnews.core.BaseAdapter
import com.carousellnews.databinding.ItemNewsBinding
import com.carousellnews.domain.models.News
import javax.inject.Inject

class NewsAdapter @Inject constructor():BaseAdapter<News>() {

    private val diffUtil = object: DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }
    override val differ: AsyncListDiffer<News> = AsyncListDiffer(this, diffUtil)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    inner class NewsViewHolder(private val binding:ItemNewsBinding):RecyclerView.ViewHolder(binding.root),Binder<News>{
        override fun bind(item: News) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }

    }
}