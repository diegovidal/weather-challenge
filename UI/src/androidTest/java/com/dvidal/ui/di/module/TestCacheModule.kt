package com.dvidal.ui.di.module

import android.app.Application
import com.dvidal.cache.db.WeatherChallengeDatabase
import com.dvidal.data.repository.WeatherChallengeCache
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): WeatherChallengeDatabase {
        return WeatherChallengeDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideWeatherChallengeCache(): WeatherChallengeCache {
        return mock()
    }
}