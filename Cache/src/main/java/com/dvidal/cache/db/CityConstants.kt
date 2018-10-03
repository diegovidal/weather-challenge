package com.dvidal.cache.db

/**
 * @author diegovidal on 01/10/2018.
 */
object CityConstants {

    const val TABLE_NAME = "cities"

    const val QUERY_CITIES = "SELECT * FROM $TABLE_NAME"

    const val DELETE_CITIES = "DELETE FROM $TABLE_NAME"
}