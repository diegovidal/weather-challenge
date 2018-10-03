package com.dvidal.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.dvidal.cache.db.CityConstants

/**
 * @author diegovidal on 03/10/2018.
 */

@Entity(tableName = CityConstants.TABLE_NAME)
data class CachedCity(
        @PrimaryKey var id: Long,
        var name: String,
        var country: String
)