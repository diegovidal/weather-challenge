package com.dvidal.cache.mapper

import com.dvidal.cache.model.CachedCurrentWeather
import com.dvidal.data.model.CurrentWeatherEntity
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class CachedCurrentWeatherMapper @Inject constructor(): CacheMapper<CachedCurrentWeather, CurrentWeatherEntity> {


    override fun mapFromCached(type: CachedCurrentWeather): CurrentWeatherEntity {
        return CurrentWeatherEntity(type.id, type.name, type.weatherDesc,
                type.temp, type.pressure, type.humidity,
                type.windSpeed)
    }

    override fun mapToCached(type: CurrentWeatherEntity): CachedCurrentWeather {
        return CachedCurrentWeather(type.id, type.name, type.weatherDesc, type.temp,
                type.pressure, type.humidity, type.windSpeed)
    }
}