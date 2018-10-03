package com.dvidal.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dvidal.cache.dao.CitiesDao
import com.dvidal.cache.dao.ConfigDao
import com.dvidal.cache.dao.CurrentWeatherDao
import com.dvidal.cache.model.CachedCity
import com.dvidal.cache.model.CachedConfig
import com.dvidal.cache.model.CachedCurrentWeather
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
@Database(
        entities = [CachedCurrentWeather::class, CachedConfig::class, CachedCity::class],
        exportSchema = false,
        version = 1
)
abstract class WeatherChallengeDatabase @Inject constructor(): RoomDatabase() {

    abstract fun citiesDao(): CitiesDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: WeatherChallengeDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): WeatherChallengeDatabase {

            if (INSTANCE == null){
                synchronized(lock) {
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                WeatherChallengeDatabase::class.java, "weatherchallenge.db")
                                .build()
                    }
                    return INSTANCE as WeatherChallengeDatabase
                }
            }
            return INSTANCE as WeatherChallengeDatabase
        }
    }
}