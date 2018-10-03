package com.dvidal.cache.mapper

import com.dvidal.cache.model.CachedCurrentWeather
import com.dvidal.cache.utils.ObjectDataFactory
import com.dvidal.data.model.CurrentWeatherEntity
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class CachedCurrentWeatherMapperTest {

    private val mapper = CachedCurrentWeatherMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = ObjectDataFactory.makeCachedCurrentWeather()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ObjectDataFactory.makeCurrentWeatherEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedCurrentWeather,
                                entity: CurrentWeatherEntity) {

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.weatherDesc, model.weatherDesc)
        assertEquals(entity.temp, model.temp)
        assertEquals(entity.pressure, model.pressure)
        assertEquals(entity.humidity, model.humidity)
        assertEquals(entity.windSpeed, model.windSpeed)
    }

}