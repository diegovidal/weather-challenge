package com.dvidal.ui.di.module

import com.dvidal.data.repository.WeatherChallengeRemote
import com.dvidal.remote.WeatherChallengeRemoteImpl
import com.dvidal.remote.service.CurrentWeatherService
import com.dvidal.remote.service.CurrentWeatherServiceFactory
import com.dvidal.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideWeatherChallengeService(): CurrentWeatherService {
            return CurrentWeatherServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindWeatherChallengeRemote(weatherChallengeRemote: WeatherChallengeRemoteImpl): WeatherChallengeRemote
}