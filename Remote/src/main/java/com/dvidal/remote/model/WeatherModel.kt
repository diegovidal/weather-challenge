package com.dvidal.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @author diegovidal on 01/10/2018.
 */
class WeatherModel(val id: Long,
                   val main: String,
                   val description: String)