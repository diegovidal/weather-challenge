package com.dvidal.domain.executor

import io.reactivex.Scheduler

/**
 * @author diegovidal on 30/09/2018.
 */
interface PostExecutionThread {

    val scheduler: Scheduler
}