package net.test.cloudmade.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.test.cloudmade.data.user.User

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {

    private val users = mutableListOf<User>()
    private var page = 1

    val isLoading = MutableLiveData<Boolean>()

    val liveDataUsers by lazy {
        interactor.subscribeOnUserSearch(::onDataLoaded, ::onError)
        return@lazy MutableLiveData<List<User>>()
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
        users.addAll(data)
        liveDataUsers.postValue(users)
        isLoading.postValue(false)
    }

    private fun onError(throwable: Throwable) {
        users.clear()
        liveDataUsers.postValue(users)
        isLoading.postValue(false)
    }
}