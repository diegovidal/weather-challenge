package com.dvidal.remote.service

import com.dvidal.remote.model.CurrentWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author diegovidal on 01/10/2018.
 */

const val APPID = "45da63a43417ad9154275bf4c1295c4d"

interface CurrentWeatherService {

    @GET("weather")
    fun searchCurrentWeatherWithPosition(@Query("lat") lat: Double,
                                         @Query("lon") long: Double,
                           @Query("APPID") order: String = APPID)
            : Observable<CurrentWeatherResponse>

    @GET("weather")
    fun searchCurrentWeatherWithCityId(@Query("id") cityId: Long,
                                         @Query("APPID") order: String = APPID)
            : Observable<CurrentWeatherResponse>
}