package com.dvidal.data.repository

import com.dvidal.data.model.CurrentWeatherEntity
import io.reactivex.Observable

/**
 * @author diegovidal on 30/09/2018.
 */
interface WeatherChallengeRemote {

    fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeatherEntity>

    fun getCurrentWeatherWithCityId(cityId: Long): Observable<CurrentWeatherEntity>
}