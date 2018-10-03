package com.dvidal.remote.mapper

/**
 * @author diegovidal on 01/10/2018.
 */
interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E
}