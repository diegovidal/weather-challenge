package com.dvidal.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.dvidal.cache.db.CurrentWeatherConstants

/**
 * @author diegovidal on 01/10/2018.
 */

@Entity(tableName = CurrentWeatherConstants.TABLE_NAME)
data class CachedCurrentWeather(
        @PrimaryKey(autoGenerate = true)
        var id: Long = -1,
        var name: String,
        var weatherDesc: String,
        var temp: Float,
        var pressure: Float,
        var humidity: Int,
        var windSpeed: Float
)