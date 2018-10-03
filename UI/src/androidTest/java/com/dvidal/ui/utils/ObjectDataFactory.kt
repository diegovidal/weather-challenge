package com.dvidal.ui.utils

import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.ui.utils.PrimitDataFactory.randomFloat
import com.dvidal.ui.utils.PrimitDataFactory.randomInt
import com.dvidal.ui.utils.PrimitDataFactory.randomLong
import com.dvidal.ui.utils.PrimitDataFactory.randomString


/**
 * @author diegovidal on 02/10/2018.
 */

object ObjectDataFactory {

    fun makeCurrentWeather(): CurrentWeather {
        return CurrentWeather(randomLong(), randomString(), randomString(),
                randomFloat(), randomFloat(), randomInt(), randomFloat())
    }

    fun makeCity(): City {
        return City(randomLong(), randomString(), randomString())
    }

}