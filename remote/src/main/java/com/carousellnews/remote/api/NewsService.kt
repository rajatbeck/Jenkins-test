package com.carousellnews.remote.api

import com.carousellnews.remote.models.NewsResponse
import retrofit2.http.GET

interface NewsService {

    @GET("/carousell-interview-assets/android/carousell_news.json")
    suspend fun getNews(): List<NewsResponse>
}