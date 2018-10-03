package com.dvidal.presentation.utils

import com.dvidal.domain.model.City
import com.dvidal.domain.model.CurrentWeather
import com.dvidal.presentation.model.CityView
import com.dvidal.presentation.model.CurrentWeatherView
import com.dvidal.presentation.utils.PrimitiveDataFactory.randomDouble
import com.dvidal.presentation.utils.PrimitiveDataFactory.randomLong
import com.dvidal.presentation.utils.PrimitiveDataFactory.randomString

/**
 * @author diegovidal on 01/10/2018.
 */

object ObjectDataFactory {

    fun makeCurrentWeatherView(): CurrentWeatherView {
        return CurrentWeatherView(randomLong(),
                PrimitiveDataFactory.randomString(), PrimitiveDataFactory.randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCurrentWeather(): CurrentWeather {
        return CurrentWeather(randomLong(),
                PrimitiveDataFactory.randomString(), PrimitiveDataFactory.randomString(),
                PrimitiveDataFactory.randomFloat(), PrimitiveDataFactory.randomFloat(),
                PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomFloat())
    }

    fun makeCityView(): CityView {
        return CityView(randomLong(), randomString(), randomString())
    }

    fun makeCity(): City {
        return City(randomLong(), randomString(), randomString())
    }

    fun makeCityViewList(count: Int): List<CityView> {
        val citiesView = mutableListOf<CityView>()
        repeat(count) {
            citiesView.add(makeCityView())
        }
        return citiesView
    }

    fun makeCityList(count: Int): List<City> {
        val cities = mutableListOf<City>()
        repeat(count) {
            cities.add(makeCity())
        }
        return cities
    }
}