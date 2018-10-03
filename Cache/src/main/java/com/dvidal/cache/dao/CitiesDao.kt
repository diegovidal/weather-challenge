package com.dvidal.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dvidal.cache.db.CityConstants.DELETE_CITIES
import com.dvidal.cache.db.CityConstants.QUERY_CITIES
import com.dvidal.cache.model.CachedCity
import io.reactivex.Flowable

/**
 * @author diegovidal on 01/10/2018.
 */

@Dao
abstract class CitiesDao {

    @Query(QUERY_CITIES)
    abstract fun getCityList(): Flowable<List<CachedCity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCityList(cities: List<CachedCity>)

    @Query(DELETE_CITIES)
    abstract fun deleteCityList()
}