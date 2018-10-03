package com.dvidal.data.utils

import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.utils.PrimitiveDataFactory.randomDouble
import com.dvidal.data.utils.PrimitiveDataFactory.randomString
import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather

/**
 * @author diegovidal on 30/09/2018.
 */
object ObjectDataFactory {

    fun makeCurrentWeatherEntity(): CurrentWeatherEntity {
        return CurrentWeatherEntity(PrimitiveDataFactory.randomLong(),
                randomString(), randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCurrentWeather(): CurrentWeather {
        return CurrentWeather(PrimitiveDataFactory.randomLong(),
                randomString(), randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCity(): City {
        return City(PrimitiveDataFactory.randomLong(), randomString(),
                randomString())
    }

    fun makeCityEntity(): CityEntity {
        return CityEntity(PrimitiveDataFactory.randomLong(), randomString(),
                randomString())
    }
}