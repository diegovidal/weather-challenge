package com.dvidal.data

import com.dvidal.data.mapper.CityMapper
import com.dvidal.data.mapper.CurrentWeatherMapper
import com.dvidal.data.model.CityEntity
import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.repository.WeatherChallengeCache
import com.dvidal.data.repository.WeatherChallengeDataStore
import com.dvidal.data.store.WeatherChallengeDataStoreFactory
import com.dvidal.data.utils.ObjectDataFactory
import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 01/10/2018.
 */
@RunWith(JUnit4::class)
class WeatherChallengeRepositoryTest {

    private val mapperCurrentWeather = mock<CurrentWeatherMapper>()
    private val mapperCity = mock<CityMapper>()

    private val factory = mock<WeatherChallengeDataStoreFactory>()
    private val store = mock<WeatherChallengeDataStore>()
    private val cache = mock<WeatherChallengeCache>()
    private val repository = WeatherChallengeDataRepository(mapperCurrentWeather, mapperCity, cache, factory)

    @Before
    fun setup() {

        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreCurrentWeatherCached(Single.just(false))
        stubSaveCurrentWeather(Completable.complete())
    }

    @Test
    fun getCurrentWeatherWithCityCompletes() {
        stubGetCurrentWeatherWithCity(Observable.just(ObjectDataFactory.makeCurrentWeatherEntity()))
        stubCurrentWeatherMapper(ObjectDataFactory.makeCurrentWeather(), any())

        val testObserver = repository.getCurrentWeatherWithCity(20).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherWithCityReturnsData() {

        val currentWeatherEntity = ObjectDataFactory.makeCurrentWeatherEntity()
        val currentWeather = ObjectDataFactory.makeCurrentWeather()
        stubGetCurrentWeatherWithCity(Observable.just(currentWeatherEntity))
        stubCurrentWeatherMapper(currentWeather, currentWeatherEntity)

        val testObserver = repository.getCurrentWeatherWithCity(20).test()
        testObserver.assertValue(currentWeather)
    }

    @Test
    fun getCurrentWeatherWithPositionCompletes() {
        stubGetCurrentWeatherWithPosition(Observable.just(ObjectDataFactory.makeCurrentWeatherEntity()))
        stubCurrentWeatherMapper(ObjectDataFactory.makeCurrentWeather(), any())

        val testObserver = repository.getCurrentWeatherWithPosition(100.0, 100.0).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherWithPositionReturnsData() {

        val currentWeatherEntity = ObjectDataFactory.makeCurrentWeatherEntity()
        val currentWeather = ObjectDataFactory.makeCurrentWeather()
        stubGetCurrentWeatherWithPosition(Observable.just(currentWeatherEntity))
        stubCurrentWeatherMapper(currentWeather, currentWeatherEntity)

        val testObserver = repository.getCurrentWeatherWithPosition(100.0, 100.0).test()
        testObserver.assertValue(currentWeather)
    }

    @Test
    fun getCityListCompletes() {
        stubGetCityList(Observable.just(listOf(ObjectDataFactory.makeCityEntity())))
        stubCityMapper(ObjectDataFactory.makeCity(), any())

        val testObserver = repository.getCityList().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCityListReturnsData() {

        val cityEntity = ObjectDataFactory.makeCityEntity()
        val city = ObjectDataFactory.makeCity()

        stubGetCityList(Observable.just(listOf(cityEntity)))
        stubCityMapper(city, cityEntity)

        val testObserver = repository.getCityList().test()
        testObserver.assertValue(listOf(city))
    }


    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isCurrentWeatherCacheExpired())
                .thenReturn(single)
    }

    private fun stubAreCurrentWeatherCached(single: Single<Boolean>) {
        whenever(cache.areCurrentWeatherCached())
                .thenReturn(single)
    }

    private fun stubCurrentWeatherMapper(model: CurrentWeather, entity: CurrentWeatherEntity) {
        whenever(mapperCurrentWeather.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubCityMapper(model: City, entity: CityEntity) {
        whenever(mapperCity.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubGetCurrentWeatherWithCity(observable: Observable<CurrentWeatherEntity>) {
        whenever(store.getCurrentWeatherWithCity(20))
                .thenReturn(observable)
    }

    private fun stubGetCurrentWeatherWithPosition(observable: Observable<CurrentWeatherEntity>) {
        whenever(store.getCurrentWeatherWithPosition(100.0, 100.0))
                .thenReturn(observable)
    }

    private fun stubGetCityList(observable: Observable<List<CityEntity>>) {
        whenever(store.getCities())
                .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubSaveCurrentWeather(completable: Completable) {
        whenever(store.saveCurrentWeather(any()))
                .thenReturn(completable)
    }

}