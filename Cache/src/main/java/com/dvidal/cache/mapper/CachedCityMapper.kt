package com.dvidal.cache.mapper

import com.dvidal.cache.model.CachedCity
import com.dvidal.data.model.CityEntity
import javax.inject.Inject

/**
 * @author diegovidal on 03/10/2018.
 */
class CachedCityMapper @Inject constructor(): CacheMapper<CachedCity, CityEntity> {

    override fun mapFromCached(type: CachedCity): CityEntity {
        return CityEntity(type.id, type.name, type.country)
    }

    override fun mapToCached(type: CityEntity): CachedCity {
        return CachedCity(type.id, type.name, type.country)
    }
}