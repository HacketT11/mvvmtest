package net.test.cloudmade.core.base

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import net.test.cloudmade.utils.Workers

abstract class BaseInteractor(private val workers: Workers) {

    protected val disposables = CompositeDisposable()

    open fun clear(){
        disposables.clear()
    }

    fun <T> Single<T>.schedule() = apply {
        observeOn(workers.observe)
        subscribeOn(workers.subscribe)
    }

    fun <T> Observable<T>.schedule() = apply {
        observeOn(workers.observe)
        subscribeOn(workers.subscribe)
    }
}