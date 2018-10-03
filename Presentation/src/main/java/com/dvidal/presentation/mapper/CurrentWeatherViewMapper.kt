package com.dvidal.presentation.mapper

import com.dvidal.domain.model.CurrentWeather
import com.dvidal.presentation.model.CurrentWeatherView
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class CurrentWeatherViewMapper @Inject constructor(): Mapper<CurrentWeatherView, CurrentWeather> {

    override fun mapToView(type: CurrentWeather): CurrentWeatherView {
        return CurrentWeatherView(type.id, type.name, type.weatherDesc,
                type.temp, type.pressure, type.humidity,
                type.windSpeed)
    }
}