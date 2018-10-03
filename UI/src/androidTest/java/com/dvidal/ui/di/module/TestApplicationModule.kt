package com.dvidal.ui.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class TestApplicationModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}