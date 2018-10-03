package com.dvidal.ui.di.module

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.ui.features.cities.CityListActivity
import com.dvidal.ui.infrastructure.UiThread
import com.dvidal.ui.features.currentweather.CurrentWeatherActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): CurrentWeatherActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): CityListActivity
}