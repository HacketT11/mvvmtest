package net.test.cloudmade.screens.search

import io.reactivex.Completable
import io.reactivex.subjects.PublishSubject
import net.test.cloudmade.core.base.BaseInteractor
import net.test.cloudmade.data.user.User
import net.test.cloudmade.data.user.UserRepository
import net.test.cloudmade.utils.Workers
import java.util.concurrent.TimeUnit

class SearchInteractor(workers: Workers,
                       private val userRepository: UserRepository) : BaseInteractor(workers) {

    private companion object {
        const val DELAY_TIME = 500L
    }

    private val querySubject = PublishSubject.create<Pair<String, Int>>()

    fun subscribeOnUserSearch(onSuccess: (List<User>) -> Unit, onError: (Throwable) -> Unit) {
        disposables.add(querySubject.debounce(DELAY_TIME, TimeUnit.MILLISECONDS)
                .switchMapSingle { userRepository.getUsersByQuery(it.first, it.second) }
                //because retry() ignores errors on subscribe
                .doOnError(onError)
                .retry()
                .subscribe(onSuccess, onError))
    }

    fun postQuery(query: String, page: Int, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        //to make interactor completely reactive
        disposables.add(Completable.fromCallable { querySubject.onNext(Pair(query, page)) }
                .subscribe(onSuccess, onError))
    }
}