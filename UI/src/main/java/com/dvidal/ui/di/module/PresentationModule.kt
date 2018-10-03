package com.dvidal.ui.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dvidal.presentation.CityListViewModel
import com.dvidal.presentation.CurrentWeatherViewModel
import com.dvidal.ui.di.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    abstract fun bindCurrentWeatherViewModel(viewModel: CurrentWeatherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindCityListViewModel(
            viewModel: CityListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)