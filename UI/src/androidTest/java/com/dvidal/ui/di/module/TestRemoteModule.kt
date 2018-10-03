package com.dvidal.ui.di.module

import com.dvidal.data.repository.WeatherChallengeRemote
import com.dvidal.remote.service.CurrentWeatherService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideCurrentWeather(): CurrentWeatherService{
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideWeatherChallengeRemote(): WeatherChallengeRemote {
        return mock()
    }
}