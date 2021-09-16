package com.carousellnews.remote.fakes

import java.util.*
import kotlin.random.Random


object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

    fun randomLong(): Long {
        return Random.nextLong()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }
}