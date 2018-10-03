package com.dvidal.ui.mapper

import com.dvidal.ui.utils.DataObjectFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(JUnit4::class)
class CurrentWeatherMapperTest {

    private val mapper = CurrentWeatherViewMapper()

    @Test
    fun mapToViewMapsData() {
        val currentWeatherView = DataObjectFactory.makeCurrentWeatherView()
        val currentWeatherForUi = mapper.mapToView(currentWeatherView)

        assertEquals(currentWeatherView.id, currentWeatherForUi.id)
        assertEquals(currentWeatherView.name, currentWeatherForUi.name)
        assertEquals(currentWeatherView.weatherDesc, currentWeatherForUi.weatherDesc)
        assertEquals(currentWeatherView.temp, currentWeatherForUi.temp)
        assertEquals(currentWeatherView.pressure, currentWeatherForUi.pressure)
        assertEquals(currentWeatherView.humidity, currentWeatherForUi.humidity)
        assertEquals(currentWeatherView.windSpeed, currentWeatherForUi.windSpeed)
    }
}