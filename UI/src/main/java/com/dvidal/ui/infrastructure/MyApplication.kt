package com.dvidal.ui.infrastructure

import android.app.Activity
import android.app.Application
import com.dvidal.ui.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class MyApplication: Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }
}