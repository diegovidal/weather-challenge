package com.dvidal.data.repository

import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author diegovidal on 30/09/2018.
 */
interface WeatherChallengeDataStore {

    fun clearCities(): Completable

    fun saveCities(cities: List<CityEntity>): Completable

    fun clearCurrentWeather(): Completable

    fun saveCurrentWeather(currentWeathers: CurrentWeatherEntity): Completable

    fun getCities(): Observable<List<CityEntity>>

    fun getCurrentWeatherWithCity(cityId: Long): Observable<CurrentWeatherEntity>

    fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeatherEntity>
}