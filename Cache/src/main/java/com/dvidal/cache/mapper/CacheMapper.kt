package com.dvidal.cache.mapper

/**
 * @author diegovidal on 01/10/2018.
 */
interface CacheMapper<C, E> {

    fun mapFromCached(type: C): E

    fun mapToCached(type: E): C
}