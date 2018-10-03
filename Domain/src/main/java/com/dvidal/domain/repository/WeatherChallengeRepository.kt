package com.dvidal.domain.repository

import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author diegovidal on 30/09/2018.
 */
interface WeatherChallengeRepository {

    fun getCurrentWeatherWithCity(cityId: Long): Observable<CurrentWeather>

    fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeather>

    fun clearCurrentWeather(): Completable

    fun getCityList(): Observable<List<City>>

    fun saveCityList(cities: List<City>): Completable
}