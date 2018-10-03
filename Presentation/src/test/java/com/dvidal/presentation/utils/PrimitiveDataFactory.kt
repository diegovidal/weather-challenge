package com.dvidal.presentation.utils

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author diegovidal on 01/10/2018.
 */
object PrimitiveDataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomFloat(): Float {
        return randomInt().toFloat()
    }

    fun randomDouble(): Double {
        return randomInt().toDouble()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }
}