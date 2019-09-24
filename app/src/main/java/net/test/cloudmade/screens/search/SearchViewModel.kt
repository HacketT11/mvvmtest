package net.test.cloudmade.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.test.cloudmade.data.user.User
import retrofit2.HttpException
import java.net.HttpURLConnection

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {

    private companion object {
        const val HTTP_UNPROCESSABLE = 422
    }

    private val users = mutableListOf<User>()
    private var page = 1

    val isLoading = MutableLiveData<Boolean>()

    val liveDataUsers by lazy {
        interactor.subscribeOnUserSearch(::onDataLoaded, ::onError)
        return@lazy MutableLiveData<Pair<List<User>, Boolean>>()
    }

    fun onSearch(query: String) {
        isLoading.postValue(true)
        page = 1
        users.clear()
        interactor.postQuery(query, page, {}, ::onError)
    }

    fun onNextPage(query: String) {
        page++
        interactor.postQuery(query, page, {}, ::onError)
    }

    override fun onCleared() {
        interactor.clear()
    }

    private fun onDataLoaded(data: List<User>) {
        val hasLoading = data.size >= SearchInteractor.PAGE_COUNT

        users.addAll(data)
        liveDataUsers.postValue(Pair(users, hasLoading))
        isLoading.postValue(false)
    }

    private fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            when (throwable.code()) {
                HTTP_UNPROCESSABLE -> users.clear()
                //more errors
            }
        }

        liveDataUsers.postValue(Pair(users, false))
        isLoading.postValue(false)
    }
}