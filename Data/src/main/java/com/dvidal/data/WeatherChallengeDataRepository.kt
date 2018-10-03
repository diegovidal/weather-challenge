package com.dvidal.data

import com.dvidal.data.mapper.CityMapper
import com.dvidal.data.mapper.CurrentWeatherMapper
import com.dvidal.data.repository.WeatherChallengeCache
import com.dvidal.data.store.WeatherChallengeDataStoreFactory
import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.domain.repository.WeatherChallengeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */

class WeatherChallengeDataRepository @Inject constructor(
        private val mapperCurrentWeather: CurrentWeatherMapper,
        private val mapperCity: CityMapper,
        private val cache: WeatherChallengeCache,
        private val factoryChallenge: WeatherChallengeDataStoreFactory
): WeatherChallengeRepository {

    override fun getCityList(): Observable<List<City>> {

        return factoryChallenge.getCacheDataStore().getCities()
                .map { list ->
                    list.map {
                        mapperCity.mapFromEntity(it)
                    }
                }
    }

    override fun saveCityList(cities: List<City>): Completable {

        return factoryChallenge.getCacheDataStore().saveCities(
                cities.map { mapperCity.mapToEntity(it) })
    }

    override fun getCurrentWeatherWithCity(cityId: Long): Observable<CurrentWeather> {

        return Observable.zip(cache.areCurrentWeatherCached().toObservable(),
                cache.isCurrentWeatherCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factoryChallenge.getDataStore(it.first, it.second).getCurrentWeatherWithCity(cityId)
                            .distinctUntilChanged()
                }
                .flatMap {currentWeatherEntity ->
                    factoryChallenge.getCacheDataStore()
                            .saveCurrentWeather(currentWeatherEntity)
                            .andThen(Observable.just(currentWeatherEntity))
                }
                .map { it ->
                    mapperCurrentWeather.mapFromEntity(it)
                }
    }

    override fun getCurrentWeatherWithPosition(lat: Double, long: Double): Observable<CurrentWeather> {

        return Observable.zip(cache.areCurrentWeatherCached().toObservable(),
                cache.isCurrentWeatherCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factoryChallenge.getDataStore(it.first, it.second).getCurrentWeatherWithPosition(lat, long)
                            .distinctUntilChanged()
                }
                .flatMap {currentWeatherEntity ->
                    factoryChallenge.getCacheDataStore()
                            .saveCurrentWeather(currentWeatherEntity)
                            .andThen(Observable.just(currentWeatherEntity))
                }
                .map { it ->
                    mapperCurrentWeather.mapFromEntity(it)
                }
    }

    override fun clearCurrentWeather(): Completable {
        return factoryChallenge.getCacheDataStore().clearCurrentWeather()
    }
}