package com.dvidal.presentation.mapper

import com.dvidal.domain.model.City
import com.dvidal.presentation.model.CityView
import javax.inject.Inject

/**
 * @author diegovidal on 03/10/2018.
 */
class CityViewMapper @Inject constructor(): Mapper<CityView, City> {

    override fun mapToView(type: City): CityView {
        return CityView(type.id, type.name, type.country)
    }
}