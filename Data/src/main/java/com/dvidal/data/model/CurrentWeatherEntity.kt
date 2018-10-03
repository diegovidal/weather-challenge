package com.dvidal.data.model

/**
 * @author diegovidal on 30/09/2018.
 */
data class CurrentWeatherEntity(val id: Long, val name: String, val weatherDesc: String,
                                val temp: Float, val pressure: Float,
                                val humidity: Int, val windSpeed: Float)