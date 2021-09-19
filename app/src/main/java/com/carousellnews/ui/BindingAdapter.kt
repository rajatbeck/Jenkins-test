package com.carousellnews.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.carousellnews.R

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background))
            .into(imgView)
    }
}

@BindingAdapter("spaceItemDecoration")
fun setSpaceItemDecoration(recyclerView: RecyclerView, spaceInPx: Float) {
    if (spaceInPx != 0f) {
        val itemDecoration = SimpleItemDecoration(recyclerView.context, spaceInPx.toInt())
        recyclerView.addItemDecoration(itemDecoration)
    }
}