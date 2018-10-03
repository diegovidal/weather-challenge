package com.dvidal.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.dvidal.cache.db.WeatherChallengeDatabase
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
class CurrentWeatherDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            WeatherChallengeDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getCurrentWeatherReturnsData() {

        val weather = ObjectDataFactory.makeCachedCurrentWeather()
        database.currentWeatherDao().insertCurrentWeather(weather)

        val testObserver = database.currentWeatherDao().getCurrentWeather().test()
        testObserver.assertValue(weather)
    }

    @Test
    fun getCurrentWeatherReturnsLastData() {

        val weather = ObjectDataFactory.makeCachedCurrentWeatherSameId()
        val lastWeather = ObjectDataFactory.makeCachedCurrentWeatherSameId()

        database.currentWeatherDao().insertCurrentWeather(weather)
        database.currentWeatherDao().insertCurrentWeather(lastWeather)

        val testObserver = database.currentWeatherDao().getCurrentWeather().test()
        testObserver.assertValue(lastWeather)
    }

    @Test
    fun deleteCurrentWeatherClearsData() {

        val weather = ObjectDataFactory.makeCachedCurrentWeather()
        database.currentWeatherDao().insertCurrentWeather(weather)
        database.currentWeatherDao().deleteCurrentWeather()

        val testObserver = database.citiesDao().getCityList().test()
        testObserver.assertValue(emptyList())
    }
}