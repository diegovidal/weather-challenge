package com.dvidal.presentation.mapper

/**
 * @author diegovidal on 01/10/2018.
 */
interface Mapper<out V, in D> {

    fun mapToView(type: D): V
}