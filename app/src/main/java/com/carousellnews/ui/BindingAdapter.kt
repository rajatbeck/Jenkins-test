package com.carousellnews.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.carousellnews.R
import com.carousellnews.domain.models.RelativeTime
import com.carousellnews.domain.models.enums.Duration

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

@BindingAdapter("setTimeStamp")
fun setTimeStamp(textView: TextView,relativeTime: RelativeTime){
    textView.text = when(relativeTime.duration){
        Duration.SECONDS -> {
            textView.context.resources.getQuantityString(R.plurals.secondCount,relativeTime.value,relativeTime.value)
        }
        Duration.MINUTES -> {
            textView.context.resources.getQuantityString(R.plurals.minuteCount,relativeTime.value,relativeTime.value)
        }
        Duration.HOURS -> {
            textView.context.resources.getQuantityString(R.plurals.hourCount,relativeTime.value,relativeTime.value)
        }
        Duration.DAYS -> {
            textView.context.resources.getQuantityString(R.plurals.dayCount,relativeTime.value,relativeTime.value)
        }
        Duration.WEEK -> {
            textView.context.resources.getQuantityString(R.plurals.weekCount,relativeTime.value,relativeTime.value)
        }
        Duration.MONTHS -> {
            textView.context.resources.getQuantityString(R.plurals.monthCount,relativeTime.value,relativeTime.value)
        }
        Duration.YEAR -> {
            textView.context.resources.getQuantityString(R.plurals.yearCount,relativeTime.value,relativeTime.value)
        }
    }
}