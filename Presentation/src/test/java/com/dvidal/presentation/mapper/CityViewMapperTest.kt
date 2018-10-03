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
class CityViewMapperTest {

    private val mapper = CityViewMapper()

    @Test
    fun mapToViewMapsData() {
        val city = ObjectDataFactory.makeCity()
        val cityView = mapper.mapToView(city)

        assertEquals(city.id, cityView.id)
        assertEquals(city.name, cityView.name)
        assertEquals(city.country, cityView.country)
    }

}