package com.dvidal.remote.mapper

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.remote.model.CurrentWeatherResponse
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */

class CurrentWeatherResponseModelMapper @Inject constructor(): ModelMapper<CurrentWeatherResponse, CurrentWeatherEntity> {

    override fun mapFromModel(model: CurrentWeatherResponse): CurrentWeatherEntity {
        return CurrentWeatherEntity(model.id, model.name, "",
                model.weatherMain.temp, model.weatherMain.pressure, model.weatherMain.humidity, model.wind.speed)
    }

}