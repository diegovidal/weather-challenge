package com.dvidal.domain.utils

import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.domain.utils.PrimitiveDataFactory.randomDouble
import com.dvidal.domain.utils.PrimitiveDataFactory.randomLong


/**
 * @author diegovidal on 30/09/2018.
 */
object ObjectDataFactory {

    fun randomString(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun makeCurrentWeather(): CurrentWeather {
        return CurrentWeather(randomLong(),
                PrimitiveDataFactory.randomString(), PrimitiveDataFactory.randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCity(): City {
        return City(randomLong(), randomString(), randomString())
    }

    fun makeCityList(count: Int): List<City> {
        val cities = mutableListOf<City>()
        repeat(count) {
            cities.add(makeCity())
        }
        return cities
    }
}