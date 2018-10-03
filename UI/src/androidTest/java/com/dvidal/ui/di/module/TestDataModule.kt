package com.dvidal.ui.di.module

import com.dvidal.domain.repository.WeatherChallengeRepository
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): WeatherChallengeRepository {
        return mock()
    }
}