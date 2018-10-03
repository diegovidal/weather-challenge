package com.dvidal.ui.utils

import com.dvidal.presentation.model.CityView
import com.dvidal.presentation.model.CurrentWeatherView
import com.dvidal.ui.utils.PrimitiveDataFactory.randomFloat
import com.dvidal.ui.utils.PrimitiveDataFactory.randomInt
import com.dvidal.ui.utils.PrimitiveDataFactory.randomLong
import com.dvidal.ui.utils.PrimitiveDataFactory.randomString

/**
 * @author diegovidal on 02/10/2018.
 */
object DataObjectFactory {

    fun makeCurrentWeatherView(): CurrentWeatherView {
        return CurrentWeatherView(randomLong(), randomString(), randomString(),
                randomFloat(), randomFloat(), randomInt(), randomFloat())
    }

    fun makeCityView(): CityView {
        return CityView(randomLong(), randomString(), randomString())
    }
}