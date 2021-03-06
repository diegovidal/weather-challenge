package com.dvidal.domain.interactor.currentweather

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.domain.repository.WeatherChallengeRepository
import com.dvidal.domain.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.any
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
class GetCurrentWeatherWithPositionUseCaseTest {

    private lateinit var getCurrentWeatherWithPositionUseCase: GetCurrentWeatherWithPositionUseCase

    @Mock lateinit var weatherChallengeRepository: WeatherChallengeRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        getCurrentWeatherWithPositionUseCase = GetCurrentWeatherWithPositionUseCase(weatherChallengeRepository, postExecutionThread)
    }

    @Test
    fun getCurrentWeatherCompletes() {

        stubGetCurrentWeather(Observable.just(ObjectDataFactory.makeCurrentWeather()))
        val testObserver = getCurrentWeatherWithPositionUseCase
                .buildUseCaseObservable(GetCurrentWeatherWithPositionUseCase.Params(100.0, 100.0)).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherReturnsData() {

        val currentWeather = ObjectDataFactory.makeCurrentWeather()
        stubGetCurrentWeather(Observable.just(currentWeather))
        val testObserver = getCurrentWeatherWithPositionUseCase
                .buildUseCaseObservable(GetCurrentWeatherWithPositionUseCase.Params(100.0, 100.0)).test()
        testObserver.assertValue(currentWeather)
    }

    private fun stubGetCurrentWeather(observable: Observable<CurrentWeather>) {
        whenever(weatherChallengeRepository.getCurrentWeatherWithPosition(100.0, 100.0))
                .thenReturn(observable)
    }
}