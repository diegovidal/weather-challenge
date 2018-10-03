package com.dvidal.data.mapper

/**
 * @author diegovidal on 30/09/2018.
 */
interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E
}