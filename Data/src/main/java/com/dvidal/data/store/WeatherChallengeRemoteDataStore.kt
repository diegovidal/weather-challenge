package com.dvidal.data.store

import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.repository.WeatherChallengeDataStore
import com.dvidal.data.repository.WeatherChallengeRemote
import com.dvidal.domain.model.City
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class WeatherChallengeRemoteDataStore @Inject constructor(
        private val weatherChallengeRemote: WeatherChallengeRemote
): WeatherChallengeDataStore {

    override fun clearCities(): Completable {
        throw UnsupportedOperationException("Isn't supported here...")
    }

    override fun saveCities(cities: List<CityEntity>): Completable {
        throw UnsupportedOperationException("Isn't supported here...")
    }

    override fun clearCurrentWeather(): Completable {
        throw UnsupportedOperationException("Isn't supported here...")
    }

    override fun saveCurrentWeather(currentWeathers: CurrentWeatherEntity): Completable {
        throw UnsupportedOperationException("Isn't supported here...")
    }

    override fun getCities(): Observable<List<CityEntity>> {
        throw UnsupportedOperationException("Isn't supported here...")
    }

    override fun getCurrentWeatherWithCity(cityId: Long): Observable<CurrentWeatherEntity> {
        return weatherChallengeRemote.getCurrentWeatherWithCityId(cityId)
    }

    override fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeatherEntity> {
        return weatherChallengeRemote.getCurrentWeatherWithPosition(lat, long)
    }
}