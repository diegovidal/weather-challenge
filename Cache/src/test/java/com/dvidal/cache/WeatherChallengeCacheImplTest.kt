package com.dvidal.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.dvidal.cache.db.WeatherChallengeDatabase
import com.dvidal.cache.mapper.CachedCityMapper
import com.dvidal.cache.mapper.CachedCurrentWeatherMapper
import com.dvidal.cache.utils.ObjectDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(RobolectricTestRunner::class)
class WeatherChallengeCacheImplTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            WeatherChallengeDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val cachedCurrentWeatherMapper = CachedCurrentWeatherMapper()
    private val cachedCityMapper = CachedCityMapper()
    private val cache = WeatherChallengeCacheImpl(database, cachedCurrentWeatherMapper, cachedCityMapper)

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun clearCurrentWeatherCompletes() {

        val testObserver = cache.clearCurrentWeather().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveCurrentWeatherCompletes() {

        val currentWeather = ObjectDataFactory.makeCurrentWeatherEntity()

        val testObserver = cache.saveCurrentWeather(currentWeather).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherReturnsData() {

        val currentWeather = ObjectDataFactory.makeCurrentWeatherEntity()
        cache.saveCurrentWeather(currentWeather).test()

        val testObserver = cache.getCurrentWeather().test()
        testObserver.assertValue(currentWeather)
    }

    @Test
    fun clearCityListCompletes() {

        val testObserver = cache.clearCities().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveCityListCompletes() {

        val cities = listOf(ObjectDataFactory.makeCityEntity(), ObjectDataFactory.makeCityEntity())

        val testObserver = cache.saveCities(cities).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCityListReturnsData() {

        val cities = listOf(ObjectDataFactory.makeCityEntity(), ObjectDataFactory.makeCityEntity())
        cache.saveCities(cities).test()

        val testObserver = cache.getCities().test()
        testObserver.assertValue(cities)
    }

    @Test
    fun areCurrentWeatherCacheReturnsData() {

        val currentWeather = ObjectDataFactory.makeCurrentWeatherEntity()
        cache.saveCurrentWeather(currentWeather).test()

        val testObserver = cache.areCurrentWeatherCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun isCurrentWeatherCacheExpiredReturnsExpired() {
        val testObserver = cache.isCurrentWeatherCacheExpired().test()
        testObserver.assertValue(true)
    }

    @Test
    fun isCurrentWeatherCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis()).test()
        val testObserver = cache.isCurrentWeatherCacheExpired().test()
        testObserver.assertValue(false)
    }
}