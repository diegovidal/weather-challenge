package com.dvidal.presentation.state

/**
 * @author diegovidal on 01/10/2018.
 */
class Resource<out T> constructor(
        val status: ResourceState,
        val data: T?,
        val message: String?
)