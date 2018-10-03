package com.dvidal.data.repository

import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author diegovidal on 30/09/2018.
 */
interface WeatherChallengeCache {

    fun clearCities(): Completable

    fun saveCities(cities: List<CityEntity>): Completable

    fun clearCurrentWeather(): Completable

    fun saveCurrentWeather(currentWeather: CurrentWeatherEntity): Completable

    fun getCities(): Observable<List<CityEntity>>

    fun getCurrentWeather(): Observable<CurrentWeatherEntity>

    fun areCurrentWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isCurrentWeatherCacheExpired(): Single<Boolean>
}