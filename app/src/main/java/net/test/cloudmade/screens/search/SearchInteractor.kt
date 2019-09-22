package net.test.cloudmade.screens.search

import io.reactivex.disposables.Disposable
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

    var getUserDisposable: Disposable? = null

    fun subscribeOnDelayedQuery(onSuccess: (Pair<String, Int>) -> Unit) {
        disposables.add(PublishSubject.create<Pair<String, Int>>()
                .debounce(DELAY_TIME, TimeUnit.MILLISECONDS)
                .subscribe(onSuccess))
    }

    fun postQuery() {

    }

    fun onDebounce(query: String, page: Int, onSuccess: (List<User>) -> Unit, onError: (Throwable) -> Unit) {
        getUserDisposable?.dispose()
        getUserDisposable = userRepository.getUsersByQuery(query, page)
                .schedule()
                .subscribe(onSuccess, onError)
    }
}