package com.dvidal.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dvidal.domain.interactor.currentweather.ClearCurrentWeatherUseCase
import com.dvidal.domain.interactor.currentweather.GetCurrentWeatherWithCityUseCase
import com.dvidal.domain.interactor.currentweather.GetCurrentWeatherWithPositionUseCase
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.presentation.mapper.CurrentWeatherViewMapper
import com.dvidal.presentation.model.CurrentWeatherView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class CurrentWeatherViewModel @Inject constructor(
        private val getCurrentWeatherWithCityUseCase: GetCurrentWeatherWithCityUseCase,
        private val getCurrentWeatherWithPositionUseCase: GetCurrentWeatherWithPositionUseCase,
        private val getClearCurrentWeatherUseCase: ClearCurrentWeatherUseCase,
        private val mapper: CurrentWeatherViewMapper)
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<CurrentWeatherView>> = MutableLiveData()

    override fun onCleared() {
        getCurrentWeatherWithCityUseCase.dispose()
        getCurrentWeatherWithPositionUseCase.dispose()
        getClearCurrentWeatherUseCase.dispose()
        super.onCleared()
    }

    fun getCurrentWeather(): LiveData<Resource<CurrentWeatherView>> {
        return liveData
    }

    fun fetchCurrentWeatherWithPosition(lat: Double, long: Double) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getCurrentWeatherWithPositionUseCase.execute(CurrentWeatherSubscriber(),
                GetCurrentWeatherWithPositionUseCase.Params(lat, long))
    }

    fun fetchCurrentWeatherWithCity(cityId: Long) {

        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getCurrentWeatherWithCityUseCase.execute(CurrentWeatherSubscriber(),
                GetCurrentWeatherWithCityUseCase.Params(cityId))
    }

    fun clearCurrentWeatherWithPosition(lat: Double, long: Double){
        return getClearCurrentWeatherUseCase.execute(ClearCurrentWeatherWithPositionSubscriber(lat, long))
    }

    fun clearCurrentWeatherWithCity(cityId: Long){
        return getClearCurrentWeatherUseCase.execute(ClearCurrentWeatherWithCitySubscriber(cityId))
    }

    inner class CurrentWeatherSubscriber: DisposableObserver<CurrentWeather>() {

        override fun onComplete() {}

        override fun onNext(t: CurrentWeather) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                     mapper.mapToView(t) , null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                    null, e.localizedMessage))
        }
    }

    inner class ClearCurrentWeatherWithPositionSubscriber(val lat: Double, val long: Double): DisposableCompletableObserver() {

        override fun onComplete() {

            fetchCurrentWeatherWithPosition(lat, long)
        }

        override fun onError(e: Throwable) {

        }
    }

    inner class ClearCurrentWeatherWithCitySubscriber(val cityId: Long): DisposableCompletableObserver() {

        override fun onComplete() {
            fetchCurrentWeatherWithCity(cityId)
        }

        override fun onError(e: Throwable) {

        }
    }
}