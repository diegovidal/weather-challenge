package com.dvidal.ui.di.module

import android.app.Application
import com.dvidal.cache.WeatherChallengeCacheImpl
import com.dvidal.cache.db.WeatherChallengeDatabase
import com.dvidal.data.repository.WeatherChallengeCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): WeatherChallengeDatabase {
            return WeatherChallengeDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindWeatherChallengeCache(weatherChallengeCache: WeatherChallengeCacheImpl): WeatherChallengeCache
}