package com.dvidal.cache.db

/**
 * @author diegovidal on 01/10/2018.
 */
object CurrentWeatherConstants {

    const val TABLE_NAME = "current_weather"

    const val QUERY_CURRENT_WEATHER = "SELECT * FROM $TABLE_NAME"

    const val DELETE_CURRENT_WEATHER = "DELETE FROM $TABLE_NAME"
}