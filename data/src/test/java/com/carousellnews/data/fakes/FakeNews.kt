package com.carousellnews.data.fakes

import com.carousellnews.data.models.NewsEntity


object FakeNews {
    fun getNews():List<NewsEntity> = listOf(
        NewsEntity(
            "121",
            "Carousell is launching its own digital wallet to improve payments for its users",
            "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash. CarouPay will be a digital wallet within the Carousell app. \"More than half of our sellers will end up buying items as well, so maybe it makes sense to have that money in the wallet for purchases\" - Quek tells Tech in Asia.",
            "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
            1532853058,
            2
        ),
        NewsEntity(
            "122",
            "Southeast Asia-based mobile listings startup Carousell raises $85M",
            "Carousell, the Singapore-based mobile listing service that operates across Southeast Asia, has pulled in an $85 million Series C fund as it seeks to strengthen its business among the region's competitive e-commerce landscape before expanding globally.",
            "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png",
            1532939458,
            5
        )
    )
}