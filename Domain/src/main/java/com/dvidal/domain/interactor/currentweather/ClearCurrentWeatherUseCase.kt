package com.dvidal.domain.interactor.currentweather

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.CompletableUseCase
import com.dvidal.domain.interactor.base.ObservableUseCase
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.domain.repository.WeatherChallengeRepository
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 03/10/2018.
 */
class ClearCurrentWeatherUseCase @Inject constructor(
        private val weatherChallengeRepository: WeatherChallengeRepository,
        postExecutionThread: PostExecutionThread

): CompletableUseCase<Nothing?>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Nothing?): Completable {
        return weatherChallengeRepository.clearCurrentWeather()
    }
}