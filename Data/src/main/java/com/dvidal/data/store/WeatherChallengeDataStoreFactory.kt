package com.dvidal.data.store

import com.dvidal.data.repository.WeatherChallengeDataStore
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
open class WeatherChallengeDataStoreFactory @Inject constructor(
        private val weatherChallengeCacheDataStore: WeatherChallengeCacheDataStore,
        private val weatherChallengeRemoteDataStore: WeatherChallengeRemoteDataStore
) {

    open fun getDataStore(currentWeatherCached: Boolean,
                          cacheExpired: Boolean): WeatherChallengeDataStore {

        return if (currentWeatherCached && !cacheExpired) weatherChallengeCacheDataStore else weatherChallengeRemoteDataStore
    }

    open fun getCacheDataStore(): WeatherChallengeDataStore {
        return weatherChallengeCacheDataStore
    }
}