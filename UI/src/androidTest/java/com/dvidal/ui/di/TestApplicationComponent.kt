package com.dvidal.ui.di

import android.app.Application
import com.dvidal.domain.repository.WeatherChallengeRepository
import com.dvidal.ui.di.module.*
import com.dvidal.ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author diegovidal on 02/10/2018.
 */

@Singleton
@Component( modules = [
    AndroidInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UiModule::class,
    TestRemoteModule::class
])
interface TestApplicationComponent {

    fun weatherChallengeRepository(): WeatherChallengeRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)
}