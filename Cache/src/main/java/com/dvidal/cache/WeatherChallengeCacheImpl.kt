package com.dvidal.cache

import com.dvidal.cache.db.WeatherChallengeDatabase
import com.dvidal.cache.mapper.CachedCityMapper
import com.dvidal.cache.mapper.CachedCurrentWeatherMapper
import com.dvidal.cache.model.CachedConfig
import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.repository.WeatherChallengeCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class WeatherChallengeCacheImpl @Inject constructor(
        private val weatherChallengeDatabase: WeatherChallengeDatabase,
        private val mapperCurrentWeather: CachedCurrentWeatherMapper,
        private val mapperCities: CachedCityMapper)
    : WeatherChallengeCache {

    override fun clearCities(): Completable {
        return Completable.defer {
            weatherChallengeDatabase.citiesDao().deleteCityList()
            Completable.complete()
        }
    }

    override fun saveCities(cities: List<CityEntity>): Completable {
        return Completable.defer {
            weatherChallengeDatabase.citiesDao().insertCityList(cities.map { mapperCities.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun clearCurrentWeather(): Completable {

        return Completable.defer {
            weatherChallengeDatabase.currentWeatherDao().deleteCurrentWeather()
            Completable.complete()
        }
    }

    override fun saveCurrentWeather(currentWeather: CurrentWeatherEntity): Completable {

        return Completable.defer {
            weatherChallengeDatabase.currentWeatherDao().insertCurrentWeather(
                    mapperCurrentWeather.mapToCached(currentWeather))
            Completable.complete()
        }
    }

    override fun getCities(): Observable<List<CityEntity>> {

        return weatherChallengeDatabase.citiesDao().getCityList()
                .toObservable()
                .map { cachedCurrentWeather ->
                    cachedCurrentWeather.map { mapperCities.mapFromCached(it) }
                }
    }

    override fun getCurrentWeather(): Observable<CurrentWeatherEntity> {

        return weatherChallengeDatabase.currentWeatherDao().getCurrentWeather()
                .toObservable()
                .map {
                     mapperCurrentWeather.mapFromCached(it)
                }
    }

    override fun areCurrentWeatherCached(): Single<Boolean> {
        return weatherChallengeDatabase.currentWeatherDao().getCurrentWeather().isEmpty
                .map { !it }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            weatherChallengeDatabase.configDao().insertConfig(CachedConfig(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isCurrentWeatherCacheExpired(): Single<Boolean> {

        return weatherChallengeDatabase.configDao().getConfig()
                .toSingle(CachedConfig(lastCacheTime = 0L))
                .map {

                    // 30 min to expire
                    System.currentTimeMillis() - it.lastCacheTime > (30 * 60 * 1000).toLong()
                }
    }
}