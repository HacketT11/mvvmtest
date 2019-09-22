package net.test.cloudmade.utils

import io.reactivex.Scheduler

data class Workers(val observe: Scheduler,
                   val subscribe: Scheduler)