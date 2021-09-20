package com.carousellnews.cache.fakes

import java.util.UUID
import kotlin.random.Random

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random.nextInt()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    fun randomLong(): Long {
        return Random.nextLong()
    }
}