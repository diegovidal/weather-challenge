package com.dvidal.remote.mapper

import com.dvidal.remote.utils.ObjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 01/10/2018.
 */
@RunWith(JUnit4::class)
class CurrentWeatherResponseModelMapperTest {

    private val mapper = CurrentWeatherResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = ObjectDataFactory.makeCurrentWeatherResponse()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals("", entity.weatherDesc)
        assertEquals(model.weatherMain.temp, entity.temp)
        assertEquals(model.weatherMain.pressure, entity.pressure)
        assertEquals(model.weatherMain.humidity, entity.humidity)
        assertEquals(model.wind.speed, entity.windSpeed)
    }

}