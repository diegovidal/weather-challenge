package com.dvidal.data.store

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 30/09/2018.
 */

class WeatherChallengeDataStoreFactoryTest {

    @Mock
    lateinit var challengeCacheStore: WeatherChallengeCacheDataStore

    @Mock
    lateinit var challengeRemoteStore: WeatherChallengeRemoteDataStore

    lateinit var factoryChallenge:  WeatherChallengeDataStoreFactory

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        factoryChallenge = WeatherChallengeDataStoreFactory(challengeCacheStore, challengeRemoteStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenCacheExpired() {

//        assertEquals(challengeRemoteStore, utils.getDataStore(true, true))
        assert(factoryChallenge.getDataStore(true, true) is WeatherChallengeRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenNoCachedData() {
        assert(factoryChallenge.getDataStore(false, false) is WeatherChallengeRemoteDataStore)
    }

    @Test
    fun getCacheStoreRetrievesCacheSource() {
        assert(factoryChallenge.getDataStore(true, false) is WeatherChallengeCacheDataStore)
    }

    @Test
    fun getCacheDataStoreRetrievesCacheSource() {
        assert(factoryChallenge.getCacheDataStore() is WeatherChallengeCacheDataStore)
    }
}