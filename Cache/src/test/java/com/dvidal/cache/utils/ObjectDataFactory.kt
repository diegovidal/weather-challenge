package com.dvidal.cache.utils

import com.dvidal.cache.model.CachedCity
import com.dvidal.cache.model.CachedCurrentWeather
import com.dvidal.cache.utils.PrimitiveDataFactory.randomDouble
import com.dvidal.cache.utils.PrimitiveDataFactory.randomFloat
import com.dvidal.cache.utils.PrimitiveDataFactory.randomInt
import com.dvidal.cache.utils.PrimitiveDataFactory.randomLong
import com.dvidal.cache.utils.PrimitiveDataFactory.randomString
import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity

/**
 * @author diegovidal on 01/10/2018.
 */
object ObjectDataFactory {

    fun makeCachedCurrentWeather(): CachedCurrentWeather {
        return CachedCurrentWeather(randomLong(), randomString(), randomString(),
                randomFloat(), randomFloat(), randomInt(), randomFloat())
    }

    fun makeCachedCurrentWeatherSameId(): CachedCurrentWeather {
        return CachedCurrentWeather(200, randomString(), randomString(),
                randomFloat(), randomFloat(), randomInt(), randomFloat())
    }

    fun makeCurrentWeatherEntity(): CurrentWeatherEntity {
        return CurrentWeatherEntity(randomLong(), randomString(), randomString(),
                randomFloat(), randomFloat(), randomInt(), randomFloat())
    }

    fun makeCachedCity(): CachedCity {
        return CachedCity(randomLong(), randomString(), randomString())
    }

    fun makeCityEntity(): CityEntity {
        return CityEntity(randomLong(), randomString(), randomString())
    }
}