package com.dvidal.remote

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.repository.WeatherChallengeRemote
import com.dvidal.remote.mapper.CurrentWeatherResponseModelMapper
import com.dvidal.remote.service.CurrentWeatherService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */

class WeatherChallengeRemoteImpl @Inject constructor(
        private val service: CurrentWeatherService,
        private val mapper: CurrentWeatherResponseModelMapper
    ): WeatherChallengeRemote {

    override fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeatherEntity> {
        return service.searchCurrentWeatherWithPosition(lat, long)
                .map { mapper.mapFromModel(it) }
    }

    override fun getCurrentWeatherWithCityId(cityId: Long): Observable<CurrentWeatherEntity> {

        return service.searchCurrentWeatherWithCityId(cityId)
                .map { mapper.mapFromModel(it) }
    }
}