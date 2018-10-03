package com.dvidal.ui.features.currentweather

import android.provider.Settings.Global.getString
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.ui.R
import com.dvidal.ui.test.TestApplication
import com.dvidal.ui.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_current_weather.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(AndroidJUnit4::class)
class CurrentWeatherActivityTest {

    @get:Rule
    val activity = ActivityTestRule<CurrentWeatherActivity>(CurrentWeatherActivity::class.java, false, false)

    @Test
    fun activityLaunches() {

        activity.launchActivity(null)
    }

    @Test
    fun projectsDisplay() {
        val currentWeather = ObjectDataFactory.makeCurrentWeather()

        stubGetCurrentWeather(Observable.just(currentWeather))
        activity.launchActivity(null)

        onView(withId(R.id.tv_temperature)).check(matches(withText(
                activity.activity.getString(R.string.tv_temperature_format, currentWeather.temp.toInt()))))

        onView(withId(R.id.tv_wind_speed)).check(matches(withText(
                activity.activity.getString(R.string.tv_wind_speed_format, currentWeather.windSpeed))))

        onView(withId(R.id.tv_humidity)).check(matches(withText(
                activity.activity.getString(R.string.tv_humidity_format,currentWeather.humidity))))

        onView(withId(R.id.tv_pressure)).check(matches(withText(
                activity.activity.getString(R.string.tv_pressure_format,currentWeather.pressure))))


    }

    private fun stubGetCurrentWeather(observable: Observable<CurrentWeather>) {

        whenever(TestApplication.appComponent().weatherChallengeRepository().getCurrentWeatherWithCity(any()))
                .thenReturn(observable)
    }
}