package com.dvidal.data.mapper

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.domain.model.CurrentWeather
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class CurrentWeatherMapper @Inject constructor(): EntityMapper<CurrentWeatherEntity, CurrentWeather> {

    override fun mapFromEntity(entity: CurrentWeatherEntity): CurrentWeather {
        return CurrentWeather(entity.id, entity.name, entity.weatherDesc, entity.temp,
                entity.pressure, entity.humidity, entity.windSpeed)
    }

    override fun mapToEntity(domain: CurrentWeather): CurrentWeatherEntity {
        return CurrentWeatherEntity(domain.id, domain.name, domain.weatherDesc,
                domain.temp, domain.pressure, domain.humidity,
                domain.windSpeed)
    }
}