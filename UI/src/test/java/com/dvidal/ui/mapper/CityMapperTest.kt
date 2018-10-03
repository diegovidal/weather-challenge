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
class CityMapperTest {

    private val mapper = CityViewMapper()

    @Test
    fun mapToViewMapsData() {
        val city = DataObjectFactory.makeCityView()
        val projectForUi = mapper.mapToView(city)

        assertEquals(city.id, projectForUi.id)
        assertEquals(city.name, projectForUi.name)
        assertEquals(city.country, projectForUi.country)

    }
}