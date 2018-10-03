package com.dvidal.cache.mapper

import com.dvidal.cache.model.CachedCity
import com.dvidal.cache.utils.ObjectDataFactory
import com.dvidal.data.model.CityEntity
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author diegovidal on 03/10/2018.
 */

@RunWith(JUnit4::class)
class CachedCityMapperTest {

    private val mapper = CachedCityMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = ObjectDataFactory.makeCachedCity()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ObjectDataFactory.makeCityEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedCity,
                                entity: CityEntity) {

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.country, model.country)
    }
}