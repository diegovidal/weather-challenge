package com.dvidal.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author diegovidal on 01/10/2018.
 */
class CurrentWeatherResponse(val id: Long, val name: String,
                             @SerializedName("main") val weatherMain: WeatherMainModel,
                             val wind: WindModel)