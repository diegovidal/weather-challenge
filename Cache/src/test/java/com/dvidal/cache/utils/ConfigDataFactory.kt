package com.dvidal.cache.utils

import com.dvidal.cache.model.CachedCurrentWeather
import com.dvidal.cache.model.CachedConfig

/**
 * @author diegovidal on 01/10/2018.
 */

object ConfigDataFactory {

    fun makeCachedConfig(): CachedConfig {
        return CachedConfig(PrimitiveDataFactory.randomInt(), PrimitiveDataFactory.randomLong())
    }
}