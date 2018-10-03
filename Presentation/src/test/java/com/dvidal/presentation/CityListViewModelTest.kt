package com.dvidal.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvidal.domain.interactor.cities.GetCitiesUseCase
import com.dvidal.domain.interactor.cities.SaveCitiesUseCase
import com.dvidal.domain.model.City
import com.dvidal.presentation.mapper.CityViewMapper
import com.dvidal.presentation.model.CityView
import com.dvidal.presentation.state.ResourceState
import com.dvidal.presentation.utils.PrimitiveDataFactory
import com.dvidal.presentation.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.RuntimeException

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class CityListViewModelTest {

    @get: Rule var intantTaskExecutorRule = InstantTaskExecutorRule()

    private var getCitiesUseCase = mock<GetCitiesUseCase>()
    private var saveCityListViewModel = mock<SaveCitiesUseCase>()
    private var mapper = mock<CityViewMapper>()

    var cityListViewModel = CityListViewModel(
            getCitiesUseCase, saveCityListViewModel, mapper
    )

    @Captor
    var captor = argumentCaptor<DisposableObserver<List<City>>>()

    @Test
    fun fetchCitiesExecutesUseCase() {

        cityListViewModel.fetchCityList()
        verify(getCitiesUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchCitiesReturnsSuccess() {

        val cities = ObjectDataFactory.makeCityList(2)
        val citiesView= ObjectDataFactory.makeCityViewList(2)

        stubCityMapperMapToView(citiesView[0], cities[0])
        stubCityMapperMapToView(citiesView[1], cities[1])

        cityListViewModel.fetchCityList()

        verify(getCitiesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(cities)

        assertEquals(ResourceState.SUCCESS, cityListViewModel
                .getCityList().value?.status)
    }

    @Test
    fun fetchCitiesReturnsData() {
        val cities = ObjectDataFactory.makeCityList(2)
        val citiesView= ObjectDataFactory.makeCityViewList(2)

        stubCityMapperMapToView(citiesView[0], cities[0])
        stubCityMapperMapToView(citiesView[1], cities[1])

        cityListViewModel.fetchCityList()

        verify(getCitiesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(cities)

        assertEquals(citiesView,
                cityListViewModel.getCityList().value?.data)
    }

    @Test
    fun fetchCitiesReturnsError() {

        cityListViewModel.fetchCityList()

        verify(getCitiesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR,
                cityListViewModel.getCityList().value?.status)
    }

    @Test
    fun fetchCitiesReturnsMessageForError() {

        val errorMessage = PrimitiveDataFactory.randomString()
        cityListViewModel.fetchCityList()

        verify(getCitiesUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage,
                cityListViewModel.getCityList().value?.message)
    }

    private fun stubCityMapperMapToView(cityView: CityView,
                                           city: City) {
        whenever(mapper.mapToView(city))
                .thenReturn(cityView)
    }
}