package com.dvidal.ui.features.cities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dvidal.domain.model.City
import com.dvidal.ui.R
import com.dvidal.ui.test.TestApplication
import com.dvidal.ui.utils.ObjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(AndroidJUnit4::class)
class CityListActivityTest {

    @get:Rule
    val activity = ActivityTestRule<CityListActivity>(CityListActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubWeatherChallengeRepositoryGetCityList(Observable.just(listOf(
                ObjectDataFactory.makeCity())))
        activity.launchActivity(null)
    }

    @Test
    fun cityListDisplay() {
        val cities = listOf(ObjectDataFactory.makeCity(), ObjectDataFactory.makeCity(),
                ObjectDataFactory.makeCity(), ObjectDataFactory.makeCity())
        stubWeatherChallengeRepositoryGetCityList(Observable.just(cities))

        activity.launchActivity(null)

        cities.forEachIndexed { index, project ->
            onView(withId(R.id.recycler_cities))
                    .perform(RecyclerViewActions.scrollToPosition<CityListAdapter.ViewHolder>(
                            index))

            onView(withId(R.id.recycler_cities))
                    .check(matches(hasDescendant(withText(project.name))))
        }
    }

    private fun stubWeatherChallengeRepositoryGetCityList(observable: Observable<List<City>>) {
        whenever(TestApplication.appComponent().weatherChallengeRepository().getCityList())
                .thenReturn(observable)
    }
}