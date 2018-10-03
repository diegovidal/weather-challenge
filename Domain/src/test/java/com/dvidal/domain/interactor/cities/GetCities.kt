package com.dvidal.domain.interactor.cities

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.model.City
import com.dvidal.domain.repository.WeatherChallengeRepository
import com.dvidal.domain.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 30/09/2018.
 */

@RunWith(JUnit4::class)
class GetCities {

    private lateinit var getCitiesUseCase: GetCitiesUseCase

    @Mock lateinit var weatherChallengeRepository: WeatherChallengeRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        getCitiesUseCase = GetCitiesUseCase(weatherChallengeRepository, postExecutionThread)
    }

    @Test
    fun getCityListCompletes() {

        stubGetCities(Observable.just(ObjectDataFactory.makeCityList(2)))
        val testObserver = getCitiesUseCase.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCityListReturnsData() {

        val cities = ObjectDataFactory.makeCityList(2)
        stubGetCities(Observable.just(cities))
        val testObserver = getCitiesUseCase.buildUseCaseObservable().test()
        testObserver.assertValue(cities)
    }

    private fun stubGetCities(observable: Observable<List<City>>) {
        whenever(weatherChallengeRepository.getCityList())
                .thenReturn(observable)
    }
}