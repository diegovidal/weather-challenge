package com.dvidal.ui.di.module

import com.dvidal.data.WeatherChallengeDataRepository
import com.dvidal.domain.repository.WeatherChallengeRepository
import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(challengeDataRepository: WeatherChallengeDataRepository): WeatherChallengeRepository
}