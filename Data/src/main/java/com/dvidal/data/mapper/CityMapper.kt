package com.dvidal.data.mapper

import com.dvidal.data.model.CityEntity
import com.dvidal.domain.model.City
import javax.inject.Inject

/**
 * @author diegovidal on 02/10/2018.
 */
class CityMapper @Inject constructor(): EntityMapper<CityEntity, City> {

    override fun mapFromEntity(entity: CityEntity): City {
        return City(entity.id, entity.name, entity.country)
    }

    override fun mapToEntity(domain: City): CityEntity {
        return CityEntity(domain.id, domain.name, domain.country)
    }
}