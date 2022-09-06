package com.android.vncalling.utilities.rx

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler?

    fun computation(): Scheduler?

    fun io(): Scheduler?
}