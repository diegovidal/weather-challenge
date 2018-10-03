package com.dvidal.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dvidal.cache.db.ConfigConstants.QUERY_CONFIG
import com.dvidal.cache.model.CachedConfig
import io.reactivex.Maybe

/**
 * @author diegovidal on 01/10/2018.
 */

@Dao
abstract class ConfigDao {

    @Query(QUERY_CONFIG)
    abstract fun getConfig(): Maybe<CachedConfig>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: CachedConfig)
}