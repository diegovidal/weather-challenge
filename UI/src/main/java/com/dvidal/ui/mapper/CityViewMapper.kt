package com.dvidal.ui.mapper

import com.dvidal.presentation.model.CityView
import com.dvidal.ui.model.City
import javax.inject.Inject

/**
 * @author diegovidal on 03/10/2018.
 */
class CityViewMapper @Inject constructor(): ViewMapper<CityView, City> {

    override fun mapToView(presentation: CityView): City {
        return City(presentation.id, presentation.name, presentation.country)
    }
}