package com.dvidal.domain.interactor.currentweather

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.ObservableUseCase
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.domain.repository.WeatherChallengeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class GetCurrentWeatherWithPositionUseCase @Inject constructor(
        private val weatherChallengeRepository: WeatherChallengeRepository,
        postExecutionThread: PostExecutionThread

): ObservableUseCase<CurrentWeather, GetCurrentWeatherWithPositionUseCase.Params?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<CurrentWeather> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return weatherChallengeRepository.getCurrentWeatherWithPosition(params.lat, params.long)
    }

    data class Params constructor(val lat: Double, val long: Double){
        companion object {

            fun forPosition(lat: Double, long: Double): Params {
                return Params(lat, long)
            }
        }
    }
}