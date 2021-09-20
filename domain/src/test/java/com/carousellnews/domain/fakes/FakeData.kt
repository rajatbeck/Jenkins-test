package com.carousellnews.domain.fakes

import com.carousellnews.domain.models.News
import com.carousellnews.domain.models.RelativeTime
import com.carousellnews.domain.models.enums.Duration

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {

    fun getNews(): Flow<List<News>> = flow {
        val newsList = listOf(
            News(
                id = "122",
                title = "Southeast Asia-based mobile listings startup Carousell raises $85M",
                description = "Carousell, the Singapore-based mobile listing service that operates across Southeast Asia, has pulled in an $85 million Series C fund as it seeks to strengthen its business among the region's competitive e-commerce landscape before expanding globally.",
                bannerUrl = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png",
                timestamp = 1630348518,
                relativeTime = RelativeTime(Duration.WEEK, 3),
                rank = 2
            ),
            News(
                id = "121",
                title = "Carousell is launching its own digital wallet to improve payments for its users",
                description = "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash. CarouPay will be a digital wallet within the Carousell app. \"More than half of our sellers will end up buying items as well, so maybe it makes sense to have that money in the wallet for purchases\" - Quek tells Tech in Asia.",
                bannerUrl = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
                timestamp = 1632159318,
                relativeTime = RelativeTime(Duration.HOURS, 1),
                rank = 2
            ),
            News(
                id = "123",
                title = "Tour de Franc=Geraint Thomas wins as Chris Froome finishes third",
                description = "The Team Sky rider, 32, follows Sir Bradley Wiggins in 2012 and four-time Tour champion Chris Froome as Britain celebrates a sixth win in seven years. Alexander Kristoff won the final sprint finish on the Champs-Elysees as Thomas crossed the line arm-in-arm with Froome after three weeks of racing.",
                bannerUrl = "https://storage.googleapis.com/carousell-interview-assets/android/images/_102749437_thomas_epa.jpg",
                timestamp = 1632076518,
                relativeTime = RelativeTime(Duration.DAYS, 1),
                rank = 1
            ),
        )
        emit(newsList)
    }
}