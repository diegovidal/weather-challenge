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
class CitiesDaoTest {

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
    fun getCitiesReturnsData() {

        val city = ObjectDataFactory.makeCachedCity()
        database.citiesDao().insertCityList(listOf(city))

        val testObserver = database.citiesDao().getCityList().test()
        testObserver.assertValue(listOf(city))
    }

    @Test
    fun deleteCitiesClearsData() {

        val city = ObjectDataFactory.makeCachedCity()
        database.citiesDao().insertCityList(listOf(city))
        database.citiesDao().deleteCityList()

        val testObserver = database.citiesDao().getCityList().test()
        testObserver.assertValue(emptyList())
    }
}