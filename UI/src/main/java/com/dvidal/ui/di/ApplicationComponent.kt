package com.dvidal.ui.di

import android.app.Application
import com.dvidal.ui.di.module.*
import com.dvidal.ui.infrastructure.MyApplication
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
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApplication)
}