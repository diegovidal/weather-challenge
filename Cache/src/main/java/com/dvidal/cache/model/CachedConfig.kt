package com.dvidal.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.dvidal.cache.db.ConfigConstants

/**
 * @author diegovidal on 01/10/2018.
 */

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class CachedConfig(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        var lastCacheTime: Long)