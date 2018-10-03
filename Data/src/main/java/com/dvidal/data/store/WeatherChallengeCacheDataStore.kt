package com.dvidal.data.store

import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.repository.WeatherChallengeCache
import com.dvidal.data.repository.WeatherChallengeDataStore
import com.dvidal.domain.model.City
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class WeatherChallengeCacheDataStore @Inject constructor(
    private val weatherChallengeCache: WeatherChallengeCache
): WeatherChallengeDataStore {


    override fun clearCities(): Completable {
        return weatherChallengeCache.clearCities()
    }

    override fun saveCities(cities: List<CityEntity>): Completable {
        return weatherChallengeCache.saveCities(cities)
    }

    override fun clearCurrentWeather(): Completable {
        return weatherChallengeCache.clearCurrentWeather()
    }

    override fun saveCurrentWeather(currentWeathers: CurrentWeatherEntity): Completable {
        return weatherChallengeCache.saveCurrentWeather(currentWeathers)
                .andThen(weatherChallengeCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun getCities(): Observable<List<CityEntity>> {
        return weatherChallengeCache.getCities()
    }

    override fun getCurrentWeatherWithCity(cityId: Long): Observable<CurrentWeatherEntity> {
        return weatherChallengeCache.getCurrentWeather()
    }

    override fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeatherEntity> {
        return weatherChallengeCache.getCurrentWeather()
    }
}