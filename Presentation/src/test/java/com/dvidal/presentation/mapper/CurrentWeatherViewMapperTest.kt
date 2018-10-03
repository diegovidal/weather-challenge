package com.dvidal.presentation.mapper

import com.dvidal.presentation.utils.ObjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class CurrentWeatherViewMapperTest {

    private val mapper = CurrentWeatherViewMapper()

    @Test
    fun mapToViewMapsData() {
        val project = ObjectDataFactory.makeCurrentWeather()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id, projectView.id)
        assertEquals(project.name, projectView.name)
        assertEquals(project.weatherDesc, projectView.weatherDesc)
        assertEquals(project.temp, projectView.temp)
        assertEquals(project.pressure, projectView.pressure)
        assertEquals(project.humidity, projectView.humidity)
        assertEquals(project.windSpeed, projectView.windSpeed)
    }
}