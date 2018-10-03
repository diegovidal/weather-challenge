package com.dvidal.data.mapper

import com.dvidal.data.model.CityEntity
import com.dvidal.data.utils.ObjectDataFactory
import com.dvidal.domain.model.City
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(JUnit4::class)
class CityMapperTest {

    private val mapper = CityMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = ObjectDataFactory.makeCityEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ObjectDataFactory.makeCity()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: CityEntity,
                                model: City) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.country, model.country)
    }
}