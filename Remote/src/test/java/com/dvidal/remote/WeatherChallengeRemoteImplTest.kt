package com.dvidal.remote

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.remote.mapper.CurrentWeatherResponseModelMapper
import com.dvidal.remote.model.CurrentWeatherResponse
import com.dvidal.remote.service.CurrentWeatherService
import com.dvidal.remote.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyLong

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class WeatherChallengeRemoteImplTest {

    private val mapper = mock<CurrentWeatherResponseModelMapper>()
    private val service = mock<CurrentWeatherService>()
    private val remote = WeatherChallengeRemoteImpl(service, mapper)

    @Test
    fun getCurrentWeatherWithPositionCompletes() {
        stubGetCurrentWeatherWithPosition(Observable.just(
                ObjectDataFactory.makeCurrentWeatherResponse()))

        stubCurrentWeatherResponseModelMapperMapFromModel(any(),
                ObjectDataFactory.makeCurrentWeatherEntity())

        val testObserver = remote.getCurrentWeatherWithPosition(100.0, 100.0).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherWithCityIdCompletes() {

        stubGetCurrentWeatherWithCityId(Observable.just(
                ObjectDataFactory.makeCurrentWeatherResponse()))

        stubCurrentWeatherResponseModelMapperMapFromModel(any(),
                ObjectDataFactory.makeCurrentWeatherEntity())

        val testObserver = remote.getCurrentWeatherWithCityId(100).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCurrentWeatherWithPositionReturnsData() {

        val response = ObjectDataFactory.makeCurrentWeatherResponse()
        val entity = ObjectDataFactory.makeCurrentWeatherEntity()

        stubCurrentWeatherResponseModelMapperMapFromModel(response, entity)
        stubGetCurrentWeatherWithPosition(Observable.just(response))

        val testObserver = remote.getCurrentWeatherWithPosition(100.0, 100.0).test()
        testObserver.assertValue(entity)
    }

    @Test
    fun getCurrentWeatherWithCityIdReturnsData() {
        val response = ObjectDataFactory.makeCurrentWeatherResponse()
        val entity = ObjectDataFactory.makeCurrentWeatherEntity()

        stubCurrentWeatherResponseModelMapperMapFromModel(response, entity)
        stubGetCurrentWeatherWithCityId(Observable.just(response))

        val testObserver = remote.getCurrentWeatherWithCityId(100).test()
        testObserver.assertValue(entity)
    }

    private fun stubGetCurrentWeatherWithCityId(observable:
                                                            Observable<CurrentWeatherResponse>) {
        whenever(service.searchCurrentWeatherWithCityId(100))
                .thenReturn(observable)
    }

    private fun stubGetCurrentWeatherWithPosition(observable:
                                                Observable<CurrentWeatherResponse>) {
        whenever(service.searchCurrentWeatherWithPosition(100.0, 100.0))
                .thenReturn(observable)
    }

    private fun stubCurrentWeatherResponseModelMapperMapFromModel(response: CurrentWeatherResponse,
                                                            entity: CurrentWeatherEntity) {
        whenever(mapper.mapFromModel(response))
                .thenReturn(entity)
    }
}