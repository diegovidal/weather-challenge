package com.dvidal.data.mapper

import com.dvidal.data.model.CurrentWeatherEntity
import com.dvidal.data.utils.ObjectDataFactory
import com.dvidal.domain.model.CurrentWeather
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 01/10/2018.
 */


@RunWith(JUnit4::class)
class CurrentWeatherMapperTest {

    private val mapper = CurrentWeatherMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = ObjectDataFactory.makeCurrentWeatherEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ObjectDataFactory.makeCurrentWeather()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: CurrentWeatherEntity,
                                model: CurrentWeather) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.weatherDesc, model.weatherDesc)
        assertEquals(entity.temp, model.temp)
        assertEquals(entity.pressure, model.pressure)
        assertEquals(entity.humidity, model.humidity)
        assertEquals(entity.windSpeed, model.windSpeed)
    }

}