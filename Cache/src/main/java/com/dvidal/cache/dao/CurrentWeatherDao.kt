package com.dvidal.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dvidal.cache.db.CurrentWeatherConstants.DELETE_CURRENT_WEATHER
import com.dvidal.cache.db.CurrentWeatherConstants.QUERY_CURRENT_WEATHER
import com.dvidal.cache.model.CachedCurrentWeather
import io.reactivex.Maybe

/**
 * @author diegovidal on 01/10/2018.
 */

@Dao
abstract class CurrentWeatherDao {

    @Query(QUERY_CURRENT_WEATHER)
    abstract fun getCurrentWeather(): Maybe<CachedCurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrentWeather(cachedCurrentWeather: CachedCurrentWeather)

    @Query(DELETE_CURRENT_WEATHER)
    abstract fun deleteCurrentWeather()
}