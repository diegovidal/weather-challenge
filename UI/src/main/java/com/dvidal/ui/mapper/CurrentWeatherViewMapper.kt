package com.dvidal.ui.mapper

import com.dvidal.presentation.model.CurrentWeatherView
import com.dvidal.ui.model.CurrentWeather
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class CurrentWeatherViewMapper @Inject constructor(): ViewMapper<CurrentWeatherView, CurrentWeather> {

    override fun mapToView(presentation: CurrentWeatherView): CurrentWeather {
        return CurrentWeather(presentation.id, presentation.name,
                presentation.weatherDesc, presentation.temp,
                presentation.pressure, presentation.humidity,
                presentation.windSpeed)
    }
}