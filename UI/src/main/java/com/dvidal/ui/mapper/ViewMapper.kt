package com.dvidal.ui.mapper

/**
 * @author diegovidal on 02/10/2018.
 */
interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V
}