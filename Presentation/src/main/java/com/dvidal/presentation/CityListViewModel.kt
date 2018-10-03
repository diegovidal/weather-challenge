package com.dvidal.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.dvidal.domain.interactor.cities.GetCitiesUseCase
import com.dvidal.domain.interactor.cities.SaveCitiesUseCase
import com.dvidal.domain.model.City
import com.dvidal.presentation.mapper.CityViewMapper
import com.dvidal.presentation.model.CityView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class CityListViewModel @Inject constructor(
        private val getCitiesUseCase: GetCitiesUseCase,
        private val saveCitiesUseCase: SaveCitiesUseCase,
        private val mapper: CityViewMapper)
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<CityView>>> = MutableLiveData()

    override fun onCleared() {
        getCitiesUseCase.dispose()
        saveCitiesUseCase.dispose()
        super.onCleared()
    }

    fun getCityList(): LiveData<Resource<List<CityView>>> {
        return liveData
    }

    fun fetchCityList() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getCitiesUseCase.execute(CityListSubscriber())
    }

    fun saveCityList(cities: List<City>) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return saveCitiesUseCase.execute(SaveCitiesUseCaseSubscriber(),
                SaveCitiesUseCase.Params.forCities(cities))
    }

    inner class CityListSubscriber: DisposableObserver<List<City>>() {

        override fun onComplete() {}

        override fun onNext(t: List<City>) {

            if (t.isEmpty()) {
                liveData.postValue(Resource(ResourceState.EMTPTY, null, null))
                return
            }
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class SaveCitiesUseCaseSubscriber: DisposableCompletableObserver() {

        override fun onComplete() {
            fetchCityList()
            Log.d("SaveCitiesUseCase","Entrou Aquii")
        }

        override fun onError(e: Throwable) {

        }
    }
}