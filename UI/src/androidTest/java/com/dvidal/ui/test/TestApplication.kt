package com.dvidal.ui.test

import android.app.Activity
import android.app.Application
import android.support.test.InstrumentationRegistry
import com.dvidal.ui.di.DaggerTestApplicationComponent
import com.dvidal.ui.di.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class TestApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: TestApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    companion object {

        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext
                    as TestApplication).appComponent
        }
    }
}