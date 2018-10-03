package com.dvidal.domain.interactor.cities

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.ObservableUseCase
import com.dvidal.domain.model.City
import com.dvidal.domain.repository.WeatherChallengeRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */

class GetCitiesUseCase @Inject constructor(
        private val weatherChallengeRepository: WeatherChallengeRepository,
        postExecutionThread: PostExecutionThread

): ObservableUseCase<List<City>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<City>> {
        return weatherChallengeRepository.getCityList()
    }
}