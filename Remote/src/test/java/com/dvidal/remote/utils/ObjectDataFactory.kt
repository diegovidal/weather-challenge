package com.dvidal.remote.utils

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.remote.model.*

/**
 * @author diegovidal on 01/10/2018.
 */
object ObjectDataFactory {

    fun makeWeather(): WeatherModel {
        return WeatherModel(PrimitiveDataFactory.randomLong(), PrimitiveDataFactory.randomString(), PrimitiveDataFactory.randomString())
    }

    fun makeWeatherMain(): WeatherMainModel {
        return WeatherMainModel(PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomInt())
    }

    fun makeWind(): WindModel {
        return WindModel(PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCurrentWeatherResponse(): CurrentWeatherResponse {
        return CurrentWeatherResponse(PrimitiveDataFactory.randomLong(), PrimitiveDataFactory.randomString(), makeWeatherMain(), makeWind())
    }

    fun makeCurrentWeatherEntity(): CurrentWeatherEntity {
        return CurrentWeatherEntity(PrimitiveDataFactory.randomLong(),
                PrimitiveDataFactory.randomString(), PrimitiveDataFactory.randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }
}