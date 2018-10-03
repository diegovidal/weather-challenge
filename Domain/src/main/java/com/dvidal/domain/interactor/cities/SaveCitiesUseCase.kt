package com.dvidal.domain.interactor.cities

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.CompletableUseCase
import com.dvidal.domain.interactor.base.ObservableUseCase
import com.dvidal.domain.model.City
import com.dvidal.domain.repository.WeatherChallengeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */

class SaveCitiesUseCase @Inject constructor(
        private val weatherChallengeRepository: WeatherChallengeRepository,
        postExecutionThread: PostExecutionThread

): CompletableUseCase<SaveCitiesUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return weatherChallengeRepository.saveCityList(params.cities)
    }

    data class Params constructor(val cities: List<City>){

        companion object {

            fun forCities(cities: List<City>): Params {
                return Params(cities)
            }
        }
    }
}